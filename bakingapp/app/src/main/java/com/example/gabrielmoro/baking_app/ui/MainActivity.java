package com.example.gabrielmoro.baking_app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.api.APICallBackResult;
import com.example.gabrielmoro.baking_app.api.APIRetrofitHandler;
import com.example.gabrielmoro.baking_app.model.Recipe;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIRetrofitHandler.getMyInstance().getAllRecipes(new APICallBackResult<List<Recipe>>() {
            @Override
            public void onSucess(List<Recipe> result) {
                if (result != null) {
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
}
