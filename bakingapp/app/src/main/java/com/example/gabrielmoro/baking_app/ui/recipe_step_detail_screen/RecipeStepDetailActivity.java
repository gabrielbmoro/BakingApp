package com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityRecipeStepDetailBinding;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.google.android.exoplayer2.ExoPlayer;
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

public class RecipeStepDetailActivity extends AppCompatActivity implements OnVideoCallback {


    private ActivityRecipeStepDetailBinding binding;
    private static final String RECIPE_STEP_INTENT_KEY = "Step target";
    private static final String RECIPE_INTENT_KEY = "Recipe target";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_step_detail);
        RecipeStepDetailViewModel viewModel = ViewModelProviders.of(this).get(RecipeStepDetailViewModel.class);
        binding.setViewModel(viewModel);

        if (!getIntent().hasExtra(RECIPE_STEP_INTENT_KEY) || !getIntent().hasExtra(RECIPE_INTENT_KEY)) {
            finish();
        } else {
            Recipe recipeTarget = getIntent().getParcelableExtra(RECIPE_INTENT_KEY);
            Step stepTarget = getIntent().getParcelableExtra(RECIPE_STEP_INTENT_KEY);
            if (stepTarget != null) {
                viewModel.setup(recipeTarget, this, stepTarget);


                // Prepare the player with the source.
                //binding.cvmedia.setPlayer(videoSource);
            }
        }
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

    public static void startActivity(Context context, Recipe recipeOrigin, Step target) {
        Intent intent = new Intent(context, RecipeStepDetailActivity.class);
        intent.putExtra(RECIPE_STEP_INTENT_KEY, target);
        intent.putExtra(RECIPE_INTENT_KEY, recipeOrigin);
        context.startActivity(intent);
    }

    @Override
    public void changeVideoURL(String url) {
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getResources().getResourceName(R.string.app_name)));

        Uri uri = getStepUri(url);
        if (uri != null) {
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri);
            binding.cvmedia.setPlayer(player);
            player.prepare(videoSource);
        }
    }
}
