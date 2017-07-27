package com.atest.ch3a;



import com.atest.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	public void onStartActivity(View v){
		Intent intent=null;
		int id=v.getId();
		if(id==R.id.btn1)
			intent=new Intent(this,DialogSample06.class);
		else if(id== R.id.btn2)
			intent=new Intent(this,DialogSample07.class);
		
		startActivity(intent);
		
		
	}

}
