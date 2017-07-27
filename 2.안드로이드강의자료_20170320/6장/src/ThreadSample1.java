package com.ch7;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThreadSample1 extends Activity {
 
    private TextView textView;
    private ProgressBar progressBar;
    Handler handler = new Handler(){
 
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(""+msg.arg1);
            progressBar.setProgress(msg.arg1);
        }
 
    };   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
 
        textView = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
 
    }    
    
    public void threadStart(View view) {
        new Thread(new Runnable() {           
            public void run() {       
                int i = 0;
                while (true) {
                    if(i>100){
                        break;
                    }else{
                        Message msg = handler.obtainMessage();
                        msg.arg1 = i;
                        handler.sendMessage(msg);
                    }                   
                    try {
                        Thread.sleep(1000);                       
                        i+=10;
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();       
 
    }
}