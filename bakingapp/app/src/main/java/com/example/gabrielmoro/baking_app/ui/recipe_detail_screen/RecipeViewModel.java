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
    private Recipe recipeTarget;
    private OnStepClick contract;

    public RecipeViewModel() {
    }

    public void setup(@NonNull Recipe recipeArgument, LayoutInflater inflater, @NonNull OnStepClick contractArg) {
        recipeTarget = recipeArgument;
        contract = contractArg;
        stepListAdapter = new StepAdapterList(convertStepsInStepViewModels(), inflater);
        ingredientListAdapter = new IngredientAdapterList(convertIngredientInIngredientViewModels(), inflater);
    }

    private List<StepItemViewModel> convertStepsInStepViewModels() {
        List<StepItemViewModel> viewModels = new ArrayList<>();
        StepItemViewModel tmp;
        for (Step step : recipeTarget.getSteps()) {
            tmp = new StepItemViewModel(contract);
            tmp.setup(step);
            tmp.setRecipe(recipeTarget);
            viewModels.add(tmp);
        }
        return viewModels;
    }

    private List<IngredientViewModel> convertIngredientInIngredientViewModels() {
        List<IngredientViewModel> viewModels = new ArrayList<>();
        IngredientViewModel tmp;
        for (Ingredient ingredient : recipeTarget.getIngredients()) {
            tmp = new IngredientViewModel();
            tmp.setup(ingredient);
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

    public Recipe getRecipeTarget() {
        return recipeTarget;
    }
}
