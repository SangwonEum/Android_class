package androidbook.ch09.rssreader;

import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            StringBuilder sb = new StringBuilder();
            URL url = new URL("http://news.google.co.kr/news?pz=1&cf=all&ned=kr&hl=ko&output=rss");

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(url.openStream(), null);

            int eventType = parser.getEventType();
            String tag;
            boolean inTitle = false;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {

                case XmlPullParser.TEXT:
                    tag = parser.getName();
                    if (inTitle) {
                        sb.append(parser.getText()).append("\n");
                    }

                    break;
                case XmlPullParser.END_TAG:
                    tag = parser.getName();
                    if (tag.compareTo("title") == 0) {
                        inTitle = false;
                    }
                    break;
                case XmlPullParser.START_TAG:
                    tag = parser.getName();
                    if (tag.compareTo("title") == 0) {
                        inTitle = true;
                    }
                    break;
                }
                eventType = parser.next();
            }
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(sb.toString());
        } catch (Exception e) {
        }
    }
}