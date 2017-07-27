package com.example.atest.ch4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BReceiverSample extends BroadcastReceiver {
    public static final String ACTION = "androidbook.ch04.action.BROADCAST_TEST";

    public BReceiverSample(Context context){
    	//초기화 수행
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
        	String name=intent.getStringExtra("name");
        	int age=intent.getIntExtra("age", 0);
        	
            Toast.makeText(context, "Broadcast received", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "name: "+name +" , age : "+age, Toast.LENGTH_SHORT).show();
            
        }
    }
}


