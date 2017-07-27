package com.ch11.ex1;

import java.util.ArrayList;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class ConInsertActivity  extends Activity{
	
	 String DisplayName = "이순신";
     String MobileNumber = "010-111-2222";
     String HomeNumber = "02-121-1212";
     String WorkNumber = "02-222-2222";
     String emailID = "email@mail.com";
     String company = "jweb";
     String jobTitle = "부장";

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.cont_main);
	     // 기본 주소록에 저장
	    ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
	            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
	            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
	            .build());
	
	    // 이름 입력
	    if(DisplayName != null)
	    {
	        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	                .withValue(ContactsContract.Data.MIMETYPE,
	                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
	                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, DisplayName).build());
	    }                       
	    //휴대폰 번호 입력
	    if(MobileNumber != null)
	    {
	        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	                .withValue(ContactsContract.Data.MIMETYPE,
	                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
	                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
	                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, 
	                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
	                .build());
	    }
	
	    //집전화 입력
	    if(HomeNumber != null)
	    {
	        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	                .withValue(ContactsContract.Data.MIMETYPE,
	                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
	                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
	                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, 
	                        ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
	                .build());
	    }
	
	    //직장 번호 입력
	    if(WorkNumber != null)
	    {
	        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	                .withValue(ContactsContract.Data.MIMETYPE,
	                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
	                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
	                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, 
	                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
	                .build());
	    }
	
	    // 이메일
	    if(emailID != null)
	    {
	         ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	                    .withValue(ContactsContract.Data.MIMETYPE,
	                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
	                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
	                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
	                    .build());
	    }
	
	    //직장 정보
	    if(!company.equals("") && !jobTitle.equals(""))
	    {
	        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
	                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	                .withValue(ContactsContract.Data.MIMETYPE,
	                        ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
	                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
	                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
	                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
	                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
	                .build());
	    }
	     
	    try 
	    {
	        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
	    } 
	    catch (Exception e) 
	    {               
	        e.printStackTrace();
	        Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
	    }
     }
}
