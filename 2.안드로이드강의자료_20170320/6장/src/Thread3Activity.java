package com.ch7;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class  Thread3Activity extends Activity {
	private Button mButton;
	 private TextView textView;
    private static final int MESSAGE_NO = 2000;
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
            case MESSAGE_NO: {
            	textView.setText("안녕하세요!!!");
            }
                break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_main);

        mButton = (Button) findViewById(R.id.Button01);
        textView=(TextView) findViewById(R.id.textView1);
    }

    public void threadStart(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain(handler, MESSAGE_NO);
                handler.sendMessage(message); //메인쓰레드로 메시지를 전달한다.
            }
        });
        thread.start();
    }
}