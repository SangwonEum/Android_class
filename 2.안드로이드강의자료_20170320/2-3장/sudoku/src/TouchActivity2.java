package com.ch1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.ch1.TouchActivity.MyView;
import com.example.androidprj.R;

public class TouchActivity2 extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
	
	public class MyView extends View {
		 /** ������ ��Ʈ�� */
	    Bitmap mBitmap;
	    /** ��Ʈ���� ���� x��ǥ */
	    float x = 0.0f;
	    /** ��Ʈ���� ���� y��ǥ */
	    float y = 0.0f;
	    /** ���� ��ġ�� x��ǥ */
	    float prevX = -1;
	    /** ���� ��ġ�� y��ǥ */
	    float prevY = -1;
		 public MyView(Context context) {
			 super(context);
			   
			  mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		}
	     
	    
	     
	    @Override
	    protected void onDraw(Canvas canvas) {
	        // ������ x, y��ǥ�� �������� �̹����� �׷��ش�.
	        canvas.drawBitmap(mBitmap, x, y, null);
	    }
	     
	    public boolean onTouchEvent(MotionEvent event) {
	        // ������ ��ġ �׼��� ������ �޾ƿ´�.
	        int action = event.getAction();
	        // ��ġ �� x��ǥ
	        float x = event.getX();
	        // ��ġ �� y��ǥ
	        float y = event.getY();
	        // �׼��� ������ ���� ���� ����
	        switch (action) {
	        // �巡�� �Ǿ��� ���� �̺�Ʈ ó��
	        case MotionEvent.ACTION_MOVE :
	            // ��ġ ��ǥ�� �̹��� �ȿ� ���� �ִٸ� �巡�� �� ��ŭ �̹����� ��ǥ�� �̵���Ų��.
	            if (x > this.x && x < this.x + mBitmap.getWidth()
	                    && y > this.y && y < this.y + mBitmap.getHeight()) {
	                if (prevX > 0 && prevY > 0) {
	                    this.x += x - prevX;
	                    this.y += y - prevY;
	                }
	                // ������ ��ǥ���� ���� ��ǥ�� �ȴ�.
	                prevX = x;
	                prevY = y;
	                // ��ǥ �̵��� �������� ȭ���� �����Ѵ�.
	                invalidate();  //������ ȭ���� ��ȿȭ �� �� �ٽ� ȭ���� �����Ѵ�.
	            }
	            break;
	        }
	        return true;
	    }
	}
}
