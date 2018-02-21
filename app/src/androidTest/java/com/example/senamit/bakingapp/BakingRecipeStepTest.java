package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.senamit.bakingapp.fragment.FragmentRecipeSteps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by senamit on 21/2/18.
 */


@RunWith(AndroidJUnit4.class)
public class BakingRecipeStepTest {



    @Rule
    public ActivityTestRule<BakingRecipeStep> mActivityTestRule =
            new ActivityTestRule<>(BakingRecipeStep.class);


    @Test
    public void searchingBakingItemSteps(){

        onView(withId(R.id.recyclerRecipeStep)).perform(RecyclerViewActions.scrollToPosition(3));

    }

    @Test
    public void searchingBakingItemStepsClick2(){
        onView(withId(R.id.recyclerRecipeStep)).perform(RecyclerViewActions.scrollToPosition(3), RecyclerViewActions.actionOnItemAtPosition(3, click()));

    }
    @Test
    public void testSampleRecylcer(){
        if (getRvConunt()>0){
            onView(withId(R.id.recyclerRecipeStep)).perform(RecyclerViewActions.scrollToPosition(3), RecyclerViewActions.actionOnItemAtPosition(3, click()));

        }
    }

    private int getRvConunt() {
        RecyclerView recyclerView = (RecyclerView)mActivityTestRule.getActivity().findViewById(R.id.recyclerRecipeStep);
        return recyclerView.getAdapter().getItemCount();

    }



}
