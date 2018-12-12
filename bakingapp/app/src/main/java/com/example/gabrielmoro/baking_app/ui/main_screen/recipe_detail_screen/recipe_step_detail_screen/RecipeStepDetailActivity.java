package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.recipe_step_detail_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityRecipeStepDetailBinding;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen.recipe_step_detail_screen.viewmodel.RecipeStepDetailViewModel;

public class RecipeStepDetailActivity extends AppCompatActivity {

    private static final String RECIPE_STEP_INTENT_KEY = "Step target";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecipeStepDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_step_detail);
        RecipeStepDetailViewModel viewModel = ViewModelProviders.of(this).get(RecipeStepDetailViewModel.class);
        binding.setViewModel(viewModel);

        if(!getIntent().hasExtra(RECIPE_STEP_INTENT_KEY)) {
            finish();
        } else {
            Step stepTarget = getIntent().getParcelableExtra(RECIPE_STEP_INTENT_KEY);
            if(stepTarget!=null) {
                viewModel.setup(stepTarget);
            }
        }
    }

    public static void startActivity(Context context, Step target) {
        Intent intent = new Intent(context, RecipeStepDetailActivity.class);
        intent.putExtra(RECIPE_STEP_INTENT_KEY, target);
        context.startActivity(intent);
    }
}
