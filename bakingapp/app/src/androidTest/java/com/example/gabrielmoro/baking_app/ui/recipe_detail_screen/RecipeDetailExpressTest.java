package com.example.gabrielmoro.baking_app.ui.recipe_detail_screen;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.gabrielmoro.baking_app.R;
import com.example.gabrielmoro.baking_app.ui.main_screen.MainActivity;
import com.example.gabrielmoro.baking_app.ui.recipe_step_detail_screen.RecipeStepDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Reference: https://developer.android.com/training/testing/ui-testing/espresso-testing
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailExpressTest {
    @Rule
    public ActivityTestRule<MainActivity> launchActivity = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<RecipeDetailActivity> recipeDetailActivity = new ActivityTestRule<>(RecipeDetailActivity.class);

    @Rule
    public ActivityTestRule<RecipeStepDetailActivity> stepDetailActivity = new ActivityTestRule<>(RecipeStepDetailActivity.class);

    @Before
    public void setup() {
        onView(withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void selectTheFirstStepToShow() {
        onView(withId(R.id.rvSteps)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.flMediaPlayerAndDescription)).check(matches(isDisplayed()));
    }

    @Test
    public void selectTheSecondStepToShow() {
        onView(withId(R.id.rvSteps)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.flMediaPlayerAndDescription)).check(matches(isDisplayed()));
    }

    @After
    public void after() {
        stepDetailActivity.finishActivity();
        recipeDetailActivity.finishActivity();
    }

}
