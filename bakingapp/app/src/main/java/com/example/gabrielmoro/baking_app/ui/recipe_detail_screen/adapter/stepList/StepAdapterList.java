package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.stepList;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.ui.base.GeneralBaseAdapter;

import java.util.List;

public class StepAdapterList extends GeneralBaseAdapter<StepItemViewModel> {
    public StepAdapterList(@NonNull List<StepItemViewModel> aLstElements, LayoutInflater aInflater) {
        super(aLstElements, aInflater);
    }

    @Override
    protected int getLayoutResourceAccordingViewType() {
        return R.layout.layout_step_item;
    }
}
