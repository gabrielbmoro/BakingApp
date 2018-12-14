package com.example.gabrielmoro.baking_app.ui.player_and_description_panel;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;
import android.support.annotation.NonNull;

public class PlayerAndDescriptionViewModel extends ViewModel {

    private String description;
    private Uri urlVideo;

    public void setup(@NonNull String descriptionArg, @NonNull Uri urlVideoArg) {
        description = descriptionArg;
        urlVideo = urlVideoArg;
    }

    public String getDescription() {
        return description;
    }

    Uri getUriVideo() {
        return urlVideo;
    }
}
