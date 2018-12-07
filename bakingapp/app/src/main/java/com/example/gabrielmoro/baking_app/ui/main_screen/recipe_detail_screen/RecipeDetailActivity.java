package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityRecipeDetailBinding;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.viewmodel.RecipeViewModel;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPE_INTENT_KEY = "Recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecipeViewModel viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        ActivityRecipeDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);
        binding.setViewModel(viewModel);

        if (!getIntent().hasExtra(RECIPE_INTENT_KEY))
            finish();
        else {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_INTENT_KEY);
            viewModel.setup(recipe.getSteps(), recipe.getIngredients(), getLayoutInflater());
        }
    }

    public static void startActivity(Context context, Recipe target) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_INTENT_KEY, target);
        context.startActivity(intent);
    }
}
