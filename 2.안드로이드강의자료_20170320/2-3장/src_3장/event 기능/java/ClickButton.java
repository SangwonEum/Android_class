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
        
        //익명 클래스를 이용해서 이벤트를 처리한다.
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getApplicationContext(), "Button is Clicked", Toast.LENGTH_LONG).show();
            	//Log.d("ClickButton2","버튼을 클릭했습니다.");
            }
        });

    }
}

