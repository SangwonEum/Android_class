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
	
	
	int lastX; //������ ���� ��ǥ�� ����ϴ� ��ǥ ����
	int lastY; 

	public DrawBoard(Context context) {
		super(context);
		
		this.mPaint = new Paint();
		this.mPaint.setColor(Color.BLACK);
		
		this.lastX = -1;
		this.lastY = -1;
		
		Log.d(TAG, "�ʱ�ȭ�մϴ�.");
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
				//�հ����� ���� �� ��ǥ�� �ʱ�ȭ�Ѵ�.
				lastX = -1;
				lastY = -1;
				
				break;
	
			case MotionEvent.ACTION_DOWN:
				// �հ����� ȭ�鿡 ���� �� ���� ��ǥ���� ��ġ�� ������ ���� �׸���.
				if (lastX != -1) {
					if (X != lastX || Y != lastY) {
						cacheCanvas.drawLine(lastX, lastY, X, Y, mPaint);
					}
				}
				
				//��� ��ġ�� ��ǥ�� ���� ��ǥ�� �����Ѵ�.
				lastX = X;
				lastY = Y;
				
				break;
	
			case MotionEvent.ACTION_MOVE:
				// ȭ�� �巡�� �ÿ� ���� ��ǥ���� �巡�� �� ��ġ�� ��ǥ���� ���������� ���� �׸���.
				if (lastX != -1) {
					cacheCanvas.drawLine(lastX, lastY, X, Y, mPaint);
				}
	
				lastX = X;
				lastY = Y;
				
				break;
		}

		invalidate(); //onDraw() �� ȣ���ؼ� �̹����� �ٽ� �׸���.
		return true;
	}
	
	public boolean Save(OutputStream outstream) {
		try {
			cacheBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
			invalidate(); ////onDraw() �� ȣ���ؼ� �̹����� �ٽ� �׸���.
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
