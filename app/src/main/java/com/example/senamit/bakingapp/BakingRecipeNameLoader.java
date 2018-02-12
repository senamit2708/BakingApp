package com.example.senamit.bakingapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by senamit on 24/1/18.
 */

public class BakingRecipeNameLoader extends AsyncTaskLoader<List<BakingItems>> {

    List<BakingItems> bakingItems;
    String stringUrl;

    private static final String LOG_TAG = BakingRecipeNameLoader.class.getSimpleName();

    public BakingRecipeNameLoader(Context context, String stringUrl) {
        super(context);
        this.stringUrl = stringUrl;
    }

    @Override
    protected void onStartLoading() {
        if (bakingItems!=null){
            Log.i(LOG_TAG, "inside not null of onstart loading");
            return;
        }
        else {
            forceLoad();
        }

    }

    @Override
    public List<BakingItems> loadInBackground() {
        if (stringUrl == null) {
            return null;
        }
        try {
            bakingItems = QueryUtils.fetchRecipeName(stringUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bakingItems;
    }
}
