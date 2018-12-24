package com.example.gabrielmoro.baking_app.dao;

import com.example.gabrielmoro.baking_app.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by Widget Provider after the retrofit request.
 */
public class RecipeDAO {

    private static RecipeDAO myInstance;
    private List<Recipe> recipes;

    private RecipeDAO() {
        recipes = new ArrayList<>();
    }

    public static RecipeDAO getMyInstance() {
        if (myInstance == null)
            myInstance = new RecipeDAO();
        return myInstance;
    }

    public void save(List<Recipe> recipesArgument) {
        recipes.clear();
        recipes.addAll(recipesArgument);
    }

    public List<Recipe> all() {
        return recipes;
    }

    public void destroyAllInstances() {
        recipes.clear();
        recipes = null;
        myInstance = null;
    }

}
