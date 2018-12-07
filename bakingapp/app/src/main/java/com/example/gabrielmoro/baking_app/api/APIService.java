package com.example.gabrielmoro.baking_app.api;

import com.example.gabrielmoro.baking_app.model.Recipe;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Defines the endpoints.
 * Reference: https://medium.com/3xplore/handling-api-calls-using-retrofit-2-and-rxjava-2-1871c891b6ae
 */
public interface APIService {

    @GET("59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
