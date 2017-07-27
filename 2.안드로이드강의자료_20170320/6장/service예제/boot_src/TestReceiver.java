package com.example.btest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class TestReceiver extends BroadcastReceiver 
{ 
   static final String ACTION = "android.intent.action.BOOT_COMPLETED"; 
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 if (intent.getAction().equals(ACTION)) 
	     { 
			  
			 /*Intent i = new Intent(context, MyActivity.class);     
		       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
		      context.startActivity(i);*/   

	          context.startService(new Intent(context, TestService.class));
	         //start my your service here 
	          Toast.makeText(context, "TestService has started!", Toast.LENGTH_LONG).show();
	          
	     } 
	} 
}
