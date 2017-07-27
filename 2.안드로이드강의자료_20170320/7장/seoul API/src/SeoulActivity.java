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
            Toast.makeText(SeoulActivity.this, "BackgroundTask ������",
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
	           
	            publishProgress(sb);  //�������� ���� ������ ȭ������ ����Ѵ�.(onProgressUpdate)
	           
	      }catch(Exception e){
	      	e.printStackTrace();
	      }
    		 return sb;  //���� �� onPostExecute �޼ҵ�� ������� ��ȯ�Ѵ�.
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