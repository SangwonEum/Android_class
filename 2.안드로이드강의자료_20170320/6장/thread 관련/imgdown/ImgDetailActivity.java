package com.ch1.ex3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidprj.R;

public class ImgDetailActivity  extends Activity{
	private static final int IO_BUFFER_SIZE = 4 * 1024;
	TextView tView;
	ImageView imgView;
	Handler mHandler = new Handler();
	ImgInfo imgInfo;
	String imgSrc;
	String title;
	ProgressDialog dialog;
	
    // Data를 관리해주는 Adapter
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.img_detail);
	        tView=(TextView) findViewById(R.id.img_title);
	        imgView=(ImageView)findViewById(R.id.img_src);
	        dialog =new ProgressDialog(this);
	 
	        Bundle bundle=getIntent().getExtras();
	        imgInfo=(ImgInfo)bundle.getParcelable("imgInfo");
	        title=imgInfo.getTitle();
	        imgSrc=imgInfo.getImageSrc();
	        tView.setText(title);
	        
	        Log.d("TAG","title="+title);
	        Log.d("TAG","imgSrc="+imgSrc);
	        dialog.setTitle("이미지를 다운로드 받고 있습니다.");
	        dialog.show();
	        
	        new Thread() {
                public void run() {
                	Log.d("TAG","thread is run");
                	
                	Log.d("TAG","delayThread is run");
                    BufferedInputStream in;
                    BufferedOutputStream out;
                    try {
                        
                        in = new BufferedInputStream(new URL(imgSrc).openStream(), IO_BUFFER_SIZE);
                        final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
                        out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
                        copy(in, out);
                        out.flush();

                        final byte[] data = dataStream.toByteArray();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        final Drawable image = new BitmapDrawable(bitmap);
                        mHandler.post(new Runnable() {
                            public void run() {
                            	imgView.setImageDrawable(image);
                            	Log.d("TAG","img downloaded");
                            	dialog.dismiss();

                            }
                        });
                    } catch (Exception e) {
                        Log.e("Net", "Failed to grab image", e);
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
