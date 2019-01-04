package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.gabrielmoro.baking_app.api.APICallBackResult;
import com.example.gabrielmoro.baking_app.api.APIRetrofitHandler;
import com.example.gabrielmoro.baking_app.dao.WidgetItemDAO;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.main_screen.adapter.RecipeAdapterList;
import com.example.gabrielmoro.baking_app.ui.main_screen.adapter.RecipeItemViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * ViewModels
 * Reference: https://developer.android.com/topic/libraries/architecture/viewmodel
 */
public class MainViewModel extends ViewModel {

    private RecipeAdapterList mainViewAdapter;
    private MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();

    public void setup(@NonNull RecipeAdapterList adapter) {
        mainViewAdapter = adapter;
    }

    public void setRecipesData(List<Recipe> alstRecipes) {
        List<RecipeItemViewModel> viewModels = new ArrayList<>();
        RecipeItemViewModel viewModelTmp;
        for (Recipe recipe : alstRecipes) {
            viewModelTmp = new RecipeItemViewModel();
            viewModelTmp.setup(recipe);
            viewModels.add(viewModelTmp);
        }
        mainViewAdapter.onUpdateAllElements(viewModels);
    }

    MutableLiveData<List<Recipe>> getAllRecipes(){
        APIRetrofitHandler.getMyInstance().getAllRecipes(new APICallBackResult<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> result) {
                if (result != null) {
                    recipes.postValue(result);
                    WidgetItemDAO.getMyInstance().save(result);
                    Timber.d("onSucess: %s", result.toString());
                }
            }

            @Override
            public void onFailure(Throwable problem) {
                if (problem != null)
                    Timber.d("onFailure: %s", problem.toString());
            }

            @Override
            public void onCompleted() {
                Timber.d("onCompleted: 100%");
            }
        });
        return recipes;
    }


    public RecipeAdapterList getMainViewAdapter() {
        return mainViewAdapter;
    }
}
