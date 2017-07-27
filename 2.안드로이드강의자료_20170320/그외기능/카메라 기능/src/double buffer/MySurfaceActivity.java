package com.mytest.ch10a;

import android.app.Activity;
import android.os.Bundle;

public class MySurfaceActivity extends Activity {
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(getApplicationContext()));
	}
}
