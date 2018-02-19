package com.example.senamit.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by senamit on 14/2/18.
 */

public class BakingIngredientService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static final String LOG_TAG = BakingIngredientService.class.getSimpleName();
    public static final String widgetUdateKey= "widgetUpdateKey1";
    public static final String UPDATE_RECIPE_INGREDIENTS = "com.example.senamit.action.updateRecipeIngredient";
    public BakingIngredientService() {
        super("BakingIngredientService");
        Log.i(LOG_TAG, "inside the constructor of widget");
    }


    public static void startActionUpdatebakingWidget(Context context, String widgetIngredient){
        Intent intent = new Intent(context, BakingIngredientService.class);
        intent.putExtra(widgetUdateKey, widgetIngredient);
        Log.i(LOG_TAG, "inside the update function of baking widget");

        intent.setAction(UPDATE_RECIPE_INGREDIENTS);
        context.startService(intent);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent!=null){
            final  String action = intent.getAction();
            if (UPDATE_RECIPE_INGREDIENTS.equals(action)){
                Log.i(LOG_TAG, "inside on handle intent function ");
              String widgetIngredient=  intent.getStringExtra(widgetUdateKey);
                handleUpdateRecipeIngredientWidget(widgetIngredient);
            }
        }

    }

    private void handleUpdateRecipeIngredientWidget(String widgetIngredient) {


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));
        BakingAppWidget.updateAppWidget(this, appWidgetManager,widgetIngredient, appWidgetIds);

    }
}
