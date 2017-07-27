package com.example.btest.ch7;

import java.net.URLDecoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.btest.R;


public class WebViewActivity3  extends Activity{
	private WebView wv;
	private TextView mTextView;
	private Button btn_send;
	 
	@SuppressLint("JavascriptInterface")
	@JavascriptInterface
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview3);
						
		mTextView=(TextView) findViewById(R.id.mTextView);
		btn_send=(Button) findViewById(R.id.btn_send);
		
		wv=(WebView) findViewById(R.id.webView3);
		wv.getSettings().setJavaScriptEnabled(true); //자바스크립트 연동 여부 설정
		wv.getSettings().setBuiltInZoomControls(true);//멀티터치 가능여부 설정(확대,축소)
		wv.addJavascriptInterface(new AndroidBridge(), "android");
		
		wv.loadUrl("http://192.168.0.100:8091/webApp/script.html");
		wv.setWebViewClient(new WebViewClient(){
			//새로운 url 접속 시 기존 webview에서 보여 준다.
			public boolean shouldOverridingUrlLoading(WebView view,String url){
				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode==KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
			wv.goBack();
			return true; //백키를 눌렀을 때 이전 페이지로 이동한다.
		}
		return super.onKeyDown(keyCode, event);
	}
	
	//웹페이지와 연동하는 클래스를 지정한다.
	 private class AndroidBridge {
		 private Handler handler = new Handler() {
		    };
		    
	        public void callAndroid(final String msg) { // must be final
	            handler.post(new Runnable() {
	                public void run() {
	                    String sendMsg = URLDecoder.decode(msg);
	                    Log.d("TAG", "Bridge Msg = "+sendMsg);
	                    mTextView.setText(sendMsg);
	                }
	            });
	        }
	    }
   //안드로이드에서 웹페이지와 연동하는 메소드
	 public void sendMessage(View view){
		 //자바스크립트 함수를 호출하려면  WebChromeClient 객체를 세팅해야 한다.
		 wv.setWebChromeClient( new WebChromeClient());
		 wv.loadUrl("javascript:communicate('Hello from Android')");
		 
		// wv.loadUrl( "javascript:alert('test', 'vvvv');");
	 }
}
