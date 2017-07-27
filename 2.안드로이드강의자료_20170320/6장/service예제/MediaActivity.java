package com.example.atest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MediaActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_main);
		
		Button btn_play = (Button) findViewById(R.id.btn_play);
		btn_play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent("com.media.service.mymusic");
				int num=R.raw.test;
				
				intent.putExtra("fileName", num);
				
				startService(intent);
			}
		});    
		
		Button btn_stop = (Button) findViewById(R.id.btn_stop);
		btn_stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent("com.media.service.mymusic"));
			}
		});  
		
	}

}