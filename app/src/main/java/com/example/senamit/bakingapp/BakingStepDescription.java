package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.senamit.bakingapp.fragment.FragmentRecipeBakingProcess;

import java.util.List;

public class BakingStepDescription extends AppCompatActivity {

    private Bundle bundle;
//    private BakingItems bakingItems;
    private List<BakingItems> bakingItems;
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
       bakingItems = bundle.getParcelableArrayList(KEY_RECIPE_STEP_PROCESS);
       int clikedItemIndex = bundle.getInt("key2", 20);

     frameLayoutBakingStepInstruction = findViewById(R.id.frameLayoutRecipeStepInstruction);

         fragmentRecipeBakingProcess = new FragmentRecipeBakingProcess();
         fragmentRecipeBakingProcess.setClickItemIndex(bakingItems.get(clikedItemIndex).getDescription());

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutRecipeStepInstruction, fragmentRecipeBakingProcess).commit();

    }
}
