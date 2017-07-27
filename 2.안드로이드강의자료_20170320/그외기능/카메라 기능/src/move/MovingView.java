package com.move;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.move.R;

public class MovingView extends SurfaceView implements SurfaceHolder.Callback {
	private ImgThread thread;
	
	public MovingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this); // 홀더를 이용해서 서피스의 정보를 클래스뷰와 연결한다.
		
		thread = new ImgThread(holder, context);
		
		setFocusable(true); // Activity에서는 Key 이벤트가 모두 먹히는데
							// View 에서는 해당 View가 Focus될 때만 동작한다
	}
	
	class ImgThread extends Thread {
		private Bitmap mImgBack;
		private Drawable mManImage[] = new Drawable[2];
		private int cnt = 0;
		private SurfaceHolder mSurfaceHolder;
		private int xpos = 0;
		private int ypos = 0;
		
		
		public ImgThread( SurfaceHolder surfaceHolder, Context context) {
			mSurfaceHolder = surfaceHolder;
			Resources res = context.getResources();
						
			mImgBack = BitmapFactory.decodeResource(res, R.drawable.back);
			mManImage[0] = res.getDrawable(R.drawable.man1);
			mManImage[1] = res.getDrawable(R.drawable.man2);
		}
		
		public void run() {
			while (true) {
				Canvas canvas = null;
				try {
					canvas = mSurfaceHolder.lockCanvas(null); // 서피스에 그림을 그린다
					//쓰레드가 홀더를 배타적으로 이용한다.
					synchronized (mSurfaceHolder) {
						canvas.drawBitmap(mImgBack, 0, 0, null);
				
						cnt++;
						mManImage[cnt%2].setBounds(xpos, ypos, xpos+70, ypos+120);
						mManImage[cnt%2].draw(canvas);
						
						Paint paint = new Paint();
						paint.setAntiAlias(true);
						paint.setTextSize(18);
						canvas.drawText(" X=" + xpos + ", Y=" + ypos, 0, 20 * 1, paint);
						
						sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (canvas != null) {
						mSurfaceHolder.unlockCanvasAndPost(canvas); // 서피스의 그림을 서피스뷰에 출력한다.
					}
				}
			}
		}
		
		void setXPos(int val) {
			xpos += val;
		}
		void setYPos(int val) {
			ypos += val;
		}
		void setX(int val) {
			xpos = val-35;
		}
		void setY(int val) {
			ypos = val-60;
		}
	}
	
	

	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while(retry) {
			try {
				thread.join(); // 쓰레드가 종료되기를 기다리는 것
				retry = false;
			} catch (InterruptedException e) {
				
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
			thread.setXPos(-10);
		else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
			thread.setXPos(10);
		else if (keyCode == KeyEvent.KEYCODE_DPAD_UP)
			thread.setYPos(-10);
		else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
			thread.setYPos(10);
		
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		thread.setX((int)event.getX());
		thread.setY((int)event.getY());
		int touchAction = event.getAction();
		
		return true;
	}

}
