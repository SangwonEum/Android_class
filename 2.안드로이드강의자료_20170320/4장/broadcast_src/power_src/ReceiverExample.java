package com.example.btest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ReceiverExample extends BroadcastReceiver
{
       private String ACTION1 = "android.intent.action.ACTION_POWER_CONNECTED";
       private String ACTION2 = "android.intent.action.ACTION_POWER_DISCONNECTED";
      
       public ReceiverExample(){
    	   Log.d("TAG","생성자 호출");
       }
       @Override
       public void onReceive(Context context, Intent intent)
       {
             if(intent.getAction().equals(ACTION1))
             {
                   Toast.makeText(context,"Be received action aboutACTION_POWER_CONNECTED",1000).show();
             }
             else if(intent.getAction().equals(ACTION2))
             {
                    Toast.makeText(context,"Be received action aboutACTION_POWER_DISCONNECTED",1000).show();
             }
       }
}

