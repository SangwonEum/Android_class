package com.ch1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;


public class DrawLine extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new LineView(this));
	}
	
	 private static class LineView extends View {

		public LineView(Context context) {
			super(context);
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
