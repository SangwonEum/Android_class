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
        dialog.setMessage("������....");
        dialog.show();
        new BackgroundTask().execute();
    }
    
    //ù��° ���ڴ� doInBackground�� ������ Ÿ���� �����Ѵ�.
    //�ι�° ���ڴ� onProgressUpdate�� ������ Ÿ���� �����Ѵ�.
    //����° ���ڴ� onPostExecute�� ������ Ÿ���� �����Ѵ�.
    private class BackgroundTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(GoogleActivity.this, "BackgroundTask ������",
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
	            publishProgress(text);  //�������� ���� ������ ȭ������ ����Ѵ�.(onProgressUpdate)
	            
        	}catch(Exception e) {
        	e.printStackTrace();
        	}
    		 return sb;  //���� �� onPostExecute �޼ҵ�� ������� ��ȯ�Ѵ�.
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