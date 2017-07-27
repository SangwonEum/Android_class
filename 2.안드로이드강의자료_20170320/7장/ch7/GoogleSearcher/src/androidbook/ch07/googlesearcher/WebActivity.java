package androidbook.ch09.googlesearcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WebActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        try {
            //URL url = new URL("https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=google");
        	URL url = new URL("http://localhost:8090/androidTest/androidTest.jsp");
            URLConnection connection = url.openConnection();

            String line;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());
            JSONObject responseData = (JSONObject) json.get("responseData");
            JSONArray results = (JSONArray) responseData.get("results");
            sb.setLength(0);
            for (int i = 0; i < results.length(); i++) {
                JSONObject result = (JSONObject) results.get(i);
                sb.append(result.get("title")).append("\n\n");
            }
            
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(sb.toString());
        } catch (Exception e) {
        }
    }
}