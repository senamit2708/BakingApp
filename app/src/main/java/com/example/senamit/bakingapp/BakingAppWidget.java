package com.example.senamit.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    public static final String appName = "Baking App";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,String name,
                                int appWidgetId) {


        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

       views.setTextViewText(R.id.appwidget_text,name);

        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        views.setOnClickPendingIntent(R.id.widget_image_icon, pendingIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       BakingIngredientService.startActionUpdatebakingWidget(context, appName);
    }


    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,String name,  int[] appWidgetIds){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager,name, appWidgetId);
        }

    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

