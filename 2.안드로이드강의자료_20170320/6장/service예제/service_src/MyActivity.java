package com.example.btest.ex2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.btest.R;

public class MyActivity extends Activity {
	   
    Button btn_start, btn_stop;
    BroadcastReceiver receiver=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
       
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
       
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, MyService.class);
                startService(intent);
            }
        });
       
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, MyService.class);
                stopService(intent);
            }
        });
        
        
    }

    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		try{
			 Intent intent = new Intent(getApplicationContext(), TestReceiver.class);
			 intent.setAction("com.example.btest.send"); // 서비스에서 입력된 Action 값으로 분기 처리
			 PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
			 sender.send();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		super.onPause();
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    
    
    
} 
