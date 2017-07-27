package com.ch7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GoogleActivity extends Activity {
	 ProgressDialog dialog;
	 TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_main);
        tv = (TextView) findViewById(R.id.textView);
  }
    
    public void receiveData(View view) {
        
        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("수신중....");
        dialog.show();
        new BackgroundTask().execute();
    }
    
    //첫번째 인자는 doInBackground의 인자의 타입을 결정한다.
    //두번째 인자는 onProgressUpdate의 인자의 타입을 결정한다.
    //세번째 인자는 onPostExecute의 인자의 타입을 결정한다.
    private class BackgroundTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(GoogleActivity.this, "BackgroundTask 시작전",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... datas) {
        	String line="";
        	String sb ="";
        	String text="";
        	try {
	        	URL url = new URL("https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=android");
	            URLConnection connection = url.openConnection();
	
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            while ((line = reader.readLine()) != null) {
	               sb+=line;
	            }
	            Log.d("GoogleActivity",sb);

	            JSONObject json = new JSONObject(sb);
	            JSONObject responseData = (JSONObject) json.get("responseData");
	            JSONArray results = (JSONArray) responseData.get("results");
	            for (int i = 0; i < results.length(); i++) {
	                JSONObject result = (JSONObject) results.get(i);
	                text+=result.get("title")+"\n\n";
	            }
	            publishProgress(text);  //서버에서 받은 정보를 화면으로 출력한다.(onProgressUpdate)
	            
        	}catch(Exception e) {
        	e.printStackTrace();
        	}
    		 return sb;  //종료 후 onPostExecute 메소드로 결과값을 반환한다.
        }

        @Override
        protected void onProgressUpdate(String... data) {
        	 tv.setText(data[0].toString());
        }

   

        @Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
		}

		@Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}