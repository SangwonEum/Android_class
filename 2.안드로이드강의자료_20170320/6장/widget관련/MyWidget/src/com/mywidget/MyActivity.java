package com.mywidget;

import com.example.mywidget.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

	public class MyActivity extends Activity {
		 
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.widget_layout);
		setContentView(new ViewWithBitmap(this));
		}
		
		
		 private static class ViewWithBitmap extends View {

			public ViewWithBitmap(Context context) {
				super(context);
			}
			
			@Override protected void onDraw(Canvas canvas) {
	            canvas.drawColor(Color.BLUE);
	            
	            Bitmap jayPic = BitmapFactory.decodeResource(getResources(), R.drawable.bluejay);
	            
	            // 이미지를 확대해서 그린다.
	            Bitmap jayPicMedium= Bitmap.createScaledBitmap(jayPic, 200, 300, false);   //이미지를 확대한다.        
	            canvas.drawBitmap(jayPicMedium, 60, 75, null); //이미지를 정해진 위치에 그린다.
	            
	            
	        }

		 }
}
