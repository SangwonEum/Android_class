package com.ch5.ex1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidprj.R;

public class ChildActivity1  extends Activity{
	TextView view;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child_activity);
		view=(TextView) findViewById(R.id.text1);
		Intent intent=getIntent();
		String data=intent.getStringExtra("data");
		view.setText(data);
	}
	
	public void finish(View view){
		finish();
	}
}
