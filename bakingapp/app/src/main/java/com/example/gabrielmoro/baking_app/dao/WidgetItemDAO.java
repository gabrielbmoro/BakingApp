package com.example.gabrielmoro.baking_app.dao;

import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.WidgetItem;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by Widget Provider after the retrofit request.
 */
public class WidgetItemDAO {

    private static WidgetItemDAO myInstance;
    private List<WidgetItem> widgetItems;

    private WidgetItemDAO() {
        widgetItems = new ArrayList<>();
    }

    public static WidgetItemDAO getMyInstance() {
        if (myInstance == null)
            myInstance = new WidgetItemDAO();
        return myInstance;
    }

    public void save(List<Recipe> recipesArgument) {
        widgetItems.clear();
        for(Recipe recipeTmp : recipesArgument) {
            widgetItems.add(new WidgetItem(recipeTmp));
        }

    }

    public List<WidgetItem> all() {
        return widgetItems;
    }

    public void destroyAllInstances() {
        widgetItems.clear();
        widgetItems = null;
        myInstance = null;
    }

}
