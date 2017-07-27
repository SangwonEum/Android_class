package com.example.atest.wifi;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.atest.R;

public class WifiActivity extends Activity {
	WifiManager wifiManager;
	int wifiState=WifiManager.WIFI_STATE_DISABLED;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_main);
		 wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
		 wifiState=wifiManager.getWifiState();
		
		Switch toggle = (Switch) findViewById(R.id.wifi_switch);
				
		//시작 시 폰의 와이파이연 정보를 가지고 와서 초기화 한다.
		switch(wifiState){
		case WifiManager.WIFI_STATE_DISABLED:
			toggle.setChecked(false);
			break;
		case WifiManager.WIFI_STATE_ENABLED:
			toggle.setChecked(true);
		}
		
        toggle.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleWiFi(true);
                    Toast.makeText(getApplicationContext(), "wifi enabled", Toast.LENGTH_LONG).show();
                } else {
                    toggleWiFi(false);
                    Toast.makeText(getApplicationContext(), "wifi disabled", Toast.LENGTH_LONG).show();
                }
            }
        });
	}
	
	public void toggleWiFi(boolean status) {
        if (status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (status == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }
	
	public void getIPAddress(View view){
		  WifiInfo info = wifiManager.getConnectionInfo(); //네트워크 정보를 얻어온다.
          int ip=info.getIpAddress();
          String ipv4=Formatter.formatIpAddress(ip);
          Toast.makeText(getApplicationContext(),"ip address: "+ipv4,Toast.LENGTH_SHORT).show();
		
	}
	
	//3G/4G 상태에서 아이피 확인하는 메소드
	public void getLocalIpAddress(View view) {
		try {
			for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
				en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
		
				for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if(!inetAddress.isLoopbackAddress()) {
						String address=inetAddress.getHostAddress().toString();
						Log.d("TAG","IP address:  "+address);
					}
				}
		}
		} catch(SocketException se) {
			Log.e("Network", se.toString());
		}
	}

}
