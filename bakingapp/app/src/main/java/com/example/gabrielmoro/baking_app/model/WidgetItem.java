package com.example.gabrielmoro.baking_app.model;

public class WidgetItem {

    private Recipe recipe;
    private boolean ingredientsVisibility;

    public WidgetItem(Recipe recipeArgument) {
        recipe = recipeArgument;
        ingredientsVisibility = false;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean isIngredientsVisibility() {
        return ingredientsVisibility;
    }

    public void setIngredientsVisibility(boolean ingredientsVisibility) {
        this.ingredientsVisibility = ingredientsVisibility;
    }
}
