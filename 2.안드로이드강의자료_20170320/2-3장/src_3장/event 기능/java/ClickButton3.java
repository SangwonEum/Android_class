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
        
        //버튼과 이벤트 핸들러를 연결한다.
        btn.setOnClickListener(new ClickListener());
    }

    //내부 클래스를 이용해서 이벤트 핸들러를 구현한다.
     class ClickListener implements View.OnClickListener {
        public void onClick(View v) {
        	Toast.makeText(getApplicationContext(), "Button is Clicked", Toast.LENGTH_LONG).show();
        	//Log.d("TAG","버튼을 클릭했습니다.");
        }
     }
}


