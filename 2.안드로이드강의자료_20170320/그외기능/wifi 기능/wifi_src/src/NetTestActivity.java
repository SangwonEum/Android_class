package com.example.btest.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.btest.R;

public class NetTestActivity extends Activity{
	
	final String TAG="NetTestActivity";
	public Button btn_netInfo=null;
	public TextView t_netInfo=null;
	public ConnectivityManager cm=null;
	public WifiManager wifiManager=null;
	

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_main);
		
		btn_netInfo=(Button) findViewById(R.id.btn_netInfo);
		t_netInfo=(TextView) findViewById(R.id.t_netInfo);
		
	
	
		btn_netInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
				wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
				
				NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
				String SSID=null;
				String netType=null;
				int networkType=activeNetwork.getType();
				if(networkType==cm.TYPE_WIFI){
					SSID=wifiManager.getConnectionInfo().getSSID();
					netType="WIFI";
				}else if(networkType==cm.TYPE_MOBILE){
					SSID=activeNetwork.getExtraInfo();
					netType="Mobile";
				}else{
					netType="None";
				}
				t_netInfo.setText("Name : "+SSID + " , networkType : "+netType);
			}
		});
		
	}
}
