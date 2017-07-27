package com.example.atest.ch3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.atest.R;

public class FrameLayoutActivity  extends Activity{
	LinearLayout layout1;
	Button btn1;
	int index=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout);

		//LinearLayout 객체를 가지고 온다.
		layout1=(LinearLayout) findViewById(R.id.layout1);
		btn1=(Button) findViewById(R.id.btn1);
		
		btn1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(index==0){
					//layout 전체를 보이지 않게 한다.
					layout1.setVisibility(View.GONE);
					index=1;
				}else{
					layout1.setVisibility(View.VISIBLE);
					index=0;
				}
			}
		});
	}
}


