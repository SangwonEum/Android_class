package com.example.btest.ch7;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.btest.R;

public class WebViewActivity2  extends Activity{
	private WebView wv;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		 final ProgressDialog pDialog;
         pDialog = new ProgressDialog(WebViewActivity2.this);
         pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       //  progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         pDialog.setMessage("�ε� ��...");
         pDialog.setMax(100);
         pDialog.setCancelable(true);
         
         
		wv=(WebView) findViewById(R.id.webView);
		
		wv.setWebViewClient(new WebViewClient(){
			//���ο� url ���� �� ���� webview���� ���� �ش�.
			public boolean shouldOverridingUrlLoading(WebView view,String url){
				view.loadUrl(url);
				return true;
			}
			
			//���������� ����� �ε� �� ���� �ȴ�.
			@Override
			public void onPageStarted(WebView view, String url,Bitmap favcon){
				pDialog.show();
			}
			
			//�� �������� ����� �ε��� ������ ȣ��ȴ�.
			@Override
			public void onPageFinished(WebView view, String url){
				if(pDialog.isShowing()){
					pDialog.cancel();
				}
			}
			
			@Override
			public void onReceivedError(WebView view,int errorCode,String description,String  errMsg){
				Toast.makeText(getApplicationContext(),"������ �ε� ���� : "+errorCode +" errorMsg",Toast.LENGTH_SHORT).show();
				if(pDialog.isShowing()){
					pDialog.cancel();
				}
			}
		});
		
		wv.loadUrl("http://m.naver.com");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode==KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
			wv.goBack();
			return true; //��Ű�� ������ �� ���� �������� �̵��Ѵ�.
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
