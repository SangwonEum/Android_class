package com.ch1.ex1;


import com.example.androidprj.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SudokuActivity2 extends Activity {
	Button btn_1;
	int originX=60;
	int originY=60;
	int width=40;
	int divX=-1;
	int divY=-1;
	int curPosX=0; //���� ��ġ�� ��ġ�� x��ǥ
	int curPosY=0;  //���� ��ġ�� ��ġ�� y��ǥ
	int number=0;   //��ư Ŭ���� ���� ����
	
	LinearLayout lay;
	
	boolean isBtnClicked=false;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sudoku_main);
		
		lay=(LinearLayout) findViewById(R.id.lay_main);
		lay.addView(new SdokuView(this));
		
	}
	
	public void btnClicked(View view){
		number=Integer.parseInt((String)view.getTag()); //��ư�� �±װ����� ���ڸ� �����Ѵ�.
		isBtnClicked=true;
		lay.invalidate(); //view��ü�� ������ �ͼ� ȭ���� �����Ѵ�.
	}
	

	private class SdokuView extends View{
		public SdokuView(Context context){
			super(context);
		}
		
		protected void onDraw(Canvas canvas){
			
			super.onDraw(canvas);
			Paint paint=new Paint();
			paint.setAntiAlias(true);
			
			paint.setColor(Color.BLACK);
			paint.setStrokeWidth(5); //�� ���� ����
			
			//���� �׸���..
			for(int i=0;i<10;i++){
				canvas.drawLine(originX, originY+i*width, canvas.getWidth()-originX,originY+i*width,paint);				
			}
			
			//���� �׸���.
			for(int i=0;i<10;i++){
				canvas.drawLine(originX+i*width, originY, originX+i*width,originY+9*width,paint);
			}
			
			if(divX!=-1 && divY!=-1){
				paint.setColor(Color.RED);
				paint.setStrokeWidth(2); //�� ���� ����
				canvas.drawRect(curPosX,curPosY, curPosX+width, curPosY+width, paint) ; 
				
			}
			
			if(isBtnClicked==true){
				paint.setTypeface(null);
				paint.setColor(Color.BLACK);
				paint.setTextSize(25);
				canvas.drawText(Integer.toString(number), curPosX+width/2, curPosY+width/2, paint);
				isBtnClicked=false;
				
			}
		}
		
		public boolean onTouchEvent(MotionEvent event){
			String x=null;
			String y=null;
			
			super.onTouchEvent(event);
			if(MotionEvent.ACTION_DOWN==event.getAction()){
				x=Float.toString(event.getX());
				y=Float.toString(event.getY());
				
				
				divX=(int)(event.getX()-originX)/width;
				divY=(int)(event.getY()-originY)/width;
				curPosX=divX*width+originX;
				curPosY=divY*width+originY;
			//	Toast.makeText(SudokuActivity2.this, "divX:"+divX + " divY ��ǥ:"+divY, Toast.LENGTH_SHORT).show();
				
				invalidate();
				return true;
			}
			return false;
		}
	}
}
