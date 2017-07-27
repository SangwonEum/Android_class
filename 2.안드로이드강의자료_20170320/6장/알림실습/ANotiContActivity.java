package com.ch6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidprj.R;

public class ANotiContActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anoti_service);
    }
    //서비스를 호출한다.
    public void onStartService(View view) {
        Intent intent = new Intent(this, ANotiService.class);
        intent.putExtra("time",10); //3초 후에 알림 메시지 표시
        startService(intent);
    }

    //서비스를 중단한다.
    public void onStopService(View view) {
        Intent intent = new Intent(this, ANotiService.class);
        stopService(intent);
        Toast.makeText(this, "알림서비스 종료", 0).show();
    }
}

