package com.example.gabrielmoro.baking_app.ui.main_screen.recipe_detail_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.databinding.ActivityRecipeDetailBinding;
import com.example.gabrielmoro.baking_app.model.Ingredient;
import com.example.gabrielmoro.baking_app.model.Recipe;
import com.example.gabrielmoro.baking_app.model.Step;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.GeneralBaseAdapter;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.ViewContractBaseAdapter;
import com.example.gabrielmoro.baking_app.ui.base.base_adapter.ViewTypes;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPE_INTENT_KEY = "Recipe";
    private RecipeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        ActivityRecipeDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);
        binding.setViewModel(viewModel);

        if (!getIntent().hasExtra(RECIPE_INTENT_KEY))
            finish();
        else {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_INTENT_KEY);
            viewModel.setup(getGeneralAdapterToSteps(recipe), getGeneralAdapterToIngredient(recipe));
        }
    }


    private GeneralBaseAdapter<Step> getGeneralAdapterToSteps(Recipe recipe) {
        return new GeneralBaseAdapter<>(recipe.getSteps(), ViewTypes.STEP_ITEM, getLayoutInflater(),
                new ViewContractBaseAdapter<Step>() {
                    @Override
                    public void bindView(@NonNull Step item, @NonNull View view) {
                        ((TextView) view.findViewById(R.id.tvShortDescription)).setText(item.getShortDescription());
                    }
                });
    }

    private GeneralBaseAdapter<Ingredient> getGeneralAdapterToIngredient(Recipe recipe) {
        return new GeneralBaseAdapter<>(recipe.getIngredients(), ViewTypes.INGREDIENT_ITEM, getLayoutInflater(),
                new ViewContractBaseAdapter<Ingredient>() {
                    @Override
                    public void bindView(@NonNull Ingredient item, @NonNull View view) {
                        ((TextView) view.findViewById(R.id.tvIngredientName)).setText(item.getIngredient());
                        ((TextView) view.findViewById(R.id.tvIngredientAmount)).setText(item.getQuantity().toString());
                    }
                });
    }


    public static void startActivity(Context context, Recipe target) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_INTENT_KEY, target);
        context.startActivity(intent);
    }
}
