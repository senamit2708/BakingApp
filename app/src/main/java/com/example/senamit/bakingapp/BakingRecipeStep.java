package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.senamit.bakingapp.fragment.FragmentRecipeBakingProcess;
import com.example.senamit.bakingapp.fragment.FragmentRecipeSteps;

import java.util.ArrayList;
import java.util.List;

public class BakingRecipeStep extends AppCompatActivity implements FragmentRecipeSteps.StepSelectedListener{

    int recipeId;
    private static final String LOG_TAG= BakingRecipeStep.class.getSimpleName();
    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentRecipeSteps fragmentRecipeSteps;
    FragmentRecipeBakingProcess fragmentRecipeBakingProcess;
    String KEY_RECIPE_STEP_PROCESS="keyBakingProcess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_recipe_step);

        Intent intent = getIntent();
        recipeId = intent.getIntExtra("recipeId", 0);

        Log.i(LOG_TAG, "the receipe is id is  "+ recipeId);

         fragmentRecipeSteps = new FragmentRecipeSteps();
        fragmentRecipeSteps.setRecipeId(recipeId);
         manager = getSupportFragmentManager();
         transaction = manager.beginTransaction();
        transaction.add(R.id.frame_receipe_step_container, fragmentRecipeSteps).commit();

         fragmentRecipeBakingProcess = new FragmentRecipeBakingProcess();


    }

    @Override
    public void stepNumberSelected(int clickItemIndex, List<BakingItems> bakingItems) {

        Intent intent = new Intent(BakingRecipeStep.this, BakingStepDescription.class);
        ArrayList<BakingItems> bakingItemsArrayList;
        bakingItemsArrayList = (ArrayList<BakingItems>) bakingItems;
        intent.putParcelableArrayListExtra(KEY_RECIPE_STEP_PROCESS, bakingItemsArrayList);
        intent.putExtra("key2", clickItemIndex);
        startActivity(intent);


    }
}
