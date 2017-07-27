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
         pDialog.setMessage("로딩 중...");
         pDialog.setMax(100);
         pDialog.setCancelable(true);
         
         
		wv=(WebView) findViewById(R.id.webView);
		
		wv.setWebViewClient(new WebViewClient(){
			//새로운 url 접속 시 기존 webview에서 보여 준다.
			public boolean shouldOverridingUrlLoading(WebView view,String url){
				view.loadUrl(url);
				return true;
			}
			
			//웹페이지가 웹뷰로 로딩 시 실행 된다.
			@Override
			public void onPageStarted(WebView view, String url,Bitmap favcon){
				pDialog.show();
			}
			
			//웹 페이지가 웹뷰로 로딩이 끝나면 호출된다.
			@Override
			public void onPageFinished(WebView view, String url){
				if(pDialog.isShowing()){
					pDialog.cancel();
				}
			}
			
			@Override
			public void onReceivedError(WebView view,int errorCode,String description,String  errMsg){
				Toast.makeText(getApplicationContext(),"페이지 로딩 실패 : "+errorCode +" errorMsg",Toast.LENGTH_SHORT).show();
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
			return true; //백키를 눌렀을 때 이전 페이지로 이동한다.
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
