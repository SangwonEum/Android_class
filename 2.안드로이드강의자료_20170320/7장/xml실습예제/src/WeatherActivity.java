package com.ch6;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ch7.GoogleActivity;
import com.example.androidprj.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends Activity {
	 ProgressDialog dialog;
//결과값을 출력할 EditText
EditText editText;
String xml; //다운로드된 xml문서
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        editText = (EditText)findViewById(R.id.editText);
        
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
         Toast.makeText(WeatherActivity.this, "BackgroundTask 시작전",
                 Toast.LENGTH_SHORT).show();
     }

     @Override
     protected String doInBackground(String... datas) {
    	 StringBuffer sBuffer = new StringBuffer();
    	    try{
    	    String urlAddr = " http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109 ";
    	    URL url = new URL(urlAddr);
    	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	    if(conn != null){
    	    conn.setConnectTimeout(20000);
    	    conn.setUseCaches(false);
    	    if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
    	    //서버에서 읽어오기 위한 스트림 객체
    	    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
    	    //줄단위로 읽어오기 위해 BufferReader로 감싼다.
    	    BufferedReader br = new BufferedReader(isr);
    	    //반복문 돌면서읽어오기
    	    while(true){
    	    String line = br.readLine();
    	    if(line==null){
    	    break;
    	    }
    	    sBuffer.append(line);
    	    }
    	    br.close();
    	    conn.disconnect();
    	    }
    	    }
    	    //결과값 출력해보기
    	    //editText.setText(sBuffer.toString());
    	    xml = sBuffer.toString(); //결과값 변수에 담기
    	    Log.d("TAG",xml);
    	    publishProgress(xml); 
    	    }catch (Exception e) {
    	// TODO: handle exception
    	    Log.e("다운로드 중 에러 발생",e.getMessage());
    	   
    	}
    	    return xml;
     }

     @Override
     protected void onProgressUpdate(String... data) {
    	 Log.d("TAG",data[0]);
    	 String xmlData=parse(data[0]);
    	editText.setText(xmlData.toString());
    	 //editText.setText(data[0].toString());
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

    
    //xml파싱하는 메소드
    public String  parse(String data){
    	String sData =null;
    try{
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    //xml을 InputStream형태로 변환
    InputStream is = new ByteArrayInputStream(data.getBytes());
    //document와 element 는 w3c dom에 있는것을 임포트 한다.
    Document doc = documentBuilder.parse(is);
    Element element = doc.getDocumentElement();
    //읽어올 태그명 정하기
    NodeList items = element.getElementsByTagName("tmx"); //도시별 최고 온도
    //읽어온 자료의 수
    int n = items.getLength();
    //자료를 누적시킬 stringBuffer 객체
    
    //반복문을 돌면서 모든 데이터 읽어오기
    for(int i=0 ; i < n ; i++){
    //읽어온 자료에서 알고 싶은 문자열의 인덱스 번호를 전달한다.
    Node item = items.item(i);
    Node text = item.getFirstChild();
    //해당 노드에서 문자열 읽어오기
    String itemValue = text.getNodeValue();
    sData+=itemValue+"\r\n";
   
     }
    //읽어온 문자열 출력해보기
 //   editText.setText(sBuffer.toString());
   
    }catch (Exception e) {
// TODO: handle exception
    Log.e("파싱 중 에러 발생", e.getMessage());
    }
    return sData;
    }
}
