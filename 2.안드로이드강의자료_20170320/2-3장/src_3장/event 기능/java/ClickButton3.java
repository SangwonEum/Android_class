package com.example.atest.ch3;

import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ClickButton3 extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_button);
        Button btn = (Button)findViewById(R.id.btn);
        
        //��ư�� �̺�Ʈ �ڵ鷯�� �����Ѵ�.
        btn.setOnClickListener(new ClickListener());
    }

    //���� Ŭ������ �̿��ؼ� �̺�Ʈ �ڵ鷯�� �����Ѵ�.
     class ClickListener implements View.OnClickListener {
        public void onClick(View v) {
        	Toast.makeText(getApplicationContext(), "Button is Clicked", Toast.LENGTH_LONG).show();
        	//Log.d("TAG","��ư�� Ŭ���߽��ϴ�.");
        }
     }
}


