package com.ch7;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Thread1Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_main);
    }

    public void threadStart(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.i("thread", "OK");
            }
        });
        thread.start();
    }
}