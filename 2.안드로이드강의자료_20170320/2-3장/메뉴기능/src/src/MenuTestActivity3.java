package com.mytest.ch3d;




import com.mytest2.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MenuTestActivity3 extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main3);
        tv = (TextView)findViewById(R.id.testView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//상위 메뉴를 먼저 생성한 후 항목을 지운다.
		super.onCreateOptionsMenu(menu);
		menu.removeItem(MENU_JSP);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	public void onBtnFinishClicked(View v){
		finish();
	}
}
