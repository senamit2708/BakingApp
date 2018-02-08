package com.example.senamit.bakingapp;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by senamit on 23/1/18.
 */

public class BakingItems implements Parcelable{

    private String recipeName;
    private String recipeImage;
    private int recipeId;
    String ingredient;
    int quantity;
    String measure;
    String shortDiscription;
    int stepId;
    String description;
    String thumbnailURL;
    String videoURL;


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

    public BakingItems(String shortDiscription, int stepId, String description, String thumbnailURL, String videoURL) {
        this.shortDiscription = shortDiscription;
        this.stepId = stepId;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.videoURL = videoURL;
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

    public String getDescription() {
        return description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    protected BakingItems(Parcel in) {
        recipeName = in.readString();
        recipeImage = in.readString();
        recipeId = in.readInt();
        ingredient = in.readString();
        quantity = in.readInt();
        measure = in.readString();
        shortDiscription = in.readString();
        stepId = in.readInt();
        description = in.readString();
        thumbnailURL = in.readString();
        videoURL = in.readString();
    }

    public static final Creator<BakingItems> CREATOR = new Creator<BakingItems>() {
        @Override
        public BakingItems createFromParcel(Parcel in) {
            return new BakingItems(in);
        }

        @Override
        public BakingItems[] newArray(int size) {
            return new BakingItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeName);
        dest.writeString(recipeImage);
        dest.writeInt(recipeId);
        dest.writeString(ingredient);
        dest.writeInt(quantity);
        dest.writeString(measure);
        dest.writeString(shortDiscription);
        dest.writeInt(stepId);
        dest.writeString(description);
        dest.writeString(thumbnailURL);
        dest.writeString(videoURL);
    }
}
