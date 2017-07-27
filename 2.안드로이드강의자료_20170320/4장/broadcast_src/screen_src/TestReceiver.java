package com.example.btest.ex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class TestReceiver extends BroadcastReceiver 
{ 
   static final String ACTION1 = "android.intent.action.BOOT_COMPLETED"; 
   static final String ACTION2 = "android.intent.action.SCREEN_ON";
   static final String ACTION3 = "android.intent.action.SCREEN_OFF";
   //
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 if (intent.getAction().equals(ACTION1) ||intent.getAction().equals(ACTION2)
				 ||intent.getAction().equals(ACTION3)){ 
			  
			 /*Intent i = new Intent(context, MyActivity.class);     
		       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
		      context.startActivity(i);*/   

	        //  context.startService(new Intent(context, TestService.class));
	         //start my your service here 
			 Log.d("TAG","TestReceiver is called");
	          Toast.makeText(context, "TestReceiver is called", Toast.LENGTH_LONG).show();
	          
	     } 
	} 
}
