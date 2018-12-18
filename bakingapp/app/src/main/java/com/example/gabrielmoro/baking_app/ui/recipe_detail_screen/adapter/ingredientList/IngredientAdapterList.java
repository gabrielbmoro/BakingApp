package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.ingredientList;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.ui.base.GeneralBaseAdapter;

import java.util.List;

public class IngredientAdapterList extends GeneralBaseAdapter<IngredientViewModel> {

    public IngredientAdapterList(@NonNull List<IngredientViewModel> aLstElements, LayoutInflater aInflater) {
        super(aLstElements);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.layout_ingredient_item;
    }

}
