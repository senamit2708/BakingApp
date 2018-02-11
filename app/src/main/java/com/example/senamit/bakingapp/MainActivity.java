package com.example.senamit.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  BakingRecipeNameAdapter.ListItemClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerRecipeName;
    private RecyclerView.LayoutManager mLayoutManager;
    BakingRecipeNameAdapter mbakingRecipeNameAdapter;
    List<BakingItems> bakingItems;
    private int recyclerNumColumn = 0;
    private int LOADER_MANAGER_ID = 0;
    BakingRecipeName bakingRecipeName;
     static String stringUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "inside on create method");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (amIConnected()){
            Log.i("connectionCheck", "user is connected");
            setupRecyclerView();
        }
    }

    private boolean amIConnected() {
        //its asking about internet connection is available or not
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //its asking about network is available or not
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        MenuItem refreshItem = menu.findItem(R.id.refresh);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                refreshActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshActivity() {
        finish();
        startActivity(getIntent());
        Toast.makeText(MainActivity.this, "please on the internet connection", Toast.LENGTH_SHORT).show();

    }

    private void setupRecyclerView() {
        recyclerRecipeName = findViewById(R.id.recyclerRecipeName);
        recyclerNumColumn = getResources().getInteger(R.integer.recycler_num_columns);
        mLayoutManager = new GridLayoutManager(this, recyclerNumColumn);
        recyclerRecipeName.setLayoutManager(mLayoutManager);
        bakingRecipeName = new BakingRecipeName();
        bakingRecipeName.loadRecipe();
    }


    @Override
    public void onListItemClick(int clickedItemIndex, BakingItems bakingItems) {
        Intent intent = new Intent(MainActivity.this, BakingRecipeStep.class);
        int recipeId = bakingItems.getRecipeId();
        intent.putExtra("recipeId", recipeId);
        startActivity(intent);
    }

    private class BakingRecipeName implements LoaderManager.LoaderCallbacks<List<BakingItems>>{
        public void loadRecipe() {
            getSupportLoaderManager().initLoader(LOADER_MANAGER_ID, null, this);
        }

        @Override
        public Loader<List<BakingItems>> onCreateLoader(int id, Bundle args) {
            return new BakingRecipeNameLoader(MainActivity.this, stringUrl);
        }

        @Override
        public void onLoadFinished(Loader<List<BakingItems>> loader, List<BakingItems> data) {
            mbakingRecipeNameAdapter = new BakingRecipeNameAdapter(data, MainActivity.this);
            recyclerRecipeName.setAdapter(mbakingRecipeNameAdapter);
        }

        @Override
        public void onLoaderReset(Loader<List<BakingItems>> loader) {

        }
    }
}
