package test.com.seoulapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;


public class CultureDetailActivity extends Activity {
	String TAG="TAG";
	String manage_num;
	ProgressDialog dialog;
	TextView tCulNum,tCulName,tCulDetail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.culture_detail);
		Intent intent=getIntent();
		manage_num=intent.getStringExtra("manage_num");
		Log.d(TAG,"manage_num:"+manage_num);
		
		tCulNum=(TextView) findViewById(R.id.tCulNum);
		tCulName=(TextView) findViewById(R.id.tCulName);
		tCulDetail=(TextView) findViewById(R.id.tCulDetail);
		
		init();
	}
	
	private void init(){
		dialog=new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("data received...");
		dialog.show();
		new BackgroundTask().execute();
	}
	
	
	private class BackgroundTask extends AsyncTask<String,String,String>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(CultureDetailActivity.this,"Background start  before",Toast.LENGTH_LONG).show();
		}

		
		@Override
		protected String doInBackground(String... arg0) {
			String line="";
			String text="";
			try{
				URL url=new URL("http://openapi.seoul.go.kr:8088/자신의 인증키/xml/ListCulturalAssetsInfo/1/5/"+manage_num);
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
		                		text+="-";
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
		                
		                if(tag.compareTo("BOARD_KOR")==0){
		                	inTitle=false;
		                }
		                break;
		            case XmlPullParser.START_TAG:
		                tag = parser.getName();
		                if (tag.compareTo("MANAGE_NUM") == 0) {
		                    inTitle = true;
		                }
		                if (tag.compareTo("NAME_KOR") == 0) {
		                    inTitle = true;
		                    //end_tag=true;
		                }
		                if(tag.compareTo("BOARD_KOR")==0){
		                	inTitle = true;
		                    end_tag=true;
		                }
					
		                break;
		            }
		            eventType = parser.next();
					}
				
				publishProgress(text);
			}catch(Exception e){
				e.printStackTrace();
			}
			return text;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
		}

		
		@Override
		protected void onProgressUpdate(String... values) {
			Log.d(TAG,values[0].toString());
			String data[]=values[0].split(",");
			
			tCulNum.setText(data[0]);
			tCulName.setText(data[1]);
			tCulDetail.setText(data[2]);
			
			/*arrayList=new ArrayList<CultureVo>();
			
			String[] culture=null;
			
			super.onProgressUpdate(values);
			Log.d(TAG,values[0].toString());
			//tv.setText(progress[0].toString());
			String str[]=values[0].split("-");
			Log.d(TAG,"str.length"+str.length);
			for(int i=0;i<str.length-1;i++){
				culture=str[i].split(",");
				Log.d(TAG,"��ȭ�� ��ȣ  : " +culture[0]+",��ȭ���̸�:"+culture[1]);
				
				CultureVo vo=new CultureVo(culture[0],culture[1]);
				arrayList.add(vo);
			}
			
			cultureAdapter=new CultureAdapter(getApplicationContext(),
											R.layout.culture_item,
											arrayList);*/
			
		/*	adatper=new ArrayAdapter(getApplicationContext(),
									android.R.layout.simple_list_item_1,
									arrayList);*/
			
		//	listView1.setAdapter(cultureAdapter);
			/*
			for(int i=0; i< str.length;i++){
				Log.d(TAG,str[i]);
			}
			*/
		}	
			
		}	
	

}
