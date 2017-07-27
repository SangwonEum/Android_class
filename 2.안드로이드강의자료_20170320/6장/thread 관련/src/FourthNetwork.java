package com.androidbook.networking;

import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class FourthNetwork extends Activity {
    Handler mHandler = new Handler();
    static String tag="";
    static String relType="";
    static String encType="";
    static String imageSrc="";
    static String data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.network_main);

        final TextSwitcher status = (TextSwitcher) findViewById(R.id.status);
        status.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                TextView tv = new TextView(FourthNetwork.this);
                tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tv.setTextSize(24);
                return tv;
            }

        });

        status.setText("데이터 없음");
        Button go = (Button) findViewById(R.id.do_action);
        go.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
                new Thread() {
                    public void run() {

                        try {
                            URL text = new URL("http://api.flickr.com/services/feeds/photos_public.gne?id=26648248@N04&amp;lang=en-us&amp;format=atom");

                            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = parserCreator.newPullParser();

                            parser.setInput(text.openStream(), null);
                           // Log.e("Net",parser.toString());
                            
                            //쓰레드 실행중에 화면에 상태를 표시한다.
                            mHandler.post(new Runnable() {
                                public void run() {
                                    status.setText("Parsing...");
                                }
                            });
                            int imgCount = 0;
                            int parserEvent = parser.getEventType();
                            
                            
                            final String curType=relType;
                            final String curImageSrc=imageSrc;
                            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                            	//XML 데이터를 파싱한다.
                                switch (parserEvent) {
                                case XmlPullParser.START_TAG:
                                    tag = parser.getName();
                                    if (tag.compareTo("link") == 0) {
                                        relType = parser.getAttributeValue(null, "rel");
                                        if (relType.compareTo("enclosure") == 0) {
                                            encType = parser.getAttributeValue(null, "type");
                                            if (encType.startsWith("image/")) {
                                               imageSrc = parser.getAttributeValue(null, "href");
                                                Log.i("Net", "image source = " + imageSrc);
                                                //파싱한 데이터를 변수에 저장한다.
                                                data+="rel: "+relType +"\n"  
                            							+",type: "+encType+"\n" 
                            							+"href : "+imageSrc+"\n";
                                                
                                            }
                                        }
                                    }
                                    break;
                                }

                                parserEvent = parser.next();

                            }
                            //while문을 종료한 후 화면에 전송받은 데이터를 표시한다.
                            mHandler.post(new Runnable() {
                                public void run() {
                                	status.setText(data);
                                }
                            });

                        } catch (Exception e) {
                            Log.e("Net", "Error in network call", e);
                        }
                    }
                }.start();

            }

        });
    }
}
