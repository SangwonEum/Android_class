package com.mytest.ch10a;

import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawBoardSurface extends SurfaceView implements SurfaceHolder.Callback {
	private String TAG="DrawBoardSurface";
	
	Canvas cacheCanvas;
	Bitmap cacheBitmap;
	
	final Paint mPaint;
	int lastX;
	
	int lastY;

	SurfaceHolder mHolder;
	
	public DrawBoardSurface(Context context) {
		super(context);
		
		mHolder = getHolder();   
		mHolder.addCallback(this); 
		
		
		this.mPaint = new Paint();
		this.mPaint.setColor(Color.RED);
		
		this.lastX = -1;
		this.lastY = -1;
		
		Log.d(TAG, "initialized.");
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
 
		int X = (int) event.getX();
		int Y = (int) event.getY();

		switch (action) {
			case MotionEvent.ACTION_UP:
				//좌표를 초기화 한다.
				lastX = -1;
				lastY = -1;
				
				break;
	
			case MotionEvent.ACTION_DOWN:
				// 화면 터치 시 기존 좌표에서 터치한 좌표로 선을 그린다.
				if (lastX != -1) {
					if (X != lastX || Y != lastY) {
						cacheCanvas.drawLine(lastX, lastY, X, Y, mPaint);
						 
					}
				}
				
				//터치한 좌표를 이전 좌표로 세팅한다.
				lastX = X;
				lastY = Y;
				
				break;
	
			case MotionEvent.ACTION_MOVE:
				// 화면 드래그 시 드래그 한 위치로 선을 그린다.
				if (lastX != -1) {
					cacheCanvas.drawLine(lastX, lastY, X, Y, mPaint);
					 
				}
	
				lastX = X;
				lastY = Y;
				
				break;
		}

	 
		Log.d(TAG, "repaintCanvas()");
		
		// 화면을 갱신한다.
		draw();
		
		return true;
	}

	//서피스 뷰 생성 시 호출되는 콜백 메소드
	public void surfaceCreated(SurfaceHolder holder) { 
		int w = getWidth();
		int h = getHeight();
		
		cacheBitmap= Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		 cacheCanvas = new Canvas();
		 cacheCanvas.setBitmap(cacheBitmap);
		 cacheCanvas.drawColor(Color.WHITE);
		
		draw();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) { 
	
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) { 
	
	}	

	
	private void draw() {
		Canvas _canvas = null;
		try {
			//서피스 뷰의 락을 가지고 온다.
			_canvas = mHolder.lockCanvas(null);
			
			super.draw(_canvas);
			
			_canvas.drawBitmap(cacheBitmap, 0, 0, null);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (_canvas != null) {
				mHolder.unlockCanvasAndPost(_canvas);
			}
		}
		
	}
	
	
	public boolean Save(OutputStream outstream) {
		try {
			cacheBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
			invalidate();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
