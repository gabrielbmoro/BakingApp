package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.api.APICallBackResult;
import com.example.gabrielmoro.baking_app.api.APIRetrofitHandler;
import com.example.gabrielmoro.baking_app.databinding.ActivityMainBinding;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.main_screen.viewmodel.MainViewModel;
import com.example.gabrielmoro.baking_app.ui.main_screen.viewmodel.RecipeItemViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * This is the main screen that list all recipts.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private MainViewModel viewModel;

    public MainActivity() {
        Timber.tag(TAG);
    }

    /**
     * To create the databinding calls I use the follow reference.
     * Reference: https://developer.android.com/topic/libraries/data-binding/expressions?hl=pt-br
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setViewModel(viewModel);

        APIRetrofitHandler.getMyInstance().getAllRecipes(new APICallBackResult<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> result) {
                if (result != null) {
                    viewModel.setRecipesData(result);
                    Timber.d("onSucess: " + result.toString());
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
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.setup(new RecipeAdapterList(new ArrayList<RecipeItemViewModel>(), getLayoutInflater()));
    }
}
