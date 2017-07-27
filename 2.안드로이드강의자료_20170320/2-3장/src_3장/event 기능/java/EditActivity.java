package com.example.atest.ch3;

import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


public class EditActivity extends Activity {
    //화면의 위젯 객체를 지정할 위젯과 같은 타입의 변수 선언
	EditText editText;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_view);
        
        //findViewById 메소드를 이용해서 화면의 위젯 객체를 가지고 온다.
        editText = (EditText)findViewById(R.id.editText);
        editText.setText("Hello, world.");
    }
}


