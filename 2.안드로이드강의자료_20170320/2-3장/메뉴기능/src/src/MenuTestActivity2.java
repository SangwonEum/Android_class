package com.mytest.ch3d;



import com.mytest2.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MenuTestActivity2 extends BaseActivity {
	private  static final  int        MENU_IPHONE       = MENU_JSP + 1; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main2);
        tv = (TextView)findViewById(R.id.testView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//���� �޴��� �ٸ� �׸��� �߰��ؼ� ����Ѵ�.
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_IPHONE, 0, "iPhone");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	public void onFinishClicked(View v){
		finish();
		
	}

}
