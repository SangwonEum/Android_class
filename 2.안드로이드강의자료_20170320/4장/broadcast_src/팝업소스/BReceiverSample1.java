package com.example.atest.ch4;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BReceiverSample1 extends BroadcastReceiver {
    public static final String ACTION = "androidbook.ch04.action.BROADCAST_TEST";

    //�޴��佺Ʈ���� ��Ͻ� ȣ��Ǵ� ������
    public BReceiverSample1(){
    	//�ʱ�ȭ ����
    }
    
    public BReceiverSample1(Context context){
    	//�ʱ�ȭ ����
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
        	String name=intent.getStringExtra("name");
        	int age=intent.getIntExtra("age", 0);
        	
           // Toast.makeText(context, "Broadcast received", Toast.LENGTH_SHORT).show();
           // Toast.makeText(context, "name: "+name +" , age : "+age, Toast.LENGTH_SHORT).show();
        	
        	 
        	 Intent i = new Intent( context, TempActivity.class );
        	 i.putExtra("name", name);
        	 i.putExtra("age", age);
        	 
        	 PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_ONE_SHOT);
        	 try {
        		 pi.send();
        	 } catch (CanceledException e) {
        		 e.printStackTrace();
        	 }
        }
    }
}


