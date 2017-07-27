package com.example.atest.ch7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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

public class SeoulActivity  extends Activity{
	ProgressDialog dialog;
	TextView tv;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seoul_data);
		
		tv=(TextView) findViewById(R.id.message);
		
	}
	
	public void receiveData(View view){
		dialog=new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("data received....");
		dialog.show();
		new BackgroundTask().execute();
	}
	
	private class BackgroundTask extends AsyncTask<String,String,String>{
		protected void onPreExecute(){
			super.onPreExecute();
			Toast.makeText(SeoulActivity.this,"Background start before",Toast.LENGTH_SHORT).show();
		}
		
		protected String doInBackground(String... datas){
			/*String line=null;
			String sb= null;*/
			String line="";
			String text="";
			try{
				URL url=new URL("http://openapi.seoul.go.kr:8088/6c6565627331323632303935/xml/ListCulturalAssetsInfo/1/5/");
				XmlPullParser parser=XmlPullParserFactory.newInstance().newPullParser();
				parser.setInput(url.openStream(),null);
				
				int eventType=parser.getEventType();
				
				String tag;
				boolean end_tag=false;
				boolean inTitle=false;
				while(eventType != XmlPullParser.END_DOCUMENT){
					switch(eventType){

		            case XmlPullParser.TEXT:
		                tag = parser.getName();
		                if (inTitle) {
		                	text+=parser.getText()+",";
		                	if(end_tag==true){
		                		text+="-\n";
		                		end_tag=false;
		                	}
		                }
		
		                break;
		            case XmlPullParser.END_TAG:
		                tag = parser.getName();
		                if (tag.compareTo("MANAGE_NUM") == 0) {
		                    inTitle = false;
		                    
		                }
		                if (tag.compareTo("NAME_KOR") == 0) {
		                    inTitle = false;
		                    
		                }
		                break;
		            case XmlPullParser.START_TAG:
		                tag = parser.getName();
		                if (tag.compareTo("MANAGE_NUM") == 0) {
		                    inTitle = true;
		                }
		                if (tag.compareTo("NAME_KOR") == 0) {
		                    inTitle = true;
		                    end_tag=true;
		                }
		                break;
		            }
		            eventType = parser.next();
					}
				
				
				/*URLConnection connection=url.openConnection();
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line =reader.readLine())!= null){
					sb +=line;
					Log.d("TAG",line);
				}*/
				
				publishProgress(text);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return text;
		}
		
		protected void onProgressUpdate(String... progress){
			
			//문자열 파싱 기능 구현
			//VO 세팅하기
			//CultureVO vo=new CultureVO();
			//String[] str="hello,world".split(",");
			
			Log.d("TAG",progress[0].toString());
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
