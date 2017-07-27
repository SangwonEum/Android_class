package com.example.atest.ch4;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.atest.R;
import com.example.atest.wifi.WifiMonitorReceiver;

public class BReceiverActivity extends  Activity{
	public static final String ACTION = "androidbook.ch04.action.BROADCAST_TEST";
	  
	private BReceiverSample bReceiver = null;
	@Override
	public void onCreate(Bundle savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver_main);
		 
		bReceiver = new BReceiverSample(this);
		 
		//broadcast Reveiver로 등록한다.
		registerReceiver(bReceiver, new IntentFilter(ACTION));
	}
	
	
	
	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(getApplicationContext(), "onPause method 호출 ", Toast.LENGTH_SHORT).show();
	}



	@Override
	protected void onDestroy()	{
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "onDestroy method 호출 ", Toast.LENGTH_SHORT).show();
		unregisterReceiver(bReceiver);
	}
}
