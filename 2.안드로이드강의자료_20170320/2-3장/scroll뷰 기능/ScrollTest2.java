package com.ch1.ex2;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.androidprj.R;

public class ScrollTest2 extends Activity implements OnClickListener{
	private ScrollView scrollView;
	private ImageView imageView;
	private Button btn1;
	private Button btn2;
	private boolean toggle=false;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_test2);
		
		//뷰 객체 생성
		scrollView=(ScrollView) findViewById(R.id.scroll);
		imageView=(ImageView) findViewById(R.id.image);
		btn1=(Button) findViewById(R.id.btn1);
		btn2=(Button) findViewById(R.id.btn2);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		
		//수평 스크롤바 사용 기능 설정
		scrollView.setHorizontalScrollBarEnabled(true);
		
		//resource 이미지 참조
		Resources res=getResources(); //getResources() 메소드를 이용해서 Bitmap이미지를 가지고 온다.
		BitmapDrawable bitmap=(BitmapDrawable) res.getDrawable(R.drawable.bluejay);
		int bWidth=bitmap.getIntrinsicWidth();  //원본 이미지의 가로 길이
		int bHeight=bitmap.getIntrinsicHeight();  //원본 이미지의 세로 길이
		
		//이미지를 레이아웃에 설정한다.
		imageView.setImageDrawable(bitmap);
		imageView.getLayoutParams().width=bWidth;
		imageView.getLayoutParams().height=bHeight;
		
		
		/*//버튼 클릭시 이미지 변경
		btn1.setOnClickListener(new OnClickListener(){
			BitmapDrawable bitmap=null;
			@Override
			public void onClick(View v) {
				Resources res=getResources();
				if(toggle==false){
					bitmap=(BitmapDrawable) res.getDrawable(R.drawable.desert);
					toggle=true;
				}else{
					bitmap=(BitmapDrawable) res.getDrawable(R.drawable.bluejay);
					toggle=false;
				}
				int bWidth=bitmap.getIntrinsicWidth();
				int bHeight=bitmap.getIntrinsicHeight();
				
				//바뀐 이미지를 레이아웃에 설정한다.
				imageView.setImageDrawable(bitmap);
				imageView.getLayoutParams().width=bWidth;
				imageView.getLayoutParams().height=bHeight;
			}
			
		});
		*/
		
	}

	@Override
	public void onClick(View v) {
		Log.d("TAG","button is clicked");
		BitmapDrawable bitmap=null;
		Resources res=getResources();
		int id= v.getId();
		if(id==R.id.btn1){
			bitmap=(BitmapDrawable) res.getDrawable(R.drawable.desert);
		}else{
			bitmap=(BitmapDrawable) res.getDrawable(R.drawable.bluejay);
		}
		int bWidth=bitmap.getIntrinsicWidth();
		int bHeight=bitmap.getIntrinsicHeight();
		//바뀐 이미지를 레이아웃에 설정한다.
		imageView.setImageDrawable(bitmap);
		imageView.getLayoutParams().width=bWidth;
		imageView.getLayoutParams().height=bHeight;
	
		
	}

}
