package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.senamit.bakingapp.fragment.FragmentRecipeBakingProcess;

public class BakingStepDescription extends AppCompatActivity {

    private Bundle bundle;
    private BakingItems bakingItems;
    String KEY_RECIPE_STEP_PROCESS="keyBakingProcess";

    public static final String LOG_TAG = BakingStepDescription.class.getSimpleName();
    FrameLayout frameLayoutBakingStepInstruction;
    FragmentRecipeBakingProcess fragmentRecipeBakingProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_step_description);

        Intent intent = getIntent();
       bundle = intent.getExtras();
       bakingItems = bundle.getParcelable(KEY_RECIPE_STEP_PROCESS);

        frameLayoutBakingStepInstruction = findViewById(R.id.frameLayoutRecipeStepInstruction);

         fragmentRecipeBakingProcess = new FragmentRecipeBakingProcess();
         fragmentRecipeBakingProcess.setClickItemIndex(bakingItems.getDescription());

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess).commit();

    }
}
