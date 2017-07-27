package com.mytest.ch6;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mytest.R;

public class  ThreadImageActivity extends Activity {
	private Button mButton;
	private ImageView imageView1;
	private int index=0;
	 
    private static final int MESSAGE_NO = 2000;
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
            case MESSAGE_NO: {
            	if(index==0){
            		imageView1.setImageResource(R.drawable.green);
            		index=1;
            	}else{
            		imageView1.setImageResource(R.drawable.tulip);
            		index=0;
            	}
            	
            	Bundle bundle= message.getData();
            	int i=bundle.getInt("id");
            	Log.d("TAG","i = "+i);
            }
                break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_image);

        mButton = (Button) findViewById(R.id.Button01);
        imageView1=(ImageView) findViewById(R.id.imageView1);
    }

    public void threadStart(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            	for(int i=0; i<=10;i++){
            		
            		try {
						Thread.sleep(1000); //1초 동안 쓰레드 실행을 중지한다.
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
            		
            		Message message = Message.obtain(handler, MESSAGE_NO);
            		//Message 객체에 데이터를 넣어서 전달할 수 있다.
            		Bundle bundle=new Bundle();
            		bundle.putInt("id", i);
            		message.setData(bundle);
            		handler.sendMessage(message); //메인쓰레드로 메시지를 전달한다.
            	}
            }
        });
        thread.start();
    }
}

