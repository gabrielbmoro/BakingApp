package com.example.gabrielmoro.baking_app.ui.main_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityMainBinding;
import com.example.gabrielmoro.baking_app.ui.main_screen.adapter.RecipeAdapterList;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * This is the main screen that list all recipts.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setViewModel(viewModel);

        setupRecyclerView();

        viewModel.getAllRecipes().observe(this, recipes -> viewModel.setRecipesData(recipes));
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.setup(new RecipeAdapterList(new ArrayList<>()));
    }

    private void setupRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        binding.rvRecipes.setLayoutManager(llm);
    }

}
