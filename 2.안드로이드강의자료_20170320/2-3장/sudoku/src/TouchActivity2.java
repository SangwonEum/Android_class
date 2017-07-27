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
		 /** 아이콘 비트맵 */
	    Bitmap mBitmap;
	    /** 비트맵의 최초 x좌표 */
	    float x = 0.0f;
	    /** 비트맵의 최초 y좌표 */
	    float y = 0.0f;
	    /** 지난 터치의 x좌표 */
	    float prevX = -1;
	    /** 지난 터치의 y좌표 */
	    float prevY = -1;
		 public MyView(Context context) {
			 super(context);
			   
			  mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		}
	     
	    
	     
	    @Override
	    protected void onDraw(Canvas canvas) {
	        // 현재의 x, y좌표를 기준으로 이미지를 그려준다.
	        canvas.drawBitmap(mBitmap, x, y, null);
	    }
	     
	    public boolean onTouchEvent(MotionEvent event) {
	        // 현재의 터치 액션의 종류를 받아온다.
	        int action = event.getAction();
	        // 터치 된 x좌표
	        float x = event.getX();
	        // 터치 된 y좌표
	        float y = event.getY();
	        // 액션의 종류에 따른 역할 수행
	        switch (action) {
	        // 드래그 되었을 때의 이벤트 처리
	        case MotionEvent.ACTION_MOVE :
	            // 터치 좌표가 이미지 안에 들어와 있다면 드래그 된 만큼 이미지의 좌표도 이동시킨다.
	            if (x > this.x && x < this.x + mBitmap.getWidth()
	                    && y > this.y && y < this.y + mBitmap.getHeight()) {
	                if (prevX > 0 && prevY > 0) {
	                    this.x += x - prevX;
	                    this.y += y - prevY;
	                }
	                // 현재의 좌표들이 지난 좌표가 된다.
	                prevX = x;
	                prevY = y;
	                // 좌표 이동이 끝났으면 화면을 갱신한다.
	                invalidate();  //이전의 화면을 무효화 한 후 다시 화면을 갱신한다.
	            }
	            break;
	        }
	        return true;
	    }
	}
}
