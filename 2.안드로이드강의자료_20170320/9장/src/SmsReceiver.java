package com.ch11.ex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        if (bundle != null) {
            StringBuffer buffer = new StringBuffer();
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                buffer.append(msgs[i].getOriginatingAddress());
                buffer.append(":");
                buffer.append(msgs[i].getMessageBody().toString());
                buffer.append("\n");
            }

            Toast.makeText(context, buffer.toString(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
