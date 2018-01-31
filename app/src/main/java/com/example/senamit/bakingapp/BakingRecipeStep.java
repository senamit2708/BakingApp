package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.senamit.bakingapp.fragment.FragmentRecipeSteps;

public class BakingRecipeStep extends AppCompatActivity {

    int recipeId;
    private static final String LOG_TAG= BakingRecipeStep.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_recipe_step);

        Intent intent = getIntent();
        recipeId = intent.getIntExtra("recipeId", 0);

        Log.i(LOG_TAG, "the receipe is id is  "+ recipeId);

        FragmentRecipeSteps fragmentRecipeSteps = new FragmentRecipeSteps();
        fragmentRecipeSteps.setRecipeId(recipeId);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_receipe_step_container, fragmentRecipeSteps).commit();

    }
}
