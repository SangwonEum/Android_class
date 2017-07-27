package com.example.atest.ex1;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class DialogService extends Service {

    private final IBinder binder = new ServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Toast.makeText(this, "onRebind", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();
        return binder;
    }

    public void  getAlertDialog() {
    	String text="Alert message";
    	
    	Bundle bun = new Bundle();
    	bun.putString("notiMessage", text);

    	Intent popupIntent = new Intent(getApplicationContext(), AlertDialog.class);

    	popupIntent.putExtras(bun);
    	PendingIntent pie= PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
    	try {
    	 pie.send();
    	} catch (CanceledException e) {
    		e.printStackTrace();
    	}
    }

    public class ServiceBinder extends Binder {
        DialogService getService() {
            return DialogService.this;
        }
    };
}