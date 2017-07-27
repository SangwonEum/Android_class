package com.mytest.ch3d;




import com.mytest2.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MenuTestActivity1 extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main1);
        tv = (TextView)findViewById(R.id.testView);
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
			intent=new Intent(this,MenuTestActivity2.class);
		else if(id== R.id.btn2)
			intent=new Intent(this,MenuTestActivity3.class);
		
		startActivity(intent);
		
		
	}

}
