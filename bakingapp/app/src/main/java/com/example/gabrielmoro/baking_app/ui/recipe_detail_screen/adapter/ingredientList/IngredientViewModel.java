package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.ingredientList;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.ui.base.AdapterViewModels;

public class IngredientViewModel extends ViewModel implements AdapterViewModels<Ingredient> {

    private String description;
    private String amount;

    @Override
    public void setup(@NonNull Ingredient ingredient) {
        description = ingredient.getIngredient();
        amount = ingredient.getQuantity() + " " + ingredient.getMeasure();
    }

    @Override
    public void click(View view) {

    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }
}
