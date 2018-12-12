package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.recipe_step_detail_screen.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.BR;

public class RecipeStepDetailBaseObserver extends BaseObservable {

    private String fullDescription;

    public String getFullDescription() {
        return fullDescription;
    }

    @Bindable
    void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
        notifyPropertyChanged(BR.fullDescription);
    }
}
