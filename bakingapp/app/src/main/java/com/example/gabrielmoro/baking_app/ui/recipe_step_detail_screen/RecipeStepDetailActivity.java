package com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityRecipeStepDetailBinding;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.player_and_description_panel.PlayerAndDescriptionFragment;

public class RecipeStepDetailActivity extends AppCompatActivity implements OnStepChangedCallBack {


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
            }
        }
    }


    public static void startActivity(Context context, Recipe recipeOrigin, Step target) {
        Intent intent = new Intent(context, RecipeStepDetailActivity.class);
        intent.putExtra(RECIPE_STEP_INTENT_KEY, target);
        intent.putExtra(RECIPE_INTENT_KEY, recipeOrigin);
        context.startActivity(intent);
    }

    @Override
    public void changeTheStep(@NonNull Step step) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.flMediaPlayerAndDescription, PlayerAndDescriptionFragment.newInstance(step))
                .commit();
    }
}
