package com.mytest.ch7a;

import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mytest.R;

public class SeoulActivity2 extends Activity implements OnItemClickListener{
	final int ITEM_NUM=10;
	String TAG="SeoulActivity2";
	ProgressDialog dialog;
	TextView tv;
	ListView listView1;
	CultureAdapter cultureAdapter;
	ArrayList arrayList;
	CultureVo vo; //VO values Object의 약자
	int pageNum=0;
	int begin=pageNum*ITEM_NUM+1;
	int end=pageNum*ITEM_NUM+ITEM_NUM;
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seoul_data);
		listView1=(ListView) findViewById(R.id.listView1);
		listView1.setOnItemClickListener(this);		
		init();
	}
	
	private void init(){
		dialog=new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("data received...");
		dialog.show();
		new BackgroundTask().execute();
	}
	
	public void receiveData(View view){
		pageNum++;
		begin=pageNum*ITEM_NUM+1;
		end=pageNum*ITEM_NUM+ITEM_NUM;
		
		dialog=new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("data received...");
		dialog.show();
		new BackgroundTask().execute();
	}
	
	//백그라운드에서 공공데이터를 xml로 받아 온다.
	private class BackgroundTask extends AsyncTask<String,String,String>{
		String urlData="http://openapi.seoul.go.kr:8088/6c6565627331323632303935/xml/ListCulturalAssetsInfo/";
		String _url=urlData+begin+"/"+end;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(SeoulActivity2.this,"Background start  before",Toast.LENGTH_LONG).show();
		}

		
		@Override
		protected String doInBackground(String... arg0) {
			String line="";
			String text="";
			try{
				Log.d("TAG","urlData: "+urlData);
				URL url=new URL(_url);
				XmlPullParser parser=XmlPullParserFactory.newInstance().newPullParser();
				parser.setInput(url.openStream(),null);
				int eventType=parser.getEventType();
				
				String tag;
				boolean end_tag=false;
				boolean inTitle=false;
				//while문을 돌면서 xml의 각 태그 안의 값을 text변수에 저장한다.
				while(eventType != XmlPullParser.END_DOCUMENT){
					switch(eventType){

		            case XmlPullParser.TEXT:
		                tag = parser.getName();
		                if (inTitle) { //각 <MANAGE_NUM>태그와 <NAME_KOR>의 값을 "-"로 연결해서 text 변수에 저장한다.
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
		                    inTitle = false; //xml의 <MANAGE_NUM> end tag에 도달했는지 설정
		                    
		                }
		                if (tag.compareTo("NAME_KOR") == 0) {
		                    inTitle = false;
		                    
		                }
		                break;
		            case XmlPullParser.START_TAG:
		                tag = parser.getName();
		                if (tag.compareTo("MANAGE_NUM") == 0) {
		                    inTitle = true; //xml의 <MANAGE_NUM> start tag에 도달했는지 설정
		                }
		                if (tag.compareTo("NAME_KOR") == 0) {
		                    inTitle = true;  //xml의 <NAME_KOR> start tag에 도달했는지 설정
		                    end_tag=true;
		                }
		                break;
		            }
		            eventType = parser.next();
					}
				
				publishProgress(text); //각 태그의 값을 text에 저장한 후 onProgressUpdate()로 보낸다.
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
			arrayList=new ArrayList<CultureVo>();
			
			String[] culture=null;
			
			super.onProgressUpdate(values);
			Log.d(TAG,values[0].toString());
			// <MANAGE_NUM>태그와 <NAME_KOR>의 값을 "-"로 연결해서 전달 받은 후 '-'를 구분자로 분리해서 
			//리스트에 표시해 준다.
			String str[]=values[0].split("-");
			Log.d(TAG,"str.length"+str.length);
			for(int i=0;i<str.length;i++){
				culture=str[i].split(",");
				Log.d(TAG,"문화재 번호  : " +culture[0]+",문화재이름:"+culture[1]);
				
				CultureVo vo=new CultureVo(culture[0],culture[1]);
				arrayList.add(vo);
			}
			
			cultureAdapter=new CultureAdapter(getApplicationContext(),
											R.layout.culture_item,
											arrayList);
			
		/*	adatper=new ArrayAdapter(getApplicationContext(),
									android.R.layout.simple_list_item_1,
									arrayList);*/
			
			listView1.setAdapter(cultureAdapter);
			/*
			for(int i=0; i< str.length;i++){
				Log.d(TAG,str[i]);
			}
			*/
			
			
		}
		
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		CultureVo vo=(CultureVo)arrayList.get(position);
		String manage_num=vo.getMagage_Num();
		Log.d(TAG,"문화재 번호 : "+manage_num);
		Intent intent=new Intent(view.getContext(),CultureDetailActivity.class);
		intent.putExtra("manage_num", manage_num);
		startActivity(intent);
	}

}
