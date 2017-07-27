package androidbook.ch09.httpsample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WebviewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        try {
        	
        	//Post 방식으로 전송
        	/*
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost();
            post.setURI(new URI("http://192.168.0.3:8090/androidTest/AndroidTest.jsp"));
            
            HttpResponse resp = client.execute(post);
            BufferedReader br = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
            */
        	
        	//get 방식으로 전송
        	HttpClient client = new DefaultHttpClient();
        	HttpGet get = new HttpGet();
        	get.setURI(new URI("http://192.168.0.3:8090/androidTest/AndroidTest.jsp"));
        	Log.d("MainActivity","setURI");
        	HttpResponse resp = client.execute(get);
        	BufferedReader br = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
        	
        	
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }
            br.close();
            Log.d("MainActivity",URLDecoder.decode(sb.toString(),"UTF-8"));
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(URLDecoder.decode(sb.toString(),"UTF-8"));
        } catch (Exception e) {
        }
    }
}
