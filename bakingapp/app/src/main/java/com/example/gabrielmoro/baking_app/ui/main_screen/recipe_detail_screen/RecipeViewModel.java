package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.arch.lifecycle.ViewModel;

import com.example.gabrielmoro.baking_app.model.Recipe;

public class RecipeViewModel extends ViewModel {

    private RecipeBaseObservable recipeBaseObservable;

    public void setup(Recipe targetRecipe) {
        recipeBaseObservable = new RecipeBaseObservable();
        recipeBaseObservable.setRecipeTarget(targetRecipe);
    }

}
