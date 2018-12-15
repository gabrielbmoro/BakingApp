package com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen;

import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.model.Step;

public interface OnStepChangedCallBack {
    void changeTheStep(@NonNull Step step);
}
