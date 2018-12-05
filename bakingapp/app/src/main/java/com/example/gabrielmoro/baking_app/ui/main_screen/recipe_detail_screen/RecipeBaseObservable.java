package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.GeneralBaseAdapter;

public class RecipeBaseObservable extends BaseObservable {

    private GeneralBaseAdapter<Step> stepListAdapter;
    private GeneralBaseAdapter<Ingredient> ingredientListAdapter;


    public RecipeBaseObservable(GeneralBaseAdapter<Step> stepListAdapterArgument,
                                GeneralBaseAdapter<Ingredient> ingredientListAdapterArgument) {
        this.stepListAdapter = stepListAdapterArgument;
        this.ingredientListAdapter = ingredientListAdapterArgument;
    }


    public GeneralBaseAdapter<Step> getStepListAdapter() {
        return stepListAdapter;
    }

    public GeneralBaseAdapter<Ingredient> getIngredientListAdapter() {
        return ingredientListAdapter;
    }
}
