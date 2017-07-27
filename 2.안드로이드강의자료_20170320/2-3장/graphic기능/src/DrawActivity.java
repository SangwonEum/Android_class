package com.ch1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class DrawActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ViewWithRedDot(this));
	}
	 //View Ŭ������ �����Ѵ�.
	 private static class ViewWithRedDot extends View {

	        public ViewWithRedDot(Context context) {
	            super(context);

	        }
	        
	        @Override protected void onDraw(Canvas canvas) {
	            canvas.drawColor(Color.YELLOW);  //ĵ������ ������ ��������� �����Ѵ�.

	            Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG); //Paint��ü�� �����Ѵ�.
	            circlePaint.setColor(Color.RED);  //���� �ѷ����� �������� �����Ѵ�.
	            canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/3, circlePaint);
	            
	        }
	    }
}
