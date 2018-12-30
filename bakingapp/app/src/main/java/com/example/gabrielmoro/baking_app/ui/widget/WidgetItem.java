package com.example.gabrielmoro.baking_app.ui.widget;

import com.example.gabrielmoro.baking_app.model.Recipe;

class WidgetItem {

    Recipe recipe;
    boolean ingredientsVisibility;

    WidgetItem(Recipe recipeArgument) {
        recipe = recipeArgument;
        ingredientsVisibility = false;
    }
}
