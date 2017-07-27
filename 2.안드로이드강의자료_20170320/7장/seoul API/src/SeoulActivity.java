package com.ch7;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeoulActivity extends Activity {
	  ProgressDialog dialog;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seoul_data);
      
        tv = (TextView) findViewById(R.id.message);
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
            Toast.makeText(SeoulActivity.this, "BackgroundTask 시작전",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... datas) {
        	String line=null;
        	String sb =null;
    		try {
    			URL url = new URL("http://openapi.seoul.go.kr:8088/sample/xml/ListCulturalAssetsInfo/1/5/11016");
	            URLConnection connection = url.openConnection();
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            while ((line = reader.readLine()) != null) {
	                sb+=line;
	                Log.d("SeoulActivity",line);
		        }
	           
	            publishProgress(sb);  //서버에서 받은 정보를 화면으로 출력한다.(onProgressUpdate)
	           
	      }catch(Exception e){
	      	e.printStackTrace();
	      }
    		 return sb;  //종료 후 onPostExecute 메소드로 결과값을 반환한다.
        }

        @Override
        protected void onProgressUpdate(String... progress) {
        	 tv.setText(progress[0].toString());
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