package com.example.btest.ch4;


import com.example.btest.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NotiSample01 extends Activity implements OnClickListener {

    private final static int MESSAGE_ID = 12345;
    private Button mNofityButton;
    private Button mCancelButton;
    private NotificationManager mNotificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti_main);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNofityButton = (Button) findViewById(R.id.nofity);
        mNofityButton.setOnClickListener(this);

        mCancelButton = (Button) findViewById(R.id.cancel);
        mCancelButton.setOnClickListener(this);
    }

    private void nofity() {
        String ticker = "티커메세지. 메시지가 도착했습니다. ";
        String title = "알림 메시지입니다.";
        String text = "내용. 노티피케이션 테스트입니다.";

        Intent intent = new Intent(this, NotiActivity.class); //노티피케이션 터치시 화면으로 이동한다.
        PendingIntent pendingIntent = PendingIntent.getActivity(//PendingIntent 는 "Intent+권한 위임+나중 처리를 의미한다.
                NotiSample01.this, 0, intent, 0);
        Notification notification = new Notification( 
                android.R.drawable.ic_input_add, ticker,
                System.currentTimeMillis());
        notification.setLatestEventInfo(this, title, text, pendingIntent);

        /*
         * Notification noti=new Notification();
         * noti.icon=android.R.drawable.ic_input_add;
         * noti.tickerText=ticker;
         * noti.when=System.currentTimeMillis();
         */
        mNotificationManager.notify(MESSAGE_ID, notification);// 노티피케이션 매니저에 등록한다.
        Toast.makeText(this, "메시지가 도착했습니다.", Toast.LENGTH_SHORT).show();
    }

    private void nofityCancel() {
        mNotificationManager.cancel(MESSAGE_ID);  //노티피케이션을 상태바에서 제거한다.
        Toast.makeText(this, "메시지를 상태바에서 지웁니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.nofity:
            nofity();
            break;
        case R.id.cancel:
            nofityCancel();
            break;
        }
    }
}