package com.example.gabrielmoro.baking_app.api;

import com.example.gabrielmoro.baking_app.model.Recipe;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface APIService {

    @GET("59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
