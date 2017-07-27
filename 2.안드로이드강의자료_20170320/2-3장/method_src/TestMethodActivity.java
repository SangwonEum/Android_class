package com.example.atest.ch1;

import com.example.atest.R;
import com.example.atest.ch4.BReceiverSample;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class TestMethodActivity extends  Activity{
	@Override
	//���ʷ� ȭ����  ���鿡 ��Ÿ�� �� ȣ��
	public void onCreate(Bundle savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);
		Toast.makeText(getApplicationContext(), "onCreate method ȣ�� ", Toast.LENGTH_SHORT).show();
	}
	
	
	
	@Override
	//�ٽ� ȭ���� ���鿡 ��Ÿ�� �� ȣ��
	protected void onResume() {
		super.onResume();
		Toast.makeText(getApplicationContext(), "onResume method ȣ�� ", Toast.LENGTH_SHORT).show();
	}



	@Override
	//ȨŰ�� �����ų�, �ٸ� ��Ƽ��Ƽ�� ���鿡 ��Ÿ�� �� ȣ��
	protected void onPause() {
		super.onPause();
		Toast.makeText(getApplicationContext(), "onPause method ȣ�� ", Toast.LENGTH_SHORT).show();
	}



	@Override
	//���� Ű�� ���� �� ȣ��
	protected void onDestroy()	{
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "onDestroy method ȣ�� ", Toast.LENGTH_SHORT).show();
	}
}


