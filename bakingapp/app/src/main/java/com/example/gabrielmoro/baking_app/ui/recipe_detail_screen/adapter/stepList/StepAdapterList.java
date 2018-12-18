package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen.adapter.stepList;

import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.ui.base.GeneralBaseAdapter;

import java.util.List;

public class StepAdapterList extends GeneralBaseAdapter<StepItemViewModel> {

    public StepAdapterList(@NonNull List<StepItemViewModel> aLstElements) {
        super(aLstElements);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.layout_step_item;
    }
}
