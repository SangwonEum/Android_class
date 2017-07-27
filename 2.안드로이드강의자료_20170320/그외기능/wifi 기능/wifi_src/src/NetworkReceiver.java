package com.example.btest.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {
	final String TAG="NetworkReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action=intent.getAction();
		Log.d(TAG,"action = "+action);
		
		if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION) 
							||action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
			int iResult=getConnectivityStatus(context);
			Log.d(TAG,"iResult= "+iResult);
			if(iResult==2){
				Toast.makeText(context, "Mobile Connect" ,Toast.LENGTH_SHORT).show();
			}else if(iResult ==1){
				Toast.makeText(context, "Wifi Connect" ,Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context, "Not Connect" ,Toast.LENGTH_SHORT).show();
			}
		}else
			return ;
		
	}
	
	public int getConnectivityStatus(Context context){
		ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
		
		if(activeNetwork != null){
			if(activeNetwork.getType()==ConnectivityManager.TYPE_WIFI){
				if(activeNetwork.getState() ==NetworkInfo.State.CONNECTED){
					return 1;
				}else if(activeNetwork.getState() == NetworkInfo.State.CONNECTING){
					Log.d(TAG,"Wifi Connecting");
				}else if(activeNetwork.getState()== NetworkInfo.State.DISCONNECTED){
					Log.d(TAG,"Wifi DisConnected");
				}else if(activeNetwork.getState()== NetworkInfo.State.DISCONNECTING){
					Log.d(TAG,"Wifi DisConnecting");
				}else if(activeNetwork.getState()== NetworkInfo.State.SUSPENDED){
					Log.d(TAG,"Wifi SUSPENDED");
				}else if(activeNetwork.getState()== NetworkInfo.State.UNKNOWN){
					Log.d(TAG,"Wifi Unknown");
				}
				return 1;
			}
			
			if(activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE){
				if(activeNetwork.getState() ==NetworkInfo.State.CONNECTED){
					Log.d(TAG,"Mobile Connected");
				}else if(activeNetwork.getState() == NetworkInfo.State.CONNECTING){
					Log.d(TAG,"Mobile Connecting");
				}else if(activeNetwork.getState()== NetworkInfo.State.DISCONNECTED){
					Log.d(TAG,"Mobile DisConnected");
				}else if(activeNetwork.getState()== NetworkInfo.State.DISCONNECTING){
					Log.d(TAG,"Mobile DisConnecting");
				}else if(activeNetwork.getState()== NetworkInfo.State.SUSPENDED){
					Log.d(TAG,"Mobile SUSPENDED");
				}else if(activeNetwork.getState()== NetworkInfo.State.UNKNOWN){
					Log.d(TAG,"Mobile Unknown");
				}
				return 2;
			}
		}else{
		
		}
		return 3;
	}
	
}
