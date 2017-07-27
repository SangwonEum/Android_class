package com.example.atest.ch3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.atest.R;


public class ImageActivity extends Activity implements OnClickListener{ 
    //화면의 위젯 객체를 지정할 위젯과 같은 타입의 변수 선언
    ImageView imageView,imageView1,imageView2;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        
        //findViewById 메소드를 이용해서 화면의 위젯 객체를 가지고 온다.
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		int id=v.getId();
		
		if(id==R.id.imageView1){
			imageView1.setVisibility(View.GONE);
			imageView2.setVisibility(View.VISIBLE);
		}else if(id==R.id.imageView2){
			imageView1.setVisibility(View.VISIBLE);
			imageView2.setVisibility(View.GONE);
		}
		
	}
}


