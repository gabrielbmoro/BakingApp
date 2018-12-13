package com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.BR;

public class RecipeStepDetailBaseObserver extends BaseObservable {

    private String stepDescription;

    @Bindable
    public String getStepDescription() {
        return stepDescription;
    }

    void setStepDescription(String fullDescription) {
        this.stepDescription = fullDescription;
        notifyPropertyChanged(BR.stepDescription);
    }
}
