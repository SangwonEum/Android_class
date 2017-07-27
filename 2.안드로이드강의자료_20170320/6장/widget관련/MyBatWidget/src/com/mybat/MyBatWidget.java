package com.mybat;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBatWidget extends AppWidgetProvider {
	Context mContext=null;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		mContext=context;
		Intent intent_battery = new Intent(context , BatteryService.class);
		context.startService(intent_battery);
	}

	public void onDeleted(Context context){
		Intent intent_battery = new Intent(context , BatteryService.class);
		context.stopService(intent_battery);
	}
	
}

