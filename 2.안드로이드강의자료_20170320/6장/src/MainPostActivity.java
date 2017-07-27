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
		//�����忡�� Handler�� �̿��Ͽ� UI �����忡�� ������ �ڵ带 �����Ѵ�
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

