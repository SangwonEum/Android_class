package com.ch1a;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogSample02 extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(DialogSample02.this);
               // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("잠시만 기다려주세요...");
                progressDialog.setMax(100);
                progressDialog.setCancelable(true);
             //   progressDialog.setIndeterminate(true); //진행률과 무관하게 상태바 표시하기
                progressDialog.show();

                Thread t = new Thread() {
                    public void run() {
                        int tick = 0;
                        while (tick < 100) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                            }
                            tick++;
                            progressDialog.incrementProgressBy(1);
                            Log.d("DialogSample", "tick: " +Integer.toString(tick));
                        }
                    }
                };
                t.start();
            }
        });
    }
}
