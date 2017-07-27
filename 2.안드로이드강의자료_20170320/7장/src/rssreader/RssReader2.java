package com.example.ch7;

import java.net.URL;

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

import com.example.atest.R;

public class RssReader extends Activity {
	ProgressDialog dialog;
	TextView tv;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_main);
		tv=(TextView) findViewById(R.id.textView);
		
	}
	
	public void receiveData(View view){
		dialog=new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMessage("data receiving..");
		dialog.show();
		new BackgroundTask().execute();
	}
	
	private class BackgroundTask extends AsyncTask<String,String,String>{
		protected void onPreExecute(){
			super.onPreExecute();
			Toast.makeText(RssReader.this,"BackgroundTask Ω√¿€ ¿¸",Toast.LENGTH_SHORT).show();
		}
		
		protected String doInBackground(String... datas){
			String line="";
			String text="";
			try{
				URL url=new URL("http://news.google.co.kr/news?pz=1&cf=all&ned=kr&hl=ko&output=rss");
				XmlPullParser parser=XmlPullParserFactory.newInstance().newPullParser();
				parser.setInput(url.openStream(),null);
				int eventType=parser.getEventType();
				
				String tag;
				boolean inTitle=false;
				while(eventType != XmlPullParser.END_DOCUMENT){
					switch(eventType){
					case XmlPullParser.TEXT:
						tag=parser.getName();
						if(inTitle){
							text+=parser.getText()+","+"\n";
						}
						break;
					case XmlPullParser.END_TAG:
						tag=parser.getName();
						if(tag.compareTo("title") ==0){
							inTitle=false;
						}
						if(tag.compareTo("link") ==0){
							inTitle=false;
						}
						break;
					case XmlPullParser.START_TAG:
						tag=parser.getName();
						if(tag.compareTo("title")==0){
							inTitle=true;
						}
						if(tag.compareTo("link") ==0){
							inTitle=true;
						}
						break;
					}
					eventType=parser.next();
				}
				publishProgress(text);
			}catch(Exception e){
				e.printStackTrace();
			}
			return text;
					
		}
		protected void onProgressUpdate(String...data){
			Log.d("tag",data[0].toString());
			tv.setText(data[0].toString());
		}
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			dialog.dismiss();
		}
	}
	

}
