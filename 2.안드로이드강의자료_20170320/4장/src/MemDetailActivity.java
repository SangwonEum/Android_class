package com.ch4a.ex2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ch1.ex3.ImgInfo;
import com.example.androidprj.R;

public class MemDetailActivity  extends Activity{
	TextView tName,tAddress,tAge;
	Member member;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mem_detail);
        tName=(TextView) findViewById(R.id.tName);
        tAddress=(TextView) findViewById(R.id.tAddress);
        tAge=(TextView) findViewById(R.id.tAge);
        
 
        Bundle bundle=getIntent().getExtras();
        member=(Member)bundle.getParcelable("member");
        
        tName.setText(member.getName());
        tAddress.setText(member.getAddress());
        tAge.setText(Integer.toString(member.getAge()));
	}
}
