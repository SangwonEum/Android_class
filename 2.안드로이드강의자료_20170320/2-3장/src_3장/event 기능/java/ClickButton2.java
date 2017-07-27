package com.example.atest.ch3;


import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ClickButton2 extends Activity implements OnClickListener{ 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_button);
        Button btn = (Button)findViewById(R.id.btn);

        //����(View Ŭ����)�� �����ʸ� �����Ѵ�.
        btn.setOnClickListener(this);
    }
	
	//�������̽��� �����ؼ� �̺�Ʈ�� ó���Ѵ�.
	@Override
	public void onClick(View view) {
		Toast.makeText(getApplicationContext(), "Button is Clicked", Toast.LENGTH_LONG).show();
		//Log.d("ClickButton2","��ư�� Ŭ���߽��ϴ�.");
	}
}


