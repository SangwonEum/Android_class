package com.mytest.ch10b;

import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class DrawBoard extends View {
	private String TAG="DrawBoard";
	
	Canvas cacheCanvas;
	Bitmap cacheBitmap;
	final Paint mPaint;
	
	
	int lastX; //이전의 점의 좌표를 기억하는 좌표 변수
	int lastY; 

	public DrawBoard(Context context) {
		super(context);
		
		this.mPaint = new Paint();
		this.mPaint.setColor(Color.BLACK);
		
		this.lastX = -1;
		this.lastY = -1;
		
		Log.d(TAG, "초기화합니다.");
	}

	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		 cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		 cacheCanvas = new Canvas();
		 cacheCanvas.setBitmap(cacheBitmap);
		 cacheCanvas.drawColor(Color.WHITE);
		
		
	}

	protected void onDraw(Canvas canvas) {
		if (cacheBitmap != null) {
			canvas.drawBitmap(cacheBitmap, 0, 0, null);
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		int X = (int) event.getX();
		int Y = (int) event.getY();

		switch (action) {
			case MotionEvent.ACTION_UP:
				//손가락을 뗄을 때 좌표를 초기화한다.
				lastX = -1;
				lastY = -1;
				
				break;
	
			case MotionEvent.ACTION_DOWN:
				// 손가락을 화면에 뎃을 때 이전 좌표에서 터치한 곳까지 선을 그린다.
				if (lastX != -1) {
					if (X != lastX || Y != lastY) {
						cacheCanvas.drawLine(lastX, lastY, X, Y, mPaint);
					}
				}
				
				//방금 터치한 좌표를 이전 좌표로 세팅한다.
				lastX = X;
				lastY = Y;
				
				break;
	
			case MotionEvent.ACTION_MOVE:
				// 화면 드래그 시에 이전 좌표에서 드래그 시 터치한 좌표까지 연속적으로 선을 그린다.
				if (lastX != -1) {
					cacheCanvas.drawLine(lastX, lastY, X, Y, mPaint);
				}
	
				lastX = X;
				lastY = Y;
				
				break;
		}

		invalidate(); //onDraw() 를 호출해서 이미지를 다시 그린다.
		return true;
	}
	
	public boolean Save(OutputStream outstream) {
		try {
			cacheBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
			invalidate(); ////onDraw() 를 호출해서 이미지를 다시 그린다.
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
