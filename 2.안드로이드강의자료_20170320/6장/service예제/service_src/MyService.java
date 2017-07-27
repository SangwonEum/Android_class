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
                        "count="+count+"\nMyService.onCreate()호출",
                        Toast.LENGTH_SHORT).show();
    }
   
    // 서비스 클래스가 백그라운드 프로세스 구현시 호출됨
    // onCreate -> onStartCommand -> onDestory
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       
        Toast.makeText(getBaseContext(),
                "MyService.onStartCommand()호출",
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
               
        // START_STICKY : 어떤 문제로 서비스가 시작되지 못했거나 중지되면 시스템에 의해서 재요청 됨
        // START_NOT_STICKY : 어떤 문제로 서비스가 시작되지 못했거나 중지되면 사용자의 재요청이 있을때 까지 중지됨
        return  Service.START_STICKY;
    }
   
    @Override
    public void onDestroy() {
    	
        super.onDestroy();
        flag = false;
        Toast.makeText(getBaseContext(),
                "count="+count+"\nMyService.onDestroy()호출",
                Toast.LENGTH_SHORT).show();
       
    }
    
    // 서비스 클래스 안의 메서드 단위의 호출이 가능한 바인딩 서비스 구현시 호출됨
    // onCreate -> onBind -> onDestory
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

