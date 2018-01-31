package com.example.senamit.bakingapp;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BakingItems>>, BakingRecipeNameAdapter.ListItemClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerRecipeName;
    private RecyclerView.LayoutManager mLayoutManager;
    BakingRecipeNameAdapter mbakingRecipeNameAdapter;
    List<BakingItems> bakingItems;
    String stringUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bakingItems = new ArrayList<>();
//        bakingItems.add(new BakingItems("chicken", R.drawable.imagetest1));
//
//        bakingItems.add(new BakingItems("Biryani", R.drawable.imagetest1));


        recyclerRecipeName = findViewById(R.id.recyclerRecipeName);
        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerRecipeName.setLayoutManager(mLayoutManager);

        Log.i(LOG_TAG, "inside on create");


        getSupportLoaderManager().initLoader(0, savedInstanceState, this);




    }

    @Override
    public Loader<List<BakingItems>> onCreateLoader(int id, Bundle args) {

        Log.i(LOG_TAG, "inside on create loader");
        return new BakingRecipeNameLoader(this,stringUrl );
    }

    @Override
    public void onLoadFinished(Loader<List<BakingItems>> loader, List<BakingItems> data) {
        Log.i(LOG_TAG, "inisde on load finished");
        mbakingRecipeNameAdapter= new BakingRecipeNameAdapter(data, this);
        recyclerRecipeName.setAdapter(mbakingRecipeNameAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<BakingItems>> loader) {

        recyclerRecipeName.setAdapter(null);
    }

    @Override
    public void onListItemClick(int clickedItemIndex, BakingItems bakingItems) {

        Intent intent = new Intent(MainActivity.this, BakingRecipeStep.class);

        int recipeId = bakingItems.getRecipeId();
        Log.i(LOG_TAG, "the reipe id is "+recipeId);
        intent.putExtra("recipeId", recipeId);
        startActivity(intent);


    }
}
