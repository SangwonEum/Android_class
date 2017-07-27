package com.ch1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.androidprj.R;

public class SudokuActivity2 extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		LinearLayout lay;
		super.onCreate(savedInstanceState);
		setContentView(new SdokuView(this));
		
		
		
	}
	
	private static class SdokuView extends View{
		public SdokuView(Context context){
			super(context);
		}
		
		protected void onDraw(Canvas canvas){
			int originX=60;
			int originY=60;
			int width=40;
			
			
			super.onDraw(canvas);
			Paint paint=new Paint();
			paint.setAntiAlias(true);
			
		//	canvas.drawColor(Color.GREEN);
			
			paint.setColor(Color.BLACK);
			paint.setStrokeWidth(5); //선 굵기 설정
			
			//행을 그린다..
			for(int i=0;i<10;i++){
				canvas.drawLine(originX, originY+i*width, canvas.getWidth()-originX,originY+i*width,paint);				
			}
			
			//열을 그린다.
			for(int i=0;i<10;i++){
				canvas.drawLine(originX+i*width, originY, originX+i*width,originY+9*width,paint);
			}
			
			//숫자를 표시한다.
			paint.setTypeface(null);
			paint.setTextSize(25);
			paint.setColor(Color.RED);
			
			for(int i=1; i<10;i++){
				canvas.drawText(Integer.toString(i),(originX+17)+i*width , originY+20, paint);
			}
			
			/*
			canvas.drawLine(0, 205, canvas.getWidth(),205,paint);
			canvas.drawLine(200,0,200,canvas.getHeight(),paint);
			*/
		}
}
}
