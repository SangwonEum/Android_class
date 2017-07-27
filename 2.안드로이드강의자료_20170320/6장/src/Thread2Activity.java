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
            	//thread���� ���� �������� �ؽ�Ʈ�信 ���� �����ϸ� ������ �߻��Ѵ�.
            	textView.setText("�ȳ��ϼ���!!!!");
            }
        });
        thread.start();
    }
}