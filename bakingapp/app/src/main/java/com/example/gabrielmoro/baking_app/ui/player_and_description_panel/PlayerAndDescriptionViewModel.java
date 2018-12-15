package com.example.gabrielmoro.baking_app.ui.player_and_description_panel;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;
import android.support.annotation.NonNull;

public class PlayerAndDescriptionViewModel extends ViewModel {

    private DescriptionBaseObservable descriptionBaseObservable = new DescriptionBaseObservable();
    private Uri urlVideo;

    public void setup(@NonNull String descriptionArg, @NonNull Uri urlVideoArg) {
        descriptionBaseObservable.setDescription(descriptionArg);
        urlVideo = urlVideoArg;
    }

    public DescriptionBaseObservable getDescriptionBaseObservable() {
        return descriptionBaseObservable;
    }

    Uri getUriVideo() {
        return urlVideo;
    }
}
