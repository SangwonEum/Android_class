package com.mytest.ch10a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder mHolder;
	public MySurfaceView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context){
		mHolder=getHolder();
		mHolder.addCallback(this);  //holder에서 콜백 메소드를 사용할 수 있게 등록한다.
	}

	//서피스의 크기가 변할 경우 호출되는 콜백 메소드
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
	}

	//메모리에 최초로 생성될 때 호출되는 콜백 메소드
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
	private void draw(){
		Canvas canvas=null;
		Paint paint=new Paint();
		try{
		canvas=mHolder.lockCanvas(); //캔버스에 접근하는 락을 얻는다.
		canvas.drawLine(100.0f,100.0f,200.0f,200.0f,paint);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(canvas != null){
				mHolder.unlockCanvasAndPost(canvas);
			}
		}
		
	}

}
