package com.mytest.ch6;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.mytest2.R;


public class MainPostActivity extends Activity {
	TextView tv;
	int num;
	Handler handler; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_main);
		tv = (TextView) findViewById(R.id.textView1);
		handler = new Handler();
		//쓰레드에서 Handler를 이용하여 UI 쓰레드에게 실행할 코드를 전달한다
		new Thread() { 
			public void run() {
				for(int i=0;i<10;i++) {
					handler.post( new Runnable() {
						public void run() {
							tv.setText(tv.getText().toString()+(++num));
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) { e.printStackTrace();
					}
				}
			};
		}.start();
	}
}

