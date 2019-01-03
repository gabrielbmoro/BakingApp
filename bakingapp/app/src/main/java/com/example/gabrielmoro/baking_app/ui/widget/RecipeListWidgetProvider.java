package com.example.gabrielmoro.baking_app.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.dao.WidgetItemDAO;
import com.example.gabrielmoro.baking_app.model.WidgetItem;

/**
 * Implementation of App Widget functionality.
 * Reference: https://www.programcreek.com/java-api-examples/?code=mgilangjanuar/GoSCELE/GoSCELE-master/app/src/main/java/com/mgilangjanuar/dev/goscele/modules/widget/view/ScheduleDailyWidget.java#
 */
public class RecipeListWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_CLICK = "action click";
    public static final String EXTRA_ITEM_POSITION = "extraItemPosition";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Intent serviceIntent = new Intent(context, RecipeListWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            Intent clickIntent = new Intent(context, RecipeListWidgetProvider.class);
            clickIntent.setAction(ACTION_CLICK);
            PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                    0, clickIntent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_list_widget);
            views.setRemoteAdapter(R.id.lvWidgetItems, serviceIntent);
            views.setViewVisibility(R.id.lvWidgetItems, View.VISIBLE);
            views.setPendingIntentTemplate(R.id.lvWidgetItems, clickPendingIntent);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_CLICK.equals(intent.getAction())) {
            int position = intent.getIntExtra(EXTRA_ITEM_POSITION, -1);

            WidgetItem itemTarget = WidgetItemDAO.getMyInstance().all().get(position);
            if (itemTarget != null) {
                itemTarget.setIngredientsVisibility(!itemTarget.isIngredientsVisibility());
            }
        }
        super.onReceive(context, intent);
    }
}

