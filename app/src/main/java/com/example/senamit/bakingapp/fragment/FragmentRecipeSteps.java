package com.example.senamit.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.senamit.bakingapp.BakingItems;
import com.example.senamit.bakingapp.BakingRecipeNameLoader;
import com.example.senamit.bakingapp.R;
import com.example.senamit.bakingapp.fragmentRecipeStepLoader;

import java.util.List;

/**
 * Created by senamit on 31/1/18.
 */

public class FragmentRecipeSteps extends Fragment implements LoaderManager.LoaderCallbacks<List<BakingItems>>{

    public static final String LOG_TAG = FragmentRecipeSteps.class.getSimpleName();
    Context context;
    TextView txtRecipeIngredient;
    int recipeId;
    String stringUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    public FragmentRecipeSteps() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        context=container.getContext();
         txtRecipeIngredient = (TextView)rootView.findViewById(R.id.txt_recipe_ingredients);
        getLoaderManager().initLoader(1, savedInstanceState, this);

        return  rootView;
    }

    @Override
    public Loader<List<BakingItems>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"inside the init loader");
        return new fragmentRecipeStepLoader(context,stringUrl, recipeId );
    }

    @Override
    public void onLoadFinished(Loader<List<BakingItems>> loader, List<BakingItems> data) {

        String ingredient = data.get(0).getIngredient();
        Log.i(LOG_TAG, "inside the onloadfinished method "+ingredient);
        txtRecipeIngredient.setText(ingredient);


    }

    @Override
    public void onLoaderReset(Loader<List<BakingItems>> loader) {

    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
