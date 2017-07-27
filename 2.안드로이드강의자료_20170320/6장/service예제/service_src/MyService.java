package com.example.btest.ex2;

import com.example.btest.ex1.TestReceiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    int count;
    boolean flag = true;
   
    @Override
    public void onCreate() {
        super.onCreate();
        count=0;
        Toast.makeText(getBaseContext(),
                        "count="+count+"\nMyService.onCreate()ȣ��",
                        Toast.LENGTH_SHORT).show();
    }
   
    // ���� Ŭ������ ��׶��� ���μ��� ������ ȣ���
    // onCreate -> onStartCommand -> onDestory
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       
        Toast.makeText(getBaseContext(),
                "MyService.onStartCommand()ȣ��",
                Toast.LENGTH_SHORT).show();
       
        Thread thread = new Thread(){
            @Override
            public void run() {
                while(flag){
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
               
        // START_STICKY : � ������ ���񽺰� ���۵��� ���߰ų� �����Ǹ� �ý��ۿ� ���ؼ� ���û ��
        // START_NOT_STICKY : � ������ ���񽺰� ���۵��� ���߰ų� �����Ǹ� ������� ���û�� ������ ���� ������
        return  Service.START_STICKY;
    }
   
    @Override
    public void onDestroy() {
    	
        super.onDestroy();
        flag = false;
        Toast.makeText(getBaseContext(),
                "count="+count+"\nMyService.onDestroy()ȣ��",
                Toast.LENGTH_SHORT).show();
       
    }
    
    // ���� Ŭ���� ���� �޼��� ������ ȣ���� ������ ���ε� ���� ������ ȣ���
    // onCreate -> onBind -> onDestory
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

