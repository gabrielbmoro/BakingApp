package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import com.example.gabrielmoro.baking_app.BR;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.model.Recipe;

public class RecipeBaseObservable extends BaseObservable {

    private Recipe recipeTarget;

    public Recipe getRecipeTarget() {
        return recipeTarget;
    }

    @Bindable
    public void setRecipeTarget(Recipe recipeTarget) {
        this.recipeTarget = recipeTarget;
        notifyPropertyChanged(BR.recipeTarget);
    }
}
