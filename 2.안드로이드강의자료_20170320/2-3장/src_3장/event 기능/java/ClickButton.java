package com.example.atest.ch3;


import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ClickButton extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_button);
        Button btn = (Button)findViewById(R.id.btn);
        
        //�͸� Ŭ������ �̿��ؼ� �̺�Ʈ�� ó���Ѵ�.
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getApplicationContext(), "Button is Clicked", Toast.LENGTH_LONG).show();
            	//Log.d("ClickButton2","��ư�� Ŭ���߽��ϴ�.");
            }
        });

    }
}

