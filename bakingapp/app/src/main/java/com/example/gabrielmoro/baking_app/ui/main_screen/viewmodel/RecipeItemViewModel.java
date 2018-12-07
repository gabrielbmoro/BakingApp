package com.example.gabrielmoro.baking_app.ui.main_screen.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.base.AdapterViewModels;
import com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.RecipeDetailActivity;

public class RecipeItemViewModel extends ViewModel implements AdapterViewModels<Recipe> {

    private String title;
    private Recipe recipeTarget;

    @Override
    public void setup(Recipe item) {
        recipeTarget = item;
        title = recipeTarget.getName();
    }

    @Override
    public void click(View view) {
        RecipeDetailActivity.startActivity(view.getContext(), recipeTarget);
    }

    public String getTitle() {
        return title;
    }
}
