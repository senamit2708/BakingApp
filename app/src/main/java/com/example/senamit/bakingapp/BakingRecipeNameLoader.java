package com.example.senamit.bakingapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by senamit on 24/1/18.
 */

public class BakingRecipeNameLoader extends AsyncTaskLoader {

    List<BakingItems> bakingItems;
    String stringUrl;

    private static final String LOG_TAG = BakingRecipeNameLoader.class.getSimpleName();

    public BakingRecipeNameLoader(Context context, String stringUrl) {
        super(context);
        Log.i(LOG_TAG, "inside the constructor");
        this.stringUrl = stringUrl;
    }

    @Override
    protected void onStartLoading() {
//        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        if (stringUrl==null){
            return null;
        }

        try {
            Log.i(LOG_TAG, "inside the load in backgroound");
            bakingItems = QueryUtils.fetchRecipeName(stringUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return bakingItems;
    }
}
