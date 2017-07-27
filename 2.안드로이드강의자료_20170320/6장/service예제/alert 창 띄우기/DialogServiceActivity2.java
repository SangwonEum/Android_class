package com.example.atest.ex1;



import com.example.atest.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DialogServiceActivity2 extends Activity {
	private DialogService service;
	private ServiceConnection mConnection=new ServiceConnection(){
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			service=((DialogService.ServiceBinder) binder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			service=null;
			//service.
		}

		
	};

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_alert);
	}
	
	public void onStartService(View view){
		if(service==null){
			Intent intent=new Intent(this,DialogService.class);
			bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
			
		}
	}
	public void onStopService(View view){
		if(service !=null) {
			unbindService(mConnection);
			service=null;
		}
		
		
	}
	
	public void getAlert(View view){
		//Log.d("TAG",service.toString());
		if(service !=null){
			service.getAlertDialog();
		}
	}
}
