package com.example.senamit.bakingapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by senamit on 24/1/18.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static ArrayList<BakingItems> bakingItems;

    public static URL createUrl(String stringUrl) throws MalformedURLException {
        if (stringUrl==null){
            return null;
        }
        URL url = null;
        url = new URL(stringUrl);
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        if (url == null) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.i(LOG_TAG, "the urlConnection is bad");
                return null;
            }
        } catch (IOException e) {
            Log.i(LOG_TAG, "the  connection is interuptrd " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<BakingItems> extractFeatureJSONReceipeName(String jsonResponse) throws JSONException {

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        String recipeName;
        String recipeImage;
        int recipeId;

        JSONArray baseJsonArray = null;

        ArrayList<BakingItems> bakingItems = new ArrayList<>();
        try{
             baseJsonArray = new JSONArray(jsonResponse);
        }
        catch(Exception e){
            Log.e(LOG_TAG, "inisde the catch block of json result");
        }
      
        for (int i=0; i<baseJsonArray.length(); i++){

            JSONObject jsonObject = baseJsonArray.optJSONObject(i);
            recipeName=jsonObject.optString("name");
            recipeImage = jsonObject.optString("image");
            recipeId = jsonObject.optInt("id");
            Log.i(LOG_TAG, "the recipe id is "+recipeId);

            if (TextUtils.isEmpty(recipeImage)){
                bakingItems.add(new BakingItems(recipeName, "no Image", recipeId));
            }
            else{
                bakingItems.add(new BakingItems(recipeName, recipeImage, recipeId));
            }
        }
        return bakingItems;

    }

    public static ArrayList<BakingItems> fetchRecipeName(String stringUrl) throws IOException, JSONException {
        Log.i(LOG_TAG, "the fetchRecipename 1");
        URL url = createUrl(stringUrl);
        Log.i(LOG_TAG, "the url is2   " + url);
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);
        bakingItems = extractFeatureJSONReceipeName(jsonResponse);
        return bakingItems;
    }

    public static ArrayList<BakingItems> extractFeatureJSONReceipeIngredient(String jsonResponse, int recipeId) throws JSONException {

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        String ingredient;
        int quantity;
        String measure;
        int arrayIndex=recipeId-1;

        JSONArray baseJsonArray = null;
        ArrayList<BakingItems> bakingItems = new ArrayList<>();
        baseJsonArray = new JSONArray(jsonResponse);

            JSONObject jsonObject = baseJsonArray.optJSONObject(arrayIndex);
            JSONArray jsonIngredientArray = jsonObject.optJSONArray("ingredients");
            Log.i(LOG_TAG, "the size of ingredients are "+jsonIngredientArray.length());
            for (int i=0; i<jsonIngredientArray.length();i++){
                JSONObject jsonIngredientObject = jsonIngredientArray.optJSONObject(i);
                quantity = jsonIngredientObject.optInt("quantity");
                measure = jsonIngredientObject.optString("measure");
                ingredient = jsonIngredientObject.optString("ingredient");

                Log.i(LOG_TAG, "the ingredients are "+ ingredient);
                bakingItems.add(new BakingItems(ingredient, quantity, measure));

            }

        return bakingItems;

    }

    public static ArrayList<BakingItems> fetchRecipeIngredient(String stringUrl, int recipeId) throws IOException, JSONException {

        Log.i(LOG_TAG, "the fetchRecipename 1");
        URL url = createUrl(stringUrl);
        Log.i(LOG_TAG, "the url is2   " + url);
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);
        Log.i(LOG_TAG, "the jsonresponse i got");
        bakingItems = extractFeatureJSONReceipeIngredient(jsonResponse,recipeId);
        return bakingItems;

    }

    public static ArrayList<BakingItems> extractFeatureJSONReceipeSteps(String jsonResponse, int recipeId) throws JSONException {

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }

        int arrayIndex=recipeId-1;
        String stepShortDescription;
        int stepId;

        JSONArray baseJsonArray = null;
        ArrayList<BakingItems> bakingItems = new ArrayList<>();
        baseJsonArray = new JSONArray(jsonResponse);

        JSONObject jsonObject = baseJsonArray.optJSONObject(arrayIndex);
        JSONArray jsonIngredientArray = jsonObject.optJSONArray("ingredients");
        Log.i(LOG_TAG, "the size of ingredients are "+jsonIngredientArray.length());

            JSONArray jsonStepArray = jsonObject.optJSONArray("steps");
        for (int i=0; i< jsonStepArray.length(); i++){
            JSONObject jsonStepObject = jsonStepArray.optJSONObject(i);
            stepShortDescription = jsonStepObject.optString("shortDescription");
            stepId = jsonStepObject.optInt("id");

            Log.i(LOG_TAG, "the short description "+stepShortDescription);
            bakingItems.add(new BakingItems(stepShortDescription, stepId));
        }


        return bakingItems;

    }

    public static ArrayList<BakingItems> fetchRecipeStep(String stringUrl, int recipeId) throws IOException, JSONException {

        Log.i(LOG_TAG, "the fetchRecipename 1");
        URL url = createUrl(stringUrl);
        Log.i(LOG_TAG, "the url is2   " + url);
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);
        Log.i(LOG_TAG, "the jsonresponse i got");
        bakingItems = extractFeatureJSONReceipeSteps(jsonResponse,recipeId);
        return bakingItems;

    }

}
