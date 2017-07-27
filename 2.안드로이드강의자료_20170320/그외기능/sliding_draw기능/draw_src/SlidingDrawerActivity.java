package com.ch1.ex5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SlidingDrawer;

import com.example.androidprj.R;

public class SlidingDrawerActivity  extends Activity{
	Button btnClose;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_draw);
        
        btnClose = (Button)findViewById(R.id.btnclose);
        
        btnClose.setOnClickListener(new OnClickListener() {   
		   @Override
		   public void onClick(View v) {
		    SlidingDrawer drawer = (SlidingDrawer)findViewById(R.id.slide);
		    drawer.animateClose();
		   }
		 });
    }
}
