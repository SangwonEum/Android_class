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
		wv.getSettings().setJavaScriptEnabled(true); //�ڹٽ�ũ��Ʈ ���� ���� ����
		wv.getSettings().setBuiltInZoomControls(true);//��Ƽ��ġ ���ɿ��� ����(Ȯ��,���)
		wv.addJavascriptInterface(new AndroidBridge(), "android");
		
		wv.loadUrl("http://192.168.0.100:8091/webApp/script.html");
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
	
	//���������� �����ϴ� Ŭ������ �����Ѵ�.
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
   //�ȵ���̵忡�� ���������� �����ϴ� �޼ҵ�
	 public void sendMessage(View view){
		 //�ڹٽ�ũ��Ʈ �Լ��� ȣ���Ϸ���  WebChromeClient ��ü�� �����ؾ� �Ѵ�.
		 wv.setWebChromeClient( new WebChromeClient());
		 wv.loadUrl("javascript:communicate('Hello from Android')");
		 
		// wv.loadUrl( "javascript:alert('test', 'vvvv');");
	 }
}
