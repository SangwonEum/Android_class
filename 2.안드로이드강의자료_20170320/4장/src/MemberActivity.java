package com.ch4a.ex2;

import com.ch1.ex3.ImgDetailActivity;
import com.example.androidprj.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MemberActivity  extends Activity{
	EditText eName,eAddress,eAge;
	String address;
	String name;
	int age;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mem_join);
        eName=(EditText) findViewById(R.id.name);
        eAddress=(EditText) findViewById(R.id.address);
        eAge=(EditText) findViewById(R.id.age);
        
	}
	
	public void regMember(View v){
		name=eName.getText().toString();
		address=eAddress.getText().toString();
		age=Integer.parseInt(eAge.getText().toString());
		
		Member member=new Member();
		member.setName(name);
		member.setAddress(address);
		member.setAge(age);
		
		
		Intent intent=new Intent(getApplicationContext(),MemDetailActivity.class);
    	intent.putExtra("member", member);
    	startActivity(intent);
	}
}
