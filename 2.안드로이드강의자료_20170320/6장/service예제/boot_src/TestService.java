package com.example.btest;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service {
	  @Override
	  public IBinder onBind(Intent intent) {
	    return null;
	  }
	    
	  @Override
	  public void onCreate() {
	    super.onCreate();
	        
	    Log.i("TAG", "Service started at the BOOT_COMPLETED.");
	  }
	  
	  @Override
		public void onStart(Intent intent, int startId) {
			  Toast.makeText(this, "onStart is called", Toast.LENGTH_SHORT).show();
			  Intent i = new Intent(this, MyActivity.class);     
		       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
		      this.startActivity(i);  
		}
}

