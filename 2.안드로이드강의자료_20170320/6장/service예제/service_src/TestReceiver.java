package com.example.btest.ex2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class TestReceiver extends BroadcastReceiver 
{ 
   //
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action=intent.getAction();
		
		Toast.makeText(context, "TestReceiver is called", Toast.LENGTH_LONG).show();
		  if (intent.getAction().equals(action)) {
	            Intent i = new Intent(context, MyService.class);
	            context.startService(i);
	            
	        }
		   
	} 
}
