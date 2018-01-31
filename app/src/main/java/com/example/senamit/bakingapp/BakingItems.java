package com.example.senamit.bakingapp;

import android.media.Image;

/**
 * Created by senamit on 23/1/18.
 */

public class BakingItems {

    private String recipeName;
    private String recipeImage;
    private int recipeId;

    public BakingItems(String recipeName, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
    }

    public BakingItems(String recipeName, String recipeImage, int recipeId) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
