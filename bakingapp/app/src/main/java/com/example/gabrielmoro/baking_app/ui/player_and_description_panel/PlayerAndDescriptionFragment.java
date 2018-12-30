package com.example.gabrielmoro.baking_app.ui.player_and_description_panel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.FragmentPlayerAndInstructionBinding;
import com.example.gabrielmoro.baking_app.model.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class PlayerAndDescriptionFragment extends Fragment {

    // autoplay = false
    private boolean autoPlay = false;

    // used to remember the playback position
    private int currentWindow;
    private long playbackPosition;

    // constant fields for saving and restoring bundle
    public static final String AUTOPLAY = "autoplay";
    public static final String CURRENT_WINDOW_INDEX = "current_window_index";
    public static final String PLAYBACK_POSITION = "playback_position";

    private static String PARCELABLE_BUNDLE_KEY = "Step to Fragment";
    private FragmentPlayerAndInstructionBinding binding;
    private PlayerAndDescriptionViewModel viewModel;

    private SimpleExoPlayer mPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(PlayerAndDescriptionViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_and_instruction, container, false);
        binding.setViewModel(viewModel);


        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION, 0);
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_INDEX, 0);
            autoPlay = savedInstanceState.getBoolean(AUTOPLAY, false);
        }

        if (getArguments() != null) {
            Parcelable parcelable = getArguments().getParcelable(PARCELABLE_BUNDLE_KEY);
            if (parcelable instanceof Step) {
                String urlVideo = ((Step) parcelable).getVideoURL();
                String description = ((Step) parcelable).getDescription();
                viewModel.setup(description, getStepUri(urlVideo));
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M || mPlayer == null) {
            initializePlayer();
        }
    }

    /**
     * If the version is less or equal to Marshmallow, the player will be released in onPause method.
     */
    @Override
    public void onPause() {
        super.onPause();
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) releasePlayer();
    }

    /**
     * If the version is greater than Marshmallow, the player will be released in onStop method.
     */
    @Override
    public void onStop() {
        super.onStop();
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) releasePlayer();
    }

    private void releasePlayer() {
        binding.cvmedia.setPlayer(null);
        mPlayer.release();
    }

    private Uri getStepUri(String urlArgument) {
        // This is the MediaSource representing the media to be played.
        URL url;
        Uri uri = null;
        try {
            url = new URL(urlArgument);
            uri = Uri.parse(url.toURI().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }

    /**
     * Reference: https://google.github.io/ExoPlayer/guide.html
     */
    public void initializePlayer() {
        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(Objects.requireNonNull(getContext()),
                Util.getUserAgent(getContext(), getResources().getResourceName(R.string.app_name)));

        Uri uri = viewModel.getUriVideo();
        if (uri != null) {
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri);
            mPlayer.setPlayWhenReady(autoPlay);
            // resume playback position
            mPlayer.seekTo(currentWindow, playbackPosition);
            mPlayer.prepare(videoSource);
            binding.cvmedia.setPlayer(mPlayer);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPlayer == null) {
            outState.putLong(PLAYBACK_POSITION, playbackPosition);
            outState.putInt(CURRENT_WINDOW_INDEX, currentWindow);
            outState.putBoolean(AUTOPLAY, autoPlay);
        }
    }

    public static PlayerAndDescriptionFragment newInstance(Step step) {
        PlayerAndDescriptionFragment fragment = new PlayerAndDescriptionFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCELABLE_BUNDLE_KEY, step);
        fragment.setArguments(bundle);
        return fragment;
    }

}
