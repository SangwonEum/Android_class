package com.example.atest.ch1;

import com.example.atest.R;
import com.example.atest.ch4.BReceiverSample;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class TestMethodActivity extends  Activity{
	@Override
	//최초로 화면이  전면에 나타날 때 호출
	public void onCreate(Bundle savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);
		Toast.makeText(getApplicationContext(), "onCreate method 호출 ", Toast.LENGTH_SHORT).show();
	}
	
	
	
	@Override
	//다시 화면이 전면에 나타날 때 호출
	protected void onResume() {
		super.onResume();
		Toast.makeText(getApplicationContext(), "onResume method 호출 ", Toast.LENGTH_SHORT).show();
	}



	@Override
	//홈키를 누르거나, 다른 액티비티가 전면에 나타날 때 호출
	protected void onPause() {
		super.onPause();
		Toast.makeText(getApplicationContext(), "onPause method 호출 ", Toast.LENGTH_SHORT).show();
	}



	@Override
	//종료 키를 누를 때 호출
	protected void onDestroy()	{
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "onDestroy method 호출 ", Toast.LENGTH_SHORT).show();
	}
}


