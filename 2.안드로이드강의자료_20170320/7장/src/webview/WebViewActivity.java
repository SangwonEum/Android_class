package com.example.btest.ch7;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.btest.R;

public class WebViewActivity  extends Activity{
	private WebView wv;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		wv=(WebView) findViewById(R.id.webView);
		wv.loadUrl("http://m.naver.com");
		wv.setWebViewClient(new WebViewClient(){
			//���ο� url ���� �� ���� webview���� ���� �ش�.
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
			return true; //��Ű�� ������ �� ���� �������� �̵��Ѵ�.
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
