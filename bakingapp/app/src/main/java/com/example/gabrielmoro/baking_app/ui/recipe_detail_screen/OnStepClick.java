package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen;

import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.model.Step;

public interface OnStepClick {

    void onStepClick(@NonNull Step step);

    boolean hasSupportToFrameLayout();
}
