package com.ch8;


import com.example.googltest.R;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocActivity1 extends Activity {
    private static final String TAG = MyLocation01Activity.class.getName();
    private TextView locationTextView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation);

        locationTextView = (TextView) findViewById(R.id.location);
        
        LocationManager locationManager;
        LocationListener locationListener;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        for( String provider : locationManager.getAllProviders() )
        {
            Log.i(TAG,provider);
        }
        
        locationListener = new LocationListener() {
        	//위치 정보가 변경되었을 때 호출됨
        	  @Override
              public void onLocationChanged(Location location) {
                 String message = String.format("위도 : %f, 경도 : %f", location.getLatitude(), location.getLongitude());
              	//String message="lat"+location.getLatitude()+" ,lang: "+location.getLongitude();
              	
                  Toast.makeText(MyLocation01Activity.this,message,Toast.LENGTH_SHORT).show();
                  locationTextView.setText(message);
              }
        	  
            @Override
            public void onStatusChanged(String provider, int status,
                    Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

          
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}