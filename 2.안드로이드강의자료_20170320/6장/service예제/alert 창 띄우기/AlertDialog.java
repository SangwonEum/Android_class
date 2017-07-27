package com.example.atest.ex1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.atest.R;


public class AlertDialog extends Activity {

	 private String notiMessage;

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  Bundle bun = getIntent().getExtras();
	  notiMessage = bun.getString("notiMessage");
	  
	  //대기 모드 에서 폰을 깨운다.
	  PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
	     PowerManager.WakeLock mWakelock = pm.newWakeLock(
	       PowerManager.FULL_WAKE_LOCK |
	       PowerManager.ACQUIRE_CAUSES_WAKEUP |
	       PowerManager.ON_AFTER_RELEASE, "test");
	     mWakelock.acquire();
	  
	  getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //lock 상태에서 보여 줌
			  | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
			  | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
			  | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
			  requestWindowFeature(android.view.Window.FEATURE_NO_TITLE); //투명하게 만들엑티비티의 타이틀을 없엠
			  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //사용자가 화면을 끄지않는한 꺼지지않게 유지
			  getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, // 생성되는 엑티비티의 초점을 잃게 만듬. 
			  WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
	  setContentView(R.layout.alert_dialog);
	 
	  TextView adMessage = (TextView)findViewById(R.id.message);
	  adMessage.setText(notiMessage);
	  
	  Button adButton = (Button)findViewById(R.id.submit);
	  
	  adButton.setOnClickListener(new SubmitOnClickListener());
	  

	 }
	 private class SubmitOnClickListener implements OnClickListener {

	  public void onClick(View v) {
	   finish();

	  }
	 }
	}