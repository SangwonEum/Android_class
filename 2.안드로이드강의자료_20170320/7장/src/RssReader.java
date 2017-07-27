package com.ch7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidprj.R;

public class RssReader extends Activity {
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
           Toast.makeText(RssReader.this, "BackgroundTask 시작전",
                   Toast.LENGTH_SHORT).show();
       }

       @Override
       protected String doInBackground(String... datas) {
       	String line="";
       	String text="";
       	try {
            
	        URL url = new URL("http://news.google.co.kr/news?pz=1&cf=all&ned=kr&hl=ko&output=rss");
	
	        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
	        parser.setInput(url.openStream(), null);
	
	        int eventType = parser.getEventType();
	        
	        String tag;
	        boolean inTitle = false;
	        while (eventType != XmlPullParser.END_DOCUMENT) {
	            switch (eventType) {
	
	            case XmlPullParser.TEXT:
	                tag = parser.getName();
	                if (inTitle) {
	                	text+=parser.getText()+"\n";
	                }
	
	                break;
	            case XmlPullParser.END_TAG:
	                tag = parser.getName();
	                if (tag.compareTo("title") == 0) {
	                    inTitle = false;
	                }
	                break;
	            case XmlPullParser.START_TAG:
	                tag = parser.getName();
	                if (tag.compareTo("title") == 0) {
	                    inTitle = true;
	                }
	                break;
	            }
	            eventType = parser.next();
	        }
	        publishProgress(text);  //서버에서 받은 정보를 화면으로 출력한다.(onProgressUpdate)
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
   		 return text;  //종료 후 onPostExecute 메소드로 결과값을 반환한다.
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
