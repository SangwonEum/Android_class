package com.ch3.lst;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerTest  extends Activity implements OnItemSelectedListener{
	Spinner spinner=null;
	String[] item=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.spin_main);
	    item=getResources().getStringArray(R.array.planets_array);//string.xml에서 문자열 배열을 가지고 온다.

	    spinner = (Spinner) findViewById(R.id.spinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.planets_array, android.R.layout.simple_spinner_item);    

	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
	    spinner.setAdapter(adapter);
	    spinner.setOnItemSelectedListener(this);
	    
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		Toast.makeText(this,"선택한 행성이름 : " +item[arg2], Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}	

}
