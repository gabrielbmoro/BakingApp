package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.recipe_step_detail_screen.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.model.Step;

public class RecipeStepDetailViewModel extends ViewModel {

    private RecipeStepDetailBaseObserver recipeStepBaseObserver = new RecipeStepDetailBaseObserver();

    public void setup(@NonNull Step stepTarget) {
        recipeStepBaseObserver.setFullDescription(stepTarget.getDescription());
    }

    public RecipeStepDetailBaseObserver getRecipeStepBaseObserver() {
        return recipeStepBaseObserver;
    }

}
