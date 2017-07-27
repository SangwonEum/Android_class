package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class TeleInfoActivity extends Activity {

    private static String TAG = TeleInfoActivity.class.toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_main);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        String phoneNumber = telephonyManager.getLine1Number(); //휴대 전화번호 
        String carrier = telephonyManager.getNetworkOperatorName();  //통신사업자
        int dataState = telephonyManager.getDataState();  //현재 데이터 통신 상태
        String countryIso = telephonyManager.getNetworkCountryIso(); //국가 코드
        String simSerialNumber = telephonyManager.getSimSerialNumber(); //SIM 번호

        Log.i(TAG, "전화번호 : " + phoneNumber);
        Log.i(TAG, "통신 사업자: " + carrier);
        Log.i(TAG, "데이터 통신 상태 : " + dataState);
        Log.i(TAG, "국가 코드 : " + countryIso);
        Log.i(TAG, "SIM 번호 : " + simSerialNumber);
    }
}