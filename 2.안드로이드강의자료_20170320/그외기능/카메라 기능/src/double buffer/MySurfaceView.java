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
		mHolder.addCallback(this);  //holder���� �ݹ� �޼ҵ带 ����� �� �ְ� ����Ѵ�.
	}

	//���ǽ��� ũ�Ⱑ ���� ��� ȣ��Ǵ� �ݹ� �޼ҵ�
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
	}

	//�޸𸮿� ���ʷ� ������ �� ȣ��Ǵ� �ݹ� �޼ҵ�
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
		canvas=mHolder.lockCanvas(); //ĵ������ �����ϴ� ���� ��´�.
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
