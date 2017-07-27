package com.mytest.ch3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mytest.R;

public class ImageActivity2 extends Activity {
	ImageView imageView1;
	Button btn1,btn2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_layout2);
		
		imageView1=(ImageView) findViewById(R.id.imageView1);
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
	}
	
	//버튼 클릭시 이벤트 처리 메소드
	public void onButtonClicked(View v){
		int btnId=v.getId();
		switch(btnId){
		case R.id.button1:
			imageView1.setImageResource(R.drawable.tulip);
			break;
		case R.id.button2:
			imageView1.setImageResource(R.drawable.green);
			break;
		}
				
	}
}
