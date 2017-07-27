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


public class DrawShape extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ShapeView(this));
	}
	
	 private static class ShapeView extends View {
			public ShapeView(Context context) {
					  super(context);
			}
				  
			@Override
		    protected void onDraw(Canvas canvas) {
				super.onDraw(canvas);
					    
				Paint paint = new Paint() ;  
				paint.setAntiAlias(true) ;
					    
				canvas.drawColor(Color.GRAY) ;  //바탕화면 배경색  
				
				paint.setColor(Color.WHITE);
				canvas.drawRect(80, 150, 250, 300, paint) ;  //지정한 좌표에 사각형을 그린다.
					     
				paint.setColor(Color.GRAY);
				canvas.drawRect(100, 180, 150, 280, paint) ; 
				paint.setColor(Color.GREEN);
				canvas.drawRect(170, 180, 230, 230, paint) ; 
					  
				paint.setColor(Color.YELLOW);
				RectF rf = new RectF(100, 180, 150, 280) ;   
				canvas.drawRoundRect(rf, 120, 120, paint) ;
				paint.setColor(Color.WHITE);
				
				canvas.drawLine(170, 205, 230, 205, paint) ; //지정된 두 좌표 사이에 라인을 그린다.
				canvas.drawLine(200, 170, 200, 230, paint) ;   
					         
				Path path =new Path() ;  
					    
				paint.setColor(Color.BLUE);
					     
				path.moveTo(50,0);
				path.lineTo(25, 100);
				path.lineTo(100, 50);
				canvas.drawPath(path, paint) ;
			}
	 }
	
}
