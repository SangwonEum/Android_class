package com.example.atest.ch3;


import com.example.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ClickButton5 extends Activity implements OnClickListener{
	Button btn,btn1,btn2,btn3;
	String tag=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_button);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);

        //����(View Ŭ����)�� �����ʸ� �����Ѵ�.
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }
	
	//�������̽��� �����ؼ� �̺�Ʈ�� ó���Ѵ�.
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		btn=(Button)findViewById(v.getId());
		tag=(String)btn.getTag();
		  switch(v.getId()) {
		  case R.id.btn1:
			  Toast.makeText(getApplicationContext(), tag+"is Clicked", Toast.LENGTH_LONG).show();
		   break;
		  case R.id.btn2:
			  Toast.makeText(getApplicationContext(), tag+"is Clicked", Toast.LENGTH_LONG).show();
		   break;
		   
		  case R.id.btn3:
			  Toast.makeText(getApplicationContext(), tag+"is Clicked", Toast.LENGTH_LONG).show();
		  break;

		 }
	}
}


