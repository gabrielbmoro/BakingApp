package com.example.gabrielmoro.baking_app.ui.main_screen.adapter;

import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.ui.base.GeneralBaseAdapter;

import java.util.List;

public class RecipeAdapterList extends GeneralBaseAdapter<RecipeItemViewModel> {

    public RecipeAdapterList(@NonNull List<RecipeItemViewModel> aLstElements) {
        super(aLstElements);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.layout_recipe_item;
    }
}
