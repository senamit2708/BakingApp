package com.example.senamit.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;

/**
 * Created by senamit on 13/2/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void searchingBakingItemName(){

        onView(withId(R.id.recyclerRecipeName)).perform(RecyclerViewActions.scrollToPosition(3), RecyclerViewActions.actionOnItemAtPosition(3, click()));

    }

    @Test
    public void testSampleRecylcer(){
        if (getRvConunt()>0){
            onView(withId(R.id.recyclerRecipeName)).perform(RecyclerViewActions.scrollToPosition(3), RecyclerViewActions.actionOnItemAtPosition(3, click()));

        }
    }

    private int getRvConunt() {
        RecyclerView recyclerView = (RecyclerView)mActivityTestRule.getActivity().findViewById(R.id.recyclerRecipeName);
        return recyclerView.getAdapter().getItemCount();

    }

    @Test
    public void searchingBakingItemNamePosition(){
        onView(withId(R.id.recyclerRecipeName)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recyclerRecipeStep)).check(matches(isDisplayed()));

//        onView(withId(R.id.recyclerRecipeName)
//                .atPositionOnView(1, R.id.txt_recipe_ingredients))
//                .check(matches(withText("Test text")));

    }

    @Test
    public void searchBakingItemNextStepMatchingText(){
        onView(withId(R.id.recyclerRecipeName)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.txt_recipe_ingredients)).check(matches(isDisplayed()));

    }






//    public void BakingItemNameTest(){
//
//    }

}
