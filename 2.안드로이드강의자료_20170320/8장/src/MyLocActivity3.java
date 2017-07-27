package com.example.googletest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MyLocActivity3 extends Activity{
	private static final String TAG="MyLocActivity1a";
	  private TextView locTextView;
	  ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation3);
        locTextView = (TextView) findViewById(R.id.address);
    }
    
    public void geocoder(View view){
    	 new BackgroundTask().execute();  //async 태스크를 실행한다.
    }
    
	
	 //asynctask를 이용해서 지역명을 얻어온다.
    private class BackgroundTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MyLocActivity3.this, "BackgroundTask 시작전",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... datas) {

            double latitude = 37.527111;
            double longitude = 127.02844;
            String countryName=null;
            String placeName=null;
            String bungi=null;
            String message="";
            
            Locale.setDefault(Locale.KOREA);
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
            	//주소로 변환 시 최대 반환 개수를 정한다.
                List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 3);
                
                for(Address address : addressList)
                {
                    countryName = address.getCountryName(); //국가
                    placeName = address.getLocality();  //지역명
                    bungi=address.getFeatureName();  //번지,동
                    message += String.format("[%s][%s][%s]\n",countryName,placeName,bungi);
                    Log.d("TAG",message);
                    publishProgress(message);
                   
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            return message;
        }

       
        @Override
        protected void onProgressUpdate(String... message) {
            //mProgressBar.setProgress(progress[0]);
        	// Toast.makeText(this, message[0], Toast.LENGTH_SHORT).show();
             locTextView.setText(message[0]);
        }

        
        @Override
        //백그라운드 작업이 완료되면 호출딘다.
        protected void onPostExecute(String result) {
            Toast.makeText(MyLocActivity3.this, result,
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
