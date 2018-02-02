package com.example.senamit.bakingapp;

import android.media.Image;

/**
 * Created by senamit on 23/1/18.
 */

public class BakingItems {

    private String recipeName;
    private String recipeImage;
    private int recipeId;
    String ingredient;
    int quantity;
    String measure;
    String shortDiscription;
    int stepId;


    public BakingItems(String recipeName, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
    }

    public BakingItems(String recipeName, String recipeImage, int recipeId) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.recipeId = recipeId;
    }

    public BakingItems(String ingredient, int quantity, String measure) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measure = measure;
    }

    public BakingItems(String shortDiscription, int stepId) {
        this.shortDiscription = shortDiscription;
        this.stepId = stepId;
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

    public String getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getShortDiscription() {
        return shortDiscription;
    }

    public int getStepId() {
        return stepId;
    }
}
