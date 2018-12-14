package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.stepList;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.AdapterViewModels;
import com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.OnStepClick;

public class StepItemViewModel extends ViewModel implements AdapterViewModels<Step> {

    private String shortDescription = "";
    private String id = "";
    private Step stepTarget;
    private Recipe recipeTarget;
    private OnStepClick contract;

    public StepItemViewModel(@NonNull OnStepClick contractArgument){
        contract = contractArgument;
    }

    private void fillTheFields() {
        shortDescription = stepTarget.getShortDescription();
        id = String.valueOf(stepTarget.getId());
    }

    @Override
    public void setup(@NonNull Step item) {
        stepTarget = item;
        fillTheFields();
    }

    public void setRecipe(@NonNull Recipe recipeArg) {
        recipeTarget = recipeArg;
    }

    @Override
    public void click(View view) {
        if (contract != null && stepTarget != null) {
            contract.onStepClick(stepTarget);
        }
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getId() {
        return id;
    }
}
