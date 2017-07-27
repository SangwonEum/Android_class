package com.example.atest.ch3;

import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ClickButton4 extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_button);
        
    }

    public void onButtonClick(View v) {
    	Toast.makeText(getApplicationContext(), "Button is Clicked", Toast.LENGTH_LONG).show();
        ((Button)v).setText("클릭 이벤트가 발생했음");
    }
}


