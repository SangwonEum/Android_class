package com.ch11.ex1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.util.Log;

import com.example.androidprj.R;

public class ConReadActivity  extends Activity{
	 Cursor cursor=null;
		String contactId = "";
		String displayName = "";
		String data ="";
		int type = -1;
		int dataColumn = -1;
		int typeColumn = -1;
		int numColumn=-1;
		String name="";
		String phoneNum="";
		
		
		String MobileNumber = "";
		String HomeNumber = "";
		String WorkNumber = "";
		String WorkFaxNumber = "";
		String emailID = "";
		String WorkemailID = "";
		String WorkName = "";
		String WorkPosition = "";
		Uri dataUri=null;
	private static String[] mProjections = new String[] { 
							ContactsContract.CommonDataKinds.Phone._ID
							,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
							,ContactsContract.CommonDataKinds.Phone.DATA2
							};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cont_main);
        /*
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);*/
        //startActivityForResult(intent, ATData.REQUEST_CONTACT_PICK);
		/*        
        try {
        	dataUri = intent.getData();
            cursor =  managedQuery(dataUri, null, null, null, null);
            
            cursor.moveToFirst();         
            contactId= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
// 1. 사용자 이름 저장
            displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 
        } finally {
            cursor.close();
        }    
*/
        // 2. 폰 정보 읽기 
       /* Cursor phoneCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, 
                null,
                ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
                new String[]{contactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE},  // where param 
                null);*/
