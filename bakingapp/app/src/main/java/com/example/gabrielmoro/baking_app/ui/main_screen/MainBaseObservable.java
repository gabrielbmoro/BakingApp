package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.BR;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.GeneralBaseAdapter;

import java.util.List;

public class MainBaseObservable extends BaseObservable {

    private GeneralBaseAdapter<Recipe> mainViewAdapter;

    MainBaseObservable(GeneralBaseAdapter<Recipe> baseAdapter) {
        mainViewAdapter = baseAdapter;
    }

    @Bindable
    void setRecipesData(List<Recipe> recipes) {
        mainViewAdapter.onUpdateAllElements(recipes);
        notifyPropertyChanged(BR.recipesData);
    }

    public GeneralBaseAdapter<Recipe> getGeneralRecipe() {
        return mainViewAdapter;
    }





}
