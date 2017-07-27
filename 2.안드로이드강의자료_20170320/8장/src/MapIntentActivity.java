package com.ch8;

import com.example.googltest.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MapIntentActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_intent);
    }
    
    public void map(View view)
    {
    	//강남역 위치
        String intentString = "geo:37.497978,127.027613";
        Uri geoUri = Uri.parse(intentString);
        Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
        startActivity(intent);
    }
}


