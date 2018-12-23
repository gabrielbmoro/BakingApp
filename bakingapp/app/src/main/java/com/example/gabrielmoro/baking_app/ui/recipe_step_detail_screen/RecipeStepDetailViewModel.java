package com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;

public class RecipeStepDetailViewModel extends ViewModel {

    private Recipe recipeTarget;
    private MutableLiveData<Step> stepTarget;
    private RecipeStepDetailBaseObserver recipeStepBaseObserver;
    private OnStepChangedCallBack contract;


    public RecipeStepDetailViewModel() {
        recipeStepBaseObserver = new RecipeStepDetailBaseObserver();
        stepTarget = new MutableLiveData<>();
    }

    public void setup(@NonNull Recipe recipeArgument, @NonNull OnStepChangedCallBack contractArgument, @NonNull Step stepArgument) {
        recipeTarget = recipeArgument;
        contract = contractArgument;
        setupStep(stepArgument);
    }


    private void setupStep(@NonNull Step stepArgument) {
        stepTarget.setValue(stepArgument);
        String description = stepTarget.getValue().getDescription();
        recipeStepBaseObserver.setStepDescription(description);
        contract.changeTheStep(stepArgument);
    }

    public RecipeStepDetailBaseObserver getRecipeStepBaseObserver() {
        return recipeStepBaseObserver;
    }

    private int getCurrentStepIndex() {
        for (int i = 0; i < recipeTarget.getSteps().size(); i++) {
            if (recipeTarget.getSteps().get(i).getId().equals(stepTarget.getValue().getId())) {
                return i;
            }
        }
        return 0;
    }

    private boolean isAValidIndex(int index) {
        return (index >= 0 && index < recipeTarget.getSteps().size());
    }

    public void onPrevious() {
        int newIndex = getCurrentStepIndex() - 1;
        if (isAValidIndex(newIndex)) {
            Step newStep = recipeTarget.getSteps().get(newIndex);
            if (newStep != null) {
                setupStep(newStep);
            }
        }
    }

    public void onNext() {
        int newIndex = getCurrentStepIndex() + 1;
        if (isAValidIndex(newIndex)) {
            Step newStep = recipeTarget.getSteps().get(newIndex);
            if (newStep != null) {
                setupStep(newStep);
            }
        }
    }

}
