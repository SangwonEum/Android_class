package com.mytest2.ch3;


import com.mytest2.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BaseBallActivity  extends Activity{
	ImageView imgBall1,imgBall2,imgBall3;
	ImageView imgStrike1,imgStrike2,imgStrike3;
	//ImageView img[] =new ImageView[3];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseball_layout);
		init();
	}
	private void init(){
		//������ �̹��� ��ü�� ������ �´�.
		imgBall1=(ImageView) findViewById(R.id.imgBall1);
		imgBall2=(ImageView) findViewById(R.id.imgBall2);
		imgBall3=(ImageView) findViewById(R.id.imgBall3);
		
		imgStrike1=(ImageView) findViewById(R.id.imgStrike1);
		imgStrike2=(ImageView) findViewById(R.id.imgStrike2);
		imgStrike3=(ImageView) findViewById(R.id.imgStrike3);

	}
	
	public void onBtnCompareClicked(View v){
		//����ڰ� �Է��� ���� ���� ������ �� ���ϱ�
		
		//2ball,1strike �� ���
		imgBall1.setImageResource(R.drawable.ball);
		imgBall2.setImageResource(R.drawable.ball);
		imgStrike1.setImageResource(R.drawable.strike);
		
	}
	
	public void onBtnFinish(){
		finish();
	}
}