/*        ContentResolver contentResolver = getContentResolver();
        Cursor phoneCursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, mProjections, null,
                null, null);
        
        try {
            if (phoneCursor.moveToFirst()) {
                dataColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA); 
                typeColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);

            	dataColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID); 
    		    typeColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
    		//    numColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
    		    
                do {
                    data = phoneCursor.getString(dataColumn);
                    type = phoneCursor.getInt(typeColumn);
                    Log.d("TAG111", "Phone Info : "+ data + " / type : "+ type);
                    switch(type){
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
                        // 회사 전화 번호 
                        if (data != null && data.length() > 0){
                           WorkNumber = data;
                        }
                        Log.d("TAG","전화번호 : "+WorkNumber);
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
                        // 회사 팩스 번호
                        if (data != null && data.length() > 0){
                            WorkFaxNumber = data;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                        // 휴대폰 번호
                        if (data != null && data.length() > 0){
                            MobileNumber = data;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                        // 집전화 번호
                        if (data != null && data.length() > 0){
                            HomeNumber = data;
                        }
                        break;
                    }
                    
                }while(phoneCursor.moveToNext());
                
            }
        }finally {
            phoneCursor.close();
        }
		*/
		 
	/*	try {
			  
			  ContentResolver contentResolver = getContentResolver();
		        //주소록에서 주소를 조회한다.
		         cursor = contentResolver.query(
		                ContactsContract.Contacts.CONTENT_URI, mProjections, null,
		                null, null);
		        
		        cursor.moveToFirst(); 
		contactId= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
		//1. 사용자 이름 저장
		displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 
		} finally {
		cursor.close();
		}    */
		
		// 2. 폰 정보 읽기 
	/*	Cursor phoneCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, 
				mProjections,
		    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
		    new String[]{contactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE},  // where param 
		    null);*/
		
		Cursor phoneCursor = getContentResolver().query(
	                ContactsContract.Contacts.CONTENT_URI, mProjections, null,
	                null, null);
		try {
		if (phoneCursor.moveToFirst()) {
		    dataColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID); 
		    typeColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		    numColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);
		
		    do {
		        data = phoneCursor.getString(dataColumn);
		        name = phoneCursor.getString(typeColumn);
		   //     phoneNum=phoneCursor.getString(numColumn);
		        
		        Log.d("TAG", "Phone ID : "+ data + " / name : "+ name );
		        switch(numColumn){
		        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
		        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
		            // 회사 전화 번호 
		            if (data != null && data.length() > 0){
		               WorkNumber = data;
		               
		               Log.d("TAG","전하번호 : "+phoneNum);
		            }
		            break;
		        case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
		            // 회사 팩스 번호
		            if (data != null && data.length() > 0){
		                WorkFaxNumber = data;
		            }
		            break;
		        case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
		            break;
		        case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
		            // 휴대폰 번호
		            if (data != null && data.length() > 0){
		                MobileNumber = data;
		            }
		            break;
		        case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
		            // 집전화 번호
		            if (data != null && data.length() > 0){
		                HomeNumber = data;
		            }
		            break;
		        }
		        
		    }while(phoneCursor.moveToNext());
		    
		}
		}finally {
		phoneCursor.close();
		}
		
	/*	
	         try {
            cursor.moveToFirst();         
            contactId= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
// 1. 사용자 이름 저장
            displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 
        } finally {
            cursor.close();
        }    

        // 2. 폰 정보 읽기 
        Cursor phoneCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, 
                null,
                ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
                new String[]{contactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE},  // where param 
                null);
        try {
            if (phoneCursor.moveToFirst()) {
                dataColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA); 
                typeColumn = phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);

                do {
                    data = phoneCursor.getString(dataColumn);
                    type = phoneCursor.getInt(typeColumn);
                    ATLog.d(this, "Phone Info : "+ data + " / type : "+ type);
                    switch(type){
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
                        // 회사 전화 번호 
                        if (data != null && data.length() > 0){
                           WorkNumber = data;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
                        // 회사 팩스 번호
                        if (data != null && data.length() > 0){
                            WorkFaxNumber = data;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                        // 휴대폰 번호
                        if (data != null && data.length() > 0){
                            MobileNumber = data;
                        }
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                        // 집전화 번호
                        if (data != null && data.length() > 0){
                            HomeNumber = data;
                        }
                        break;
                    }
                    
                }while(phoneCursor.moveToNext());
                
            }
        }finally {
            phoneCursor.close();
        }
     
	  // 3. 이메일 정보 읽기
		Cursor emailCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, 
		    null,
		    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
		    new String[]{contactId, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},  // where param 
		    null);
		
		try {
		if (emailCursor.moveToFirst()) {
		    dataColumn = emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA); 
		    typeColumn = emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.TYPE);
		
		    do {
		        data = emailCursor.getString(dataColumn);
		        type = emailCursor.getInt(typeColumn);
		        
		        //
		        switch(type){
		        case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
		            // 개인 이메일
		            if (data != null && data.length() > 0){
		                emailID = data;
		            }
		             break;
		        case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
		            // 회사 이메일
		            if (data != null && data.length() > 0){
		                WorkemailID = data;
		            }
		             break;
		        }
		        
		    }while(emailCursor.moveToNext());
		}
		}finally {
		emailCursor.close();
		}
		
		// 4. 조직 정보 읽기
		Cursor organizationCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, 
		    null,
		    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
		    new String[]{contactId, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE},  // where param 
		    null);
		
		try {
		if (organizationCursor.moveToFirst()) {
		    dataColumn = organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.DATA); 
		    typeColumn = organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.TYPE);
		    String position; // 직급
		    int positionColumn = organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.TITLE);
		    
		    do {
		        data = organizationCursor.getString(dataColumn); // 조직명
		        type = organizationCursor.getInt(typeColumn);
		        position = organizationCursor.getString(positionColumn); // 직급명
		    
		        // 조직은 무조건 회사로 취급한다.
		        switch(type){
		        case ContactsContract.CommonDataKinds.Organization.TYPE_WORK:
		            // 조직 정보
		            if (data != null && data.length() > 0){
		                WorkName = data;
		                WorkPosition = position;
		            }
		             break;
		        }
		        
		    }while(organizationCursor.moveToNext());
		}
		}finally {
		organizationCursor.close();
		}        
		
		// 5. 주소 정보 가져오기
		Cursor addressCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, 
		    null,
		    ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",     // where
		    new String[]{contactId, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE},  // where param 
		    null);
		
		try {
		if (addressCursor.moveToFirst()) {
		    typeColumn = addressCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredPostal.TYPE);
		    
		    do {
		        type = addressCursor.getInt(typeColumn);
		        String poBox = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
		         String street = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
		         String city = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
		         String state = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
		         String postalCode = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
		         String country = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
		        
		       
		        switch(type){
		        case ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK:
		         
		             break;
		        }
		        
		    }while(addressCursor.moveToNext());
		}
		}finally {
		addressCursor.close();
		}      */  
	}
}

