package com.example.atest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MediaService extends Service{
	
	private MediaPlayer mPlayer = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("TAG", "music is playing");
		super.onStart(intent, startId);
		int  num=intent.getIntExtra("fileName",0);
		
		mPlayer = MediaPlayer.create(this,num);
		mPlayer.start();
	}
	
	@Override
	public void onDestroy() { 
		Log.d("TAG", "music is stopped");
		mPlayer.stop();
		super.onDestroy();
	}
}
