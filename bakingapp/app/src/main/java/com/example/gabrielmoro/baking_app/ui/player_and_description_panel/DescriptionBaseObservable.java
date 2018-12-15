package com.example.gabrielmoro.baking_app.ui.player_and_description_panel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gabrielmoro.baking_app.BR;

public class DescriptionBaseObservable extends BaseObservable {

    private String description;


    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
}
