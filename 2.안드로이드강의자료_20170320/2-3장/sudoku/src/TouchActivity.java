package com.ch1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TouchActivity extends Activity{
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(new MyView(this));
	    }
	    
	  class MyView extends View {
		  public MyView(Context context) {
		   super(context);
		   // TODO Auto-generated constructor stub
		  }
		  
		  @Override
		  public boolean onTouchEvent(MotionEvent event) {
			  String x=null;
			  String y=null;
			  
			   super.onTouchEvent(event);
			   if (MotionEvent.ACTION_DOWN == event.getAction()) {
				   x=Float.toString(event.getX());
				   y=Float.toString(event.getY());
			    Toast.makeText(TouchActivity.this, "x:��ǥ:"+x +"y��ǥ:"+y, Toast.LENGTH_SHORT).show();
			    return true;
			   }
			   return false;
		  }
		  
		  protected void onDraw(Canvas canvas) {
				super.onDraw(canvas);
					    
				super.onDraw(canvas);
			    
				Paint paint = new Paint() ;  
				paint.setAntiAlias(true) ;
					    
				canvas.drawColor(Color.GRAY) ;  //����ȭ�� ����  
				
				paint.setColor(Color.BLACK);
				canvas.drawLine(0, 205, canvas.getWidth(), 205, paint) ; //������ �� ��ǥ ���̿� ������ �׸���.
				canvas.drawLine(200, 0, 200, canvas.getHeight(), paint) ;   
		  }
		
	}
}
