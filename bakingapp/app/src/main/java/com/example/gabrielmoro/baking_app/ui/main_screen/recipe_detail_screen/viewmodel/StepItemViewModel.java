package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.AdapterViewModels;

public class StepItemViewModel extends ViewModel implements AdapterViewModels<Step> {

    private String shortDescription = "";
    private String id = "";

    @Override
    public void setup(@NonNull Step step) {
        shortDescription = step.getShortDescription();
        id = String.valueOf(step.getId());
    }

    @Override
    public void click(View view) {
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public String getId() {
        return id;
    }
}
