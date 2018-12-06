package com.example.gabrielmoro.baking_app.ui.base;

import android.view.View;

public interface AdapterViewModels<T> {

    void setup(T item);

    void click(View view);
}
