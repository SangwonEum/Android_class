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

	//화면 회전 시 호출 되는 콜백 메소드
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			Toast.makeText(getApplicationContext(), "가로 방향으로 전환 되었습니다.", Toast.LENGTH_LONG).show();
		}else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
			Toast.makeText(getApplicationContext(), "세로 방향으로 전환 되었습니다.", Toast.LENGTH_LONG).show();
		}
	}
}


