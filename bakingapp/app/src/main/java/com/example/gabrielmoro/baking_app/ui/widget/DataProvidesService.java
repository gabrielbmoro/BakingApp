package com.example.gabrielmoro.baking_app.ui.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class DataProvidesService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new DataProvider(getBaseContext());
    }
}
