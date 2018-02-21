package com.example.senamit.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.senamit.bakingapp.fragment.FragmentRecipeBakingProcess;

import java.util.List;

public class BakingStepDescription extends AppCompatActivity {

    private Bundle bundle;
    private List<BakingItems> bakingItems;
    private String KEY_RECIPE_STEP_PROCESS = "keyBakingProcess";
    Button btnNextStepDescription;
    Button btnPreviousStepDescription;
    private int clikedItemIndex;
    private int stepCount;

    int orientationId;
    private final int LANDSCAPE=2;

    public static final String LOG_TAG = BakingStepDescription.class.getSimpleName();
    FragmentRecipeBakingProcess fragmentRecipeBakingProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_steps_description);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnNextStepDescription = findViewById(R.id.btnNextStepDescription);
        btnPreviousStepDescription = findViewById(R.id.btnPreviousStepDescription);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        bakingItems = bundle.getParcelableArrayList(KEY_RECIPE_STEP_PROCESS);
        clikedItemIndex = bundle.getInt("key2", 20);
        stepCount = bakingItems.size();

        if (savedInstanceState == null) {
            fragmentRecipeBakingProcess = new FragmentRecipeBakingProcess();
            fragmentRecipeBakingProcess.setClickItemIndex(bakingItems.get(clikedItemIndex).getDescription(), bakingItems.get(clikedItemIndex).getVideoURL(), bakingItems.get(clikedItemIndex).getThumbnailURL());
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess).commit();
        }
        orientationId=   getResources().getConfiguration().orientation;
        if (orientationId==LANDSCAPE){
            btnNextStepDescription.setVisibility(View.GONE);
            btnPreviousStepDescription.setVisibility(View.GONE);
        }
        else {
            btnNextStepDescription.setVisibility(View.VISIBLE);
            btnPreviousStepDescription.setVisibility(View.VISIBLE);
        }


        btnNextStepDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clikedItemIndex == 0) {
                    btnPreviousStepDescription.setVisibility(View.VISIBLE);
                }

                if (clikedItemIndex < stepCount - 1) {
                    clikedItemIndex++;
                    FragmentRecipeBakingProcess fragmentRecipeBakingProcess2 = new FragmentRecipeBakingProcess();
                    fragmentRecipeBakingProcess2.setClickItemIndex(bakingItems.get(clikedItemIndex).getDescription(), bakingItems.get(clikedItemIndex).getVideoURL(), bakingItems.get(clikedItemIndex).getThumbnailURL());
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess2).commit();
                } else {
                    btnNextStepDescription.setVisibility(View.INVISIBLE);
                    Toast.makeText(BakingStepDescription.this, "No more steps", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPreviousStepDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clikedItemIndex == stepCount - 1) {
                    btnNextStepDescription.setVisibility(View.VISIBLE);
                }
                if (clikedItemIndex > 0) {
                    clikedItemIndex--;
                    FragmentRecipeBakingProcess fragmentRecipeBakingProcess2 = new FragmentRecipeBakingProcess();
                    fragmentRecipeBakingProcess2.setClickItemIndex(bakingItems.get(clikedItemIndex).getDescription(), bakingItems.get(clikedItemIndex).getVideoURL(), bakingItems.get(clikedItemIndex).getThumbnailURL());
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess2).commit();
                    Log.i(LOG_TAG, "the clicked index is " + clikedItemIndex);
                } else {
                    btnPreviousStepDescription.setVisibility(View.INVISIBLE);
                    Toast.makeText(BakingStepDescription.this, "No previous steps", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
