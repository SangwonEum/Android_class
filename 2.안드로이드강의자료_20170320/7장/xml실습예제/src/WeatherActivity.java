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
//������� ����� EditText
EditText editText;
String xml; //�ٿ�ε�� xml����
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        editText = (EditText)findViewById(R.id.editText);
        
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
         Toast.makeText(WeatherActivity.this, "BackgroundTask ������",
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
    	    //�������� �о���� ���� ��Ʈ�� ��ü
    	    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
    	    //�ٴ����� �о���� ���� BufferReader�� ���Ѵ�.
    	    BufferedReader br = new BufferedReader(isr);
    	    //�ݺ��� ���鼭�о����
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
    	    //����� ����غ���
    	    //editText.setText(sBuffer.toString());
    	    xml = sBuffer.toString(); //����� ������ ���
    	    Log.d("TAG",xml);
    	    publishProgress(xml); 
    	    }catch (Exception e) {
    	// TODO: handle exception
    	    Log.e("�ٿ�ε� �� ���� �߻�",e.getMessage());
    	   
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

    
    //xml�Ľ��ϴ� �޼ҵ�
    public String  parse(String data){
    	String sData =null;
    try{
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    //xml�� InputStream���·� ��ȯ
    InputStream is = new ByteArrayInputStream(data.getBytes());
    //document�� element �� w3c dom�� �ִ°��� ����Ʈ �Ѵ�.
    Document doc = documentBuilder.parse(is);
    Element element = doc.getDocumentElement();
    //�о�� �±׸� ���ϱ�
    NodeList items = element.getElementsByTagName("tmx"); //���ú� �ְ� �µ�
    //�о�� �ڷ��� ��
    int n = items.getLength();
    //�ڷḦ ������ų stringBuffer ��ü
    
    //�ݺ����� ���鼭 ��� ������ �о����
    for(int i=0 ; i < n ; i++){
    //�о�� �ڷῡ�� �˰� ���� ���ڿ��� �ε��� ��ȣ�� �����Ѵ�.
    Node item = items.item(i);
    Node text = item.getFirstChild();
    //�ش� ��忡�� ���ڿ� �о����
    String itemValue = text.getNodeValue();
    sData+=itemValue+"\r\n";
   
     }
    //�о�� ���ڿ� ����غ���
 //   editText.setText(sBuffer.toString());
   
    }catch (Exception e) {
// TODO: handle exception
    Log.e("�Ľ� �� ���� �߻�", e.getMessage());
    }
    return sData;
    }
}
