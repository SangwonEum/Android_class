package com.mywidget;

import com.example.mywidget.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	@Override
	public void onReceive(Context context, Intent intent) {
	super.onReceive(context, intent);
	}
	 
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		for(int i = 0; i < N; i++){
		int appWidgetId = appWidgetIds[i];
		Intent intent = new Intent(context, MyActivity.class);
		PendingIntent pi = PendingIntent.getActivity(
		context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		views.setOnClickPendingIntent(R.id.lay, pi);
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	}
}
