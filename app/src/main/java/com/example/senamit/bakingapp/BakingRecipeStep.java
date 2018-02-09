package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

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
    String KEY_RECIPE_STEP_PROCESS="keyBakingProcess";
    FragmentRecipeBakingProcess fragmentRecipeBakingProcess;
    Boolean mTwoPane=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_recipe_step);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        recipeId = intent.getIntExtra("recipeId", 0);

         fragmentRecipeSteps = new FragmentRecipeSteps();
        fragmentRecipeSteps.setRecipeId(recipeId);
         manager = getSupportFragmentManager();
         transaction = manager.beginTransaction();
        transaction.add(R.id.frame_receipe_step_container, fragmentRecipeSteps).commit();




       if(findViewById(R.id.linear_two_pane_layout)!=null){
           mTwoPane=true;
           fragmentRecipeBakingProcess = new FragmentRecipeBakingProcess();
           getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess).commit();
       }

    }

    @Override
    public void stepNumberSelected(int clickItemIndex, List<BakingItems> bakingItems) {

        if (mTwoPane==false) {
            Intent intent = new Intent(BakingRecipeStep.this, BakingStepDescription.class);
            ArrayList<BakingItems> bakingItemsArrayList;
            bakingItemsArrayList = (ArrayList<BakingItems>) bakingItems;
            intent.putParcelableArrayListExtra(KEY_RECIPE_STEP_PROCESS, bakingItemsArrayList);
            intent.putExtra("key2", clickItemIndex);
            startActivity(intent);
        }
        else {
            FragmentRecipeBakingProcess fragmentRecipeBakingProcess2 = new FragmentRecipeBakingProcess();
            Log.i(LOG_TAG, "the video url is "+bakingItems.get(clickItemIndex).getVideoURL());
            fragmentRecipeBakingProcess2.setClickItemIndex(bakingItems.get(clickItemIndex).getDescription(),bakingItems.get(clickItemIndex).getVideoURL());
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess2).commit();

        }



    }
}
