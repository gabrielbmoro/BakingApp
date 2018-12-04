package com.example.gabrielmoro.baking_app.ui.base.base_adapter;

import android.support.annotation.NonNull;
import android.view.View;

public interface ViewContractBaseAdapter<T> {
    void bindView(@NonNull T item, @NonNull View view);
}