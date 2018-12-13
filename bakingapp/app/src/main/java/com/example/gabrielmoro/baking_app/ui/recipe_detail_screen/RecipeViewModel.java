package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.ingredientList.IngredientAdapterList;
import com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.ingredientList.IngredientViewModel;
import com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.stepList.StepAdapterList;
import com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.stepList.StepItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends ViewModel {

    private StepAdapterList stepListAdapter;
    private IngredientAdapterList ingredientListAdapter;

    public void setup(@NonNull Recipe recipeArgument, LayoutInflater inflater) {
        stepListAdapter = new StepAdapterList(convertStepsInStepViewModels(recipeArgument), inflater);
        ingredientListAdapter = new IngredientAdapterList(convertIngredientInIngredientViewModels(recipeArgument), inflater);
    }

    private List<StepItemViewModel> convertStepsInStepViewModels(@NonNull Recipe recipeTarget) {
        List<StepItemViewModel> viewModels = new ArrayList<>();
        StepItemViewModel tmp;
        for (Step step : recipeTarget.getSteps()) {
            tmp = new StepItemViewModel();
            tmp.setup(recipeTarget, step);
            viewModels.add(tmp);
        }
        return viewModels;
    }

    private List<IngredientViewModel> convertIngredientInIngredientViewModels(@NonNull Recipe recipeTarget) {
        List<IngredientViewModel> viewModels = new ArrayList<>();
        IngredientViewModel tmp;
        for (Ingredient ingredient : recipeTarget.getIngredients()) {
            tmp = new IngredientViewModel();
            tmp.setup(null, ingredient);
            viewModels.add(tmp);
        }
        return viewModels;
    }

    public StepAdapterList getStepListAdapter() {
        return stepListAdapter;
    }

    public IngredientAdapterList getIngredientListAdapter() {
        return ingredientListAdapter;
    }
}
