package com.example.atest.ch3;

import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


public class EditActivity extends Activity {
    //ȭ���� ���� ��ü�� ������ ������ ���� Ÿ���� ���� ����
	EditText editText;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_view);
        
        //findViewById �޼ҵ带 �̿��ؼ� ȭ���� ���� ��ü�� ������ �´�.
        editText = (EditText)findViewById(R.id.editText);
        editText.setText("Hello, world.");
    }
}


