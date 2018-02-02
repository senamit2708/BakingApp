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

public class fragmentRecipeStepLoader extends AsyncTaskLoader {

    private static final String LOG_TAG = fragmentRecipeStepLoader.class.getSimpleName();

    List<BakingItems> bakingItems;
    int recipeId;
    String stringUrl;

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
//        super.onStartLoading();
        if (bakingItems!=null){
            return;
        }
        else {
            forceLoad();
        }

    }

    @Override
    public Object loadInBackground() {
//        return null;
        if (stringUrl==null){
            return null;
        }
        try {
            Log.i(LOG_TAG, "inside the load in backgroound");
            bakingItems = QueryUtils.fetchRecipeStep(stringUrl, recipeId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return bakingItems;



    }
}
