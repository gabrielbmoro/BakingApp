package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.AdapterViewModels;
import com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.recipe_step_detail_screen.RecipeStepDetailActivity;

public class StepItemViewModel extends ViewModel implements AdapterViewModels<Step> {

    private String shortDescription = "";
    private String id = "";
    private Step stepTarget;

    @Override
    public void setup(@NonNull Step step) {
        stepTarget = step;
        fillTheFields();
    }

    private void fillTheFields() {
        shortDescription = stepTarget.getShortDescription();
        id = String.valueOf(stepTarget.getId());
    }

    @Override
    public void click(View view) {
        RecipeStepDetailActivity.startActivity(view.getContext(), stepTarget);
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public String getId() {
        return id;
    }
}
