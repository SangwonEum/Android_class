package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class TeleStateLisActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_main);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(phoneStateListener,
                PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
                        | PhoneStateListener.LISTEN_CALL_STATE
                        | PhoneStateListener.LISTEN_CELL_LOCATION
                        | PhoneStateListener.LISTEN_DATA_ACTIVITY
                        | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
                        | PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR
                        | PhoneStateListener.LISTEN_SERVICE_STATE
                        | PhoneStateListener.LISTEN_SIGNAL_STRENGTH);
    }

    PhoneStateListener phoneStateListener = new PhoneStateListener() {

        // 통화 전달 변경 통지
        public void onCallForwardingIndicatorChanged(boolean cfi) {
        }

        // 통화상태 변경
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
            // 유휴상태
            case TelephonyManager.CALL_STATE_IDLE:
                Toast.makeText(TeleStateLisActivity.this,
                        "전화 수신 대기 상태", Toast.LENGTH_SHORT).show();
                break;
            // 벨이 울리는 중
            case TelephonyManager.CALL_STATE_RINGING:
                Toast.makeText(TeleStateLisActivity.this,
                        "전화 수신 벨이 울리는 상태", Toast.LENGTH_SHORT).show();
                break;
            // 현재 통화 중
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(TeleStateLisActivity.this,
                        "전화 통화 상태", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
            }
        }

        // 셀 위치변경
        public void onCellLocationChanged(CellLocation location) {
        }

        // 데이터 활동 변경
        public void onDataActivity(int direction) {
        }

        // 데이터 연결 상태변경
        public void onDataConnectionStateChanged(int state) {
        }

        // 대기 변경 통지
        public void onMessageWaitingIndicatorChanged(boolean mwi) {
        }

        // 휴대폰 서비스변경
        public void onServiceStateChanged(ServiceState serviceState) {
        }

        // 신호세기 변경
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        }
    };
}