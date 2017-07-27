package com.ch1.ex3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class TitleListView extends Activity {
	private static final int IO_BUFFER_SIZE = 4 * 1024;

	Handler mHandler = new Handler();
	private String title = "non";
    // Data를 관리해주는 Adapter
    private static  CustomAdapter mCustomAdapter = null;
    private static  ArrayList<ImgInfo> imgList = new ArrayList<ImgInfo>();
    private static ListView mListView = null;
    private Button mCountBt = null;
    
    private ImgInfo imgInfo;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list2);
        setLayout();
 
    }
 
    // ListView 안에 Item을 클릭시에 호출되는 Listener
    private AdapterView.OnItemClickListener mItemClickListener = new
            AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1,
                int position, long arg3) {
        	
        	imgInfo=imgList.get(position);
        	Intent intent=new Intent(getApplicationContext(),ImgDetailActivity.class);
        	intent.putExtra("imgInfo", imgInfo);
        	startActivity(intent);
        	
        }
    };
 
    // Custom Adapter
    class CustomAdapter extends BaseAdapter {
 
        private ViewHolder viewHolder = null;
        // 뷰를 새로 만들기 위한 Inflater
        private LayoutInflater inflater = null;
        private ArrayList<ImgInfo> sArrayList = new ArrayList<ImgInfo>();
 
        public CustomAdapter (Context c , ArrayList<ImgInfo> mList){
            inflater = LayoutInflater.from(c);
            this.sArrayList = mList;
        }
 
 
 
        @Override
        public int getCount() { 
            return sArrayList.size();
        }
 
        @Override
        public Object getItem(int arg0) {
            return null;
        }
 
        @Override
        public long getItemId(int arg0) {
            return 0;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	
            // ConvertView가 null 일 경우
            View v = convertView;
 
            if( v == null ){
                viewHolder = new ViewHolder();
                // View를 inflater 시켜준다.
                v = inflater.inflate(R.layout.row2, null);
                viewHolder.tView=(TextView)v.findViewById(R.id.data);
                v.setTag(viewHolder);
            }
 
            else {
                viewHolder = (ViewHolder)v.getTag();
            }
            //네트워크 쓰레드에서 받은 사진 제목리스트를 뷰에 세팅한다.
            ImgInfo info=sArrayList.get(position);
            viewHolder.tView.setText(info.getTitle());
          
            return v;
        }
    }
 
    class ViewHolder {
    	private TextView tView=null;
    }
    /*
     * Layout
     */
    private void setLayout(){
        mListView = (ListView) findViewById(R.id.main_listview);
        mCountBt = (Button) findViewById(R.id.main_text_button);
        mCountBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	downLoad();
            }
        });
 
    }
    
    
    public void downLoad(){
             new Thread() {
                 public void run() {

                     try {
                         URL text = new URL("http://api.flickr.com/services/feeds/photos_public.gne?id=26648248@N04&amp;lang=en-us&amp;format=atom");

                         XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                         XmlPullParser parser = parserCreator.newPullParser();

                         parser.setInput(text.openStream(), null);

                         String tag;
                         boolean inTitle = false;
                         int imgCount = 0;
                         int parserEvent = parser.getEventType();
                         while (parserEvent != XmlPullParser.END_DOCUMENT) {

                             switch (parserEvent) {
                             case XmlPullParser.TEXT:
                                 if (inTitle) {
                                     title = parser.getText();
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
                                 if (tag.compareTo("link") == 0) {
                                     String relType = parser.getAttributeValue(null, "rel");
                                     if (relType.compareTo("enclosure") == 0) {
                                         String encType = parser.getAttributeValue(null, "type");
                                         if (encType.startsWith("image/")) {
                                             final String imageSrc = parser.getAttributeValue(null, "href");
                                             Log.i("Net", "image source = " + imageSrc);
                                             final int curImageCount = ++imgCount;
                                             
                                             //xml을 파싱한 데이터를 VO에 세팅한다.		
                                             mHandler.post(new Runnable() {
                                                 public void run() {
                                                	 
                                                	 ImgInfo imgInfo=new ImgInfo();
                                                	 imgInfo.setTitle(title);
                                                	 imgInfo.setImageSrc(imageSrc);
                                                 	imgList.add(imgInfo);
                                                 }

                                             });
                                       

                                         }
                                     }
                                 }
                                 break;
                             }

                             parserEvent = parser.next();

                         }
                         mHandler.post(new Runnable() {
                             public void run() {
                                 
                            	 mCustomAdapter = new CustomAdapter(TitleListView.this , imgList);
                                 mListView.setAdapter(mCustomAdapter);
                                 mListView.setOnItemClickListener(mItemClickListener);
                            	 
                             }
                         });

                     } catch (Exception e) {
                         Log.e("Net", "Error in network call", e);
                     }
                 }
             }.start();


    }
    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }  
}
 