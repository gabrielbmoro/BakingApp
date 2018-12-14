package com.example.gabrielmoro.baking_app.ui.player_and_description_panel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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

    private static String PARCELABLE_BUNDLE_KEY = "Step to Fragment";
    private FragmentPlayerAndInstructionBinding binding;
    private PlayerAndDescriptionViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(PlayerAndDescriptionViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_and_instruction, container, false);
        binding.setViewModel(viewModel);

        Parcelable parcelable = getArguments().getParcelable(PARCELABLE_BUNDLE_KEY);
        if (parcelable instanceof Step) {
            String urlVideo = ((Step) parcelable).getVideoURL();
            String description = ((Step) parcelable).getDescription();
            viewModel.setup(description, getStepUri(urlVideo));
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeVideoURL();
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
    public void changeVideoURL() {
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(getContext());
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(Objects.requireNonNull(getContext()),
                Util.getUserAgent(getContext(), getResources().getResourceName(R.string.app_name)));

        Uri uri = viewModel.getUriVideo();
        if (uri != null) {
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri);
            binding.cvmedia.setPlayer(player);
            player.prepare(videoSource);
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
