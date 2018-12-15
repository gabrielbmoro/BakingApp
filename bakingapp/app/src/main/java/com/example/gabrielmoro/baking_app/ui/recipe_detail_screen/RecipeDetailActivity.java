package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityRecipeDetailBinding;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.player_and_description_panel.PlayerAndDescriptionFragment;
import com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen.RecipeStepDetailActivity;

public class RecipeDetailActivity extends AppCompatActivity implements OnStepClick {

    private ActivityRecipeDetailBinding binding;
    private RecipeViewModel viewModel;
    public static final String RECIPE_INTENT_KEY = "Recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);
        binding.setViewModel(viewModel);

        if (!getIntent().hasExtra(RECIPE_INTENT_KEY))
            finish();
        else {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_INTENT_KEY);
            viewModel.setup(recipe, getLayoutInflater(), this);
        }
    }

    public static void startActivity(Context context, Recipe target) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_INTENT_KEY, target);
        context.startActivity(intent);
    }

    @Override
    public void onStepClick(@NonNull Step step) {
        if (hasSupportToFrameLayout()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flMediaPlayerAndDescription, PlayerAndDescriptionFragment.newInstance(step))
                    .commit();
        } else {
            RecipeStepDetailActivity.startActivity(this, viewModel.getRecipeTarget(), step);
        }

    }

    @Override
    public boolean hasSupportToFrameLayout() {
        return binding.flMediaPlayerAndDescription != null;
    }
}
