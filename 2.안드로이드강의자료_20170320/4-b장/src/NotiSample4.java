package com.ch4a;

import com.example.androidprj.R;

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

public class NotiSample4 extends Activity implements OnClickListener {

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
    	String ticker = "ƼĿ�޼���. �޽����� �����߽��ϴ�. ";
        String title = "�˸� �޽����Դϴ�.";
        String text = "����: ��Ƽ�����̼� �׽�Ʈ�Դϴ�.";

        Intent intent = new Intent(this, NotiActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotiSample4.this, 0, intent, 0);

        Notification notification = new Notification();
        notification.icon = android.R.drawable.ic_input_add;
        notification.tickerText = ticker;
        notification.when = System.currentTimeMillis();
        notification.ledARGB = 0xff00ff00;
        notification.ledOnMS = 3000;
        notification.ledOffMS = 1000;
        notification.flags |= Notification.DEFAULT_LIGHTS;

        notification.setLatestEventInfo(this, title, text, pendingIntent);

        mNotificationManager.notify(MESSAGE_ID, notification);// ��Ƽ�����̼� �Ŵ����� ����Ѵ�.
        Toast.makeText(this, "�޽����� �����߽��ϴ�.", Toast.LENGTH_SHORT).show();
    }

    private void nofityCancel() {
        mNotificationManager.cancel(MESSAGE_ID);  //��Ƽ�����̼��� ���¹ٿ��� �����Ѵ�.
        Toast.makeText(this, "�޽����� ���¹ٿ��� ����ϴ�.", Toast.LENGTH_SHORT).show();
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