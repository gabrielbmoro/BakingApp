package com.example.gabrielmoro.baking_app.ui.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.dao.RecipeDAO;
import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference: https://www.programcreek.com/java-api-examples/?code=mgilangjanuar/GoSCELE/GoSCELE-master/app/src/main/java/com/mgilangjanuar/dev/goscele/modules/widget/view/ScheduleDailyWidget.java#
 */
public class DataAdapterWidget implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<WidgetItem> list;

    DataAdapterWidget(Context contextArgument) {
        context = contextArgument;
        list = new ArrayList<>();
        for(Recipe recipeItem : RecipeDAO.getMyInstance().all()) {
            list.add(new WidgetItem(recipeItem));
        }
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
        remoteViews.setTextViewText(R.id.tvItem, list.get(position).recipe.getName());
        remoteViews.setTextViewText(R.id.tvSubItems, getIngredientsTextFromRecipe(list.get(position).recipe));
        remoteViews.setViewVisibility(R.id.tvSubItems, list.get(position).ingredientsVisibility ? View.VISIBLE : View.GONE);
        return remoteViews;
    }

    private String getIngredientsTextFromRecipe(Recipe recipe) {

        StringBuilder text = new StringBuilder();

        for(Ingredient tmp : recipe.getIngredients()) {
            text.append(tmp.getIngredient()).append("\n");
        }
        return text.toString();
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
