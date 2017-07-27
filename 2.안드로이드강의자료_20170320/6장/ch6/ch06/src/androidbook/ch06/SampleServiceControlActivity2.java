package com.mytest.ch6;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.mytest.R;

public class SampleServiceControlActivity2  extends Activity{
	private SampleService service;
	private ServiceConnection mConnection=new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			service= ((SampleService.SampleBinder) binder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			service=null;
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service2);
	}
	
	public void onStartService(View view){
		if(service==null){
			Intent intent=new Intent(this,SampleService.class);
			bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
		}
	}
	
	public void onStopService(View view){
		if(service !=null) {
			unbindService(mConnection);
			service=null;
		}
	}
	
	public void onGetMessage(View view){
		if(service !=null){
			String message=service.getMessage();
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}
	

}
