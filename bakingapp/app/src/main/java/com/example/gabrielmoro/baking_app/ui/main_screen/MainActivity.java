package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.api.APICallBackResult;
import com.example.gabrielmoro.baking_app.api.APIRetrofitHandler;
import com.example.gabrielmoro.baking_app.databinding.ActivityMainBinding;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.GeneralBaseAdapter;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.ViewContractBaseAdapter;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.ViewTypes;
import com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private MainViewModel viewModel;

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
                    Log.d(TAG, "onSucess: " + result.toString());
                }
            }

            @Override
            public void onFailure(Throwable problem) {
                if (problem != null)
                    Log.d(TAG, "onFailure: " + problem.toString());
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: 100%");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.setup(new GeneralBaseAdapter<>(new ArrayList<Recipe>(),
                ViewTypes.RECIPE_ITEM,
                getLayoutInflater(),
                new ViewContractBaseAdapter<Recipe>() {
                    @Override
                    public void bindView(final @NonNull Recipe item, @NonNull View view) {
                        ((TextView) view.findViewById(R.id.tvRecipeName)).setText(item.getName());
                        view.getRootView().setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        RecipeDetailActivity.startActivity(v.getContext(), item);
                                    }
                                });
                    }
                }));
    }
}
