package com.example.gabrielmoro.baking_app.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.dao.RecipeDAO;
import com.example.gabrielmoro.baking_app.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference: https://www.programcreek.com/java-api-examples/?code=mgilangjanuar/GoSCELE/GoSCELE-master/app/src/main/java/com/mgilangjanuar/dev/goscele/modules/widget/view/ScheduleDailyWidget.java#
 */
public class DataProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<Recipe> list;

    DataProvider(Context contextArgument) {
        context = contextArgument;
        list = RecipeDAO.getMyInstance().all();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        Intent intent = new Intent(context, RecipeListWidget.class);
        intent.setAction("CHECK_LIST");
        intent.putExtra("isEmptyList", list.isEmpty());
        context.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
        remoteViews.setTextViewText(R.id.tvItem, list.get(position).getName());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
