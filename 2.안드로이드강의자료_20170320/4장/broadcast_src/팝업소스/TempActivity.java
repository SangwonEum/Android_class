package com.example.atest.ch4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.atest.R;

public class TempActivity   extends  Activity{
	Button btn_cancel;
	TextView tName;
	TextView tAge;
	

	public void onCreate(Bundle savedInstanceState)	{
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		String name=intent.getStringExtra("name");
		int age=intent.getIntExtra("age", 0);
		
		
		setContentView(R.layout.temp);
		
		tName=(TextView) findViewById(R.id.tName);
		tAge=(TextView) findViewById(R.id.tAge);
		
		tName.setText(name);
		tAge.setText(Integer.toString(age));
		
	}
	
	public void cancel(View view){
		finish();
	}
	
}
