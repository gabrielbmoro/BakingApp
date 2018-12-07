package com.example.gabrielmoro.baking_app.ui.main_screen.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.BR;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.main_screen.RecipeAdapterList;

import java.util.ArrayList;
import java.util.List;

public class MainBaseObservable extends BaseObservable {

    private RecipeAdapterList mainViewAdapter;

    MainBaseObservable(RecipeAdapterList baseAdapter) {
        mainViewAdapter = baseAdapter;
    }

    @Bindable
    void setRecipesData(List<Recipe> recipes) {
        List<RecipeItemViewModel> viewModels = new ArrayList<>();
        RecipeItemViewModel viewModelTmp;
        for (Recipe recipe : recipes) {
            viewModelTmp = new RecipeItemViewModel();
            viewModelTmp.setup(recipe);
            viewModels.add(viewModelTmp);
        }

        mainViewAdapter.onUpdateAllElements(viewModels);
        notifyPropertyChanged(BR.recipesData);
    }

    public RecipeAdapterList getGeneralRecipe() {
        return mainViewAdapter;
    }


}
