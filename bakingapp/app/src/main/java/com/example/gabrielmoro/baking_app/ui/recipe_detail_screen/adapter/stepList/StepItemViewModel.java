package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.stepList;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.AdapterViewModels;
import com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen.RecipeStepDetailActivity;

public class StepItemViewModel extends ViewModel implements AdapterViewModels<Step> {

    private String shortDescription = "";
    private String id = "";
    private Step stepTarget;
    private Recipe recipeTarget;

    private void fillTheFields() {
        shortDescription = stepTarget.getShortDescription();
        id = String.valueOf(stepTarget.getId());
    }

    @Override
    public void setup(Object object, @NonNull Step item) {
        recipeTarget = (Recipe) object;
        stepTarget = item;
        fillTheFields();
    }

    @Override
    public void click(View view) {
        RecipeStepDetailActivity.startActivity(view.getContext(), recipeTarget, stepTarget);
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public String getId() {
        return id;
    }
}
