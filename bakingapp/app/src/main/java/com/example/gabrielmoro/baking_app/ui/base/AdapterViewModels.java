package com.example.gabrielmoro.baking_app.ui.base;

import android.view.View;

/**
 * Define the general contract between the ViewModel objects
 * @param <T>
 */
public interface AdapterViewModels<T> {

    void setup(Object object, T item);

    void click(View view);
}
