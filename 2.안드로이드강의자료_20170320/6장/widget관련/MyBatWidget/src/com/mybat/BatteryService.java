package com.mybat;


import com.example.mybat.R;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.RemoteViews;

public class BatteryService extends Service {
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		IntentFilter filter = new IntentFilter();
		// 배터리 변화에 대한 액션 등록
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		// 배터리 BroadcastReceiver 등록
		registerReceiver(mBRBattery, filter);
		return START_STICKY;
	}
	
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBRBattery);
	}
	
	BroadcastReceiver mBRBattery = new BroadcastReceiver() {
		public void onReceive(Context context , Intent intent) {
			String action = intent.getAction();
			if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
				int current , max , percent;
				current = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,100);
				max = intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
				percent = current * 100 / max;

				RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
				views.setTextViewText(R.id.battery,"" + percent + "%");
				AppWidgetManager wm = AppWidgetManager.getInstance(BatteryService.this);
				ComponentName widget = new ComponentName(context, MyBatWidget.class);
				wm.updateAppWidget(widget,views);
			}
		}
	};
}