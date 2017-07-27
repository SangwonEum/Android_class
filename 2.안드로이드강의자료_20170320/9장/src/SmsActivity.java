package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends Activity implements OnClickListener {

    EditText mEditTextPhone;
    EditText mEditTextMessage;
    Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);

        mEditTextPhone = (EditText) findViewById(R.id.phone);
        mEditTextMessage = (EditText) findViewById(R.id.message);
        mButton = (Button) findViewById(R.id.send);
        mButton.setOnClickListener(this);
    }

    private void sendMessage() {
        String phone = mEditTextPhone.getText().toString();
        String message = mEditTextMessage.getText().toString();

        if (phone.length() == 0 || message.length() == 0) return;

        String SENT = "SMS를 전송함";
        String DELIVERED = "SMS가 도착함";

        PendingIntent sendPendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);
        PendingIntent deliveredPendingIntent = PendingIntent.getBroadcast(this,
                0, new Intent(DELIVERED), 0);

        //SMS를 발송한 후 성공 또는 실패 여부를 반환 받는다.
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(getBaseContext(), "RESULT_OK",
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(getBaseContext(),
                            "RESULT_ERROR_GENERIC_FAILURE", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(getBaseContext(), "RESULT_ERROR_NO_SERVICE",
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(getBaseContext(), "RESULT_ERROR_NULL_PDU",
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(getBaseContext(), "RESULT_ERROR_RADIO_OFF",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }, new IntentFilter(SENT));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(getBaseContext(), "RESULT_OK",
                            Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(getBaseContext(), "RESULT_CANCELED",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, sendPendingIntent,
                deliveredPendingIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.send:
            sendMessage();
            break;
        }
    }
}