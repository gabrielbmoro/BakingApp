package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.arch.lifecycle.ViewModel;
import android.view.LayoutInflater;

import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel extends ViewModel {

    private RecipeBaseObservable recipeBaseObservable;

    void setup(List<Step> steps, List<Ingredient> ingredients, LayoutInflater inflater) {
        StepAdapterList stepAdapterList = new StepAdapterList(convertStepsInStepViewModels(steps), inflater);
        IngredientAdapterList ingredientAdapterList = new IngredientAdapterList(convertIngredientInIngredientViewModels(ingredients), inflater);
        recipeBaseObservable = new RecipeBaseObservable(stepAdapterList, ingredientAdapterList);
    }

    private List<StepItemViewModel> convertStepsInStepViewModels(List<Step> steps) {
        List<StepItemViewModel> viewModels = new ArrayList<>();
        StepItemViewModel tmp;
        for (Step step : steps) {
            tmp = new StepItemViewModel();
            tmp.setup(step);
            viewModels.add(tmp);
        }
        return viewModels;
    }

    private List<IngredientViewModel> convertIngredientInIngredientViewModels(List<Ingredient> ingredients) {
        List<IngredientViewModel> viewModels = new ArrayList<>();
        IngredientViewModel tmp;
        for (Ingredient ingredient : ingredients) {
            tmp = new IngredientViewModel();
            tmp.setup(ingredient);
            viewModels.add(tmp);
        }
        return viewModels;
    }

    public RecipeBaseObservable getRecipeBaseObservable() {
        return recipeBaseObservable;
    }

}
