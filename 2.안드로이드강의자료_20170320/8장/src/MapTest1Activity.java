package com.ch8;

import android.os.Bundle;

import com.example.googltest.R;
import com.google.android.maps.MapActivity;

public class MapTest1Activity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}