package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.arch.lifecycle.ViewModel;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.GeneralBaseAdapter;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MainBaseObservable observableObjects;

    void setup(GeneralBaseAdapter<Recipe> adapter) {
        observableObjects = new MainBaseObservable(adapter);
    }

    void setRecipesData(List<Recipe> alstRecipes) {
        observableObjects.setRecipesData(alstRecipes);
    }

    public MainBaseObservable getObservableObjects() {
        return observableObjects;
    }

}
