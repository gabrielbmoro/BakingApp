package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.databinding.BaseObservable;

public class RecipeBaseObservable extends BaseObservable {

    private StepAdapterList stepListAdapter;
    private IngredientAdapterList ingredientListAdapter;


    public RecipeBaseObservable(StepAdapterList stepAdapterList,
                                IngredientAdapterList ingredientAdapterList) {
        this.stepListAdapter = stepAdapterList;
        this.ingredientListAdapter = ingredientAdapterList;
    }


    public StepAdapterList getStepListAdapter() {
        return stepListAdapter;
    }

    public IngredientAdapterList getIngredientListAdapter() {
        return ingredientListAdapter;
    }
}
