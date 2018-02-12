package com.example.senamit.bakingapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by senamit on 31/1/18.
 */

public class fragmentRecipeStepLoader extends AsyncTaskLoader<List<BakingItems>> {

    private static final String LOG_TAG = fragmentRecipeStepLoader.class.getSimpleName();
    private List<BakingItems> bakingItems;
    private int recipeId;
    private String stringUrl;

    public fragmentRecipeStepLoader(Context context, String stringUrl) {
        super(context);
        this.stringUrl = stringUrl;
    }

    public fragmentRecipeStepLoader(Context context, String stringUrl, int recipeId) {
        super(context);
        this.recipeId = recipeId;
        this.stringUrl = stringUrl;
    }

    @Override
    protected void onStartLoading() {
        if (bakingItems != null) {
            deliverResult(bakingItems);
        } else {
            super.forceLoad();
        }
    }

    @Override
    public List<BakingItems> loadInBackground() {
        if (stringUrl == null) {
            return null;
        }
        try {
            bakingItems = QueryUtils.fetchRecipeStep(stringUrl, recipeId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bakingItems;
    }

    @Override
    public void deliverResult(List<BakingItems> data) {
        bakingItems = data;
        super.deliverResult(data);
    }
}
