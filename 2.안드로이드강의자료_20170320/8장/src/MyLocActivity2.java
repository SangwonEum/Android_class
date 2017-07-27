package com.ch8;


import com.example.googltest.R;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocActivity2 extends Activity {
    
    private LocationProvider locationProvider;
    private static final String TAG = MyLocation02Activity.class.getName();
    private TextView locationTextView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation);

        locationTextView = (TextView) findViewById(R.id.location);
        
        LocationManager locManager;
        LocationListener locationListener;
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //���ι��̴� ����� ���´�.
        for( String provider : locManager.getAllProviders() )
        {
            Log.i(TAG,provider);
        }
        
        //������ �����Ѵ�.
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        //������ ���ι��̴��� ���´�.
        String locationProviderName = locManager.getBestProvider(criteria, true);
        locationProvider = locManager.getProvider(locationProviderName);
        locationListener = new LocationListener() {
    	  @Override
          public void onLocationChanged(Location location) {
          	String message = String.format("���ι��̴� : %s, ���� : %f, �浵 : %f", locationProvider.getName(), location.getLatitude(), location.getLongitude());
              Toast.makeText(MyLocation02Activity.this,message,Toast.LENGTH_SHORT).show();
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
        locManager.requestLocationUpdates(locationProvider.getName(), 0, 0, locationListener);
    }
}