package com.example.gabrielmoro.baking_app.ui.main_screen.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.main_screen.RecipeAdapterList;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MainBaseObservable observableObjects;

    public void setup(RecipeAdapterList adapter) {
        observableObjects = new MainBaseObservable(adapter);
    }

    public void setRecipesData(List<Recipe> alstRecipes) {
        observableObjects.setRecipesData(alstRecipes);
    }

    public MainBaseObservable getObservableObjects() {
        return observableObjects;
    }

}
