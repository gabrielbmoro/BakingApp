package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.main_screen.adapter.RecipeAdapterList;
import com.example.gabrielmoro.baking_app.ui.main_screen.adapter.RecipeItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private RecipeAdapterList mainViewAdapter;

    public void setup(@NonNull RecipeAdapterList adapter) {
        mainViewAdapter = adapter;
    }

    public void setRecipesData(List<Recipe> alstRecipes) {
        List<RecipeItemViewModel> viewModels = new ArrayList<>();
        RecipeItemViewModel viewModelTmp;
        for (Recipe recipe : alstRecipes) {
            viewModelTmp = new RecipeItemViewModel();
            viewModelTmp.setup(recipe);
            viewModels.add(viewModelTmp);
        }
        mainViewAdapter.onUpdateAllElements(viewModels);
    }

    public RecipeAdapterList getMainViewAdapter() {
        return mainViewAdapter;
    }
}
