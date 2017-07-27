package com.mytest2.ch3;

import com.mytest2.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class ConfigActicity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_layout);
	}

	//ȭ�� ȸ�� �� ȣ�� �Ǵ� �ݹ� �޼ҵ�
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			Toast.makeText(getApplicationContext(), "���� �������� ��ȯ �Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
		}else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
			Toast.makeText(getApplicationContext(), "���� �������� ��ȯ �Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
		}
	}
}


