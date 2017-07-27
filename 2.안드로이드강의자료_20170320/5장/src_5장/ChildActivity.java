package com.atest.ch5;


import com.atest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ChildActivity  extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child_layout);
	}
	
	public void onBtnClicked(View v){
		finish();
	}

}
