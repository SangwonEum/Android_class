package com.ch11.ex1;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.androidprj.R;

public class ConReadActivity2 extends Activity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cont_main);
        
        //주소 정보의 가지고 온다.
        ContentResolver cr=getContentResolver();
        Cursor c=cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        
        while(c.moveToNext()){
        	//그룹
        	int group=c.getInt(c.getColumnIndex(ContactsContract.Contacts.IN_VISIBLE_GROUP));
        	
        	if(group !=1)
        		continue;
        	//ID
        	String id=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
        	Log.d("TAG","ID : "+id);
        	//표시명
        	String name=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        	Log.d("TAG","이름 : "+name);
        	//사진
        	int photoID=c.getInt(c.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
        	Bitmap icon=null;
        	
        	if(photoID !=0){
        		Uri uri=ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.parseLong(id));
        		InputStream in=ContactsContract.Contacts.openContactPhotoInputStream(cr,uri);
        		icon=BitmapFactory.decodeStream(in);
        	}
        		//전화
        		String dial1="";
        		if(Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) >0){
        			Cursor cp=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        								null,
        								ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
        								new String[] {id},null);
        			while(cp.moveToNext()){
        				dial1=cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA1));
        				Log.d("TAG","전화번호 : "+dial1);
        			}
        			cp.close();
        		}
        		
        		//메일
        		String mail="";
        		Cursor cm=cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,
        				ContactsContract.CommonDataKinds.Email.CONTACT_ID+"=?",new String[] {id},null);
        		while(cm.moveToNext()){
        			mail=cm.getString(cm.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
        			Log.d("TAG","메일주소 :"+mail);
        		}
        		cm.close();
        	
        }
	}

}
