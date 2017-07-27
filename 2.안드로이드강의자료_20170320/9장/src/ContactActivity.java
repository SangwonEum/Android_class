package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cont_main);
        
        //주소록의 아이디를 가지고 온다.
        Cursor c=getContentResolver().query(
        		ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        
        while(c.moveToNext()){
        	String contactId=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
        	String name=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        	Log.d("TAG"," 이름 : "+name + " ,_ID : "+contactId);
        	
        	String hasPhone=c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        	
        	if(Integer.parseInt(hasPhone)==1){
        		//아이디로 전화번호 정보를 가지고 온다.
        		Cursor phoneCursor=getContentResolver().query(
        				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        				null,
        				ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        				+"='"+contactId+"'",
        				null,null);
        		
        		while(phoneCursor.moveToNext()){
        			String number=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        			String numberType=phoneCursor
        					.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
        			
        			switch(Integer.parseInt(numberType)){
        				default:
        					Log.d("TAG","전화번호: "+number+" 타입: "+numberType);
        					break;
        			}
        		}
        			
        	}else{        		
    			Log.d("TAG","전화번호가 없습니다.");
        	}
        	//아이디로 이메일 정보를 가지고 온다.
        	Cursor emailCursor=getContentResolver().query(
        			ContactsContract.CommonDataKinds.Email.CONTENT_URI,
        			new String[] {ContactsContract.CommonDataKinds.Email.DATA,
        					ContactsContract.CommonDataKinds.Email.TYPE},
        					ContactsContract.CommonDataKinds.Email.CONTACT_ID+"='"
        					+contactId+"'",null,null);
        	
        	while(emailCursor.moveToNext()){
        		String email=emailCursor
        				.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
        		String emailType=emailCursor
        				.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
        		
        		switch(Integer.parseInt(emailType)){
        		case 1:
        			Log.d("TAG","개인 메일주소: "+email);
        			break;
        		case 2:
        			Log.d("TAG","직장 메일주소: "+email);
        			break;
        		case 3:
        			Log.d("TAG","기타 메일주소: "+email);
        		case 4:
        			Log.d("TAG","스마트폰 메일주소:"+email);
        		case 5:
        			Log.d("TAG","고객 메일주소:"+email);
        			break;
        			
        		}
        	}
        	emailCursor.close();
        }
        c.close();
        
        
	}
}
