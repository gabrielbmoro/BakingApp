package com.example.gabrielmoro.baking_app.ui.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Reference: https://www.programcreek.com/java-api-examples/?code=mgilangjanuar/GoSCELE/GoSCELE-master/app/src/main/java/com/mgilangjanuar/dev/goscele/modules/widget/view/ScheduleDailyWidget.java#
 */
public class DataProvidesService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new DataProvider(getBaseContext());
    }
}
