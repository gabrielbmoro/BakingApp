package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.arch.lifecycle.ViewModel;

import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.GeneralBaseAdapter;

public class RecipeViewModel extends ViewModel {

    private RecipeBaseObservable recipeBaseObservable;

    void setup(GeneralBaseAdapter<Step> generalAdapterToSteps, GeneralBaseAdapter<Ingredient> generalAdapterToIngredients) {
        recipeBaseObservable = new RecipeBaseObservable(generalAdapterToSteps, generalAdapterToIngredients);
    }

    public RecipeBaseObservable getRecipeBaseObservable() {
        return recipeBaseObservable;
    }

}
