package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.senamit.bakingapp.fragment.FragmentRecipeBakingProcess;

public class BakingStepDescription extends AppCompatActivity {

    public static final String LOG_TAG = BakingStepDescription.class.getSimpleName();
    FrameLayout frameLayoutBakingStepInstruction;
    FragmentRecipeBakingProcess fragmentRecipeBakingProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_step_description);

        Intent intent = getIntent();
        int clickItemIndex = intent.getIntExtra("key", 20);

        Log.i(LOG_TAG, "inside the baking step description class");

        frameLayoutBakingStepInstruction = findViewById(R.id.frameLayoutRecipeStepInstruction);

         fragmentRecipeBakingProcess = new FragmentRecipeBakingProcess();
         fragmentRecipeBakingProcess.setClickItemIndex(clickItemIndex);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess).commit();

    }
}
