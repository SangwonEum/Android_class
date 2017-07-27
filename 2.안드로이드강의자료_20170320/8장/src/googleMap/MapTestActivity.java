package com.example.gtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapTestActivity  extends FragmentActivity{
	GoogleMap mGoogleMap;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_detail);
		try {
			MapsInitializer.initialize(this);
			  
		} catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		}
		
		init();
		
	}
	private void init() {
		
		Intent getI=getIntent();
		//String title=getI.getStringExtra("title");
		String title="movie Threater";
		double lon=getI.getDoubleExtra("lon", 127.027613);
		double lat=getI.getDoubleExtra("lat", 37.497978);
		LatLng position=new LatLng(lat,lon);
		
		GooglePlayServicesUtil.isGooglePlayServicesAvailable(MapTestActivity.this);
		
		mGoogleMap=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		
/*		CameraUpdate camera=CameraUpdateFactory.newLatLng(position);
		mGoogleMap.moveCamera(camera);
*/		
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,6));
		mGoogleMap.addMarker(new MarkerOptions().position(position).title(title)).showInfoWindow();
		mGoogleMap.setOnMarkerClickListener(new OnMarkerClickListener(){

			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
		
	}

}
