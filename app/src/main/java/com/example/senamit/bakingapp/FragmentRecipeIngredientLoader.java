package com.example.senamit.bakingapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by senamit on 1/2/18.
 */

public class FragmentRecipeIngredientLoader extends AsyncTaskLoader {

    private List<BakingItems> bakingItems;
    private int recipeId;
    private String stringUrl;

    public FragmentRecipeIngredientLoader(Context context, int recipeId, String stringUrl) {
        super(context);
        this.recipeId = recipeId;
        this.stringUrl = stringUrl;
    }

    @Override
    protected void onStartLoading() {
        if (bakingItems != null) {
            return;
        } else {
            forceLoad();
        }
    }

    @Override
    public Object loadInBackground() {
        try {
            bakingItems = QueryUtils.fetchRecipeIngredient(stringUrl, recipeId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bakingItems;
    }
}
