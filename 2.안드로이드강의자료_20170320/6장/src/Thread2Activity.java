package com.ch7;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Thread2Activity extends Activity {

    private Button mButton;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_main);

        mButton = (Button) findViewById(R.id.Button01);
        textView=(TextView) findViewById(R.id.textView);
    }

    public void threadStart(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            	//thread에서 메인 쓰레드의 텍스트뷰에 직접 접근하면 에러가 발생한다.
            	textView.setText("안녕하세요!!!!");
            }
        });
        thread.start();
    }
}