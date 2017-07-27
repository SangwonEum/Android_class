package com.ch3;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MenuTestActivity extends Activity {
	TextView tv;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.menu_main);
	        
	        tv = (TextView)findViewById(R.id.testView);
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	//public abstract MenuItem add (int groupId, int itemId, int order, CharSequence title)
		//메뉴 항목을 설정한다.(그룹아이디,아이템아이디,순서,메뉴제목)
	menu.add(0, 0, 0, "자바"); 
	menu.add(0, 1, 0, "안드로이드");
	//menu.add(0, 1, 0, "안드로이드").setIcon(R.drawable.ic_launcher);
	menu.add(0, 2, 0, "JSP");
	menu.add(0, 3, 0, "Oracle");
	return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	int choiceItem = item.getItemId();
	switch(choiceItem){
	case 0:
	tv.setText("자바 메뉴 선택");
	break;
	case 1:
	tv.setText("안드로이드 메뉴 선택");
	break;
	case 2:
	tv.setText("JSP 메뉴 선택");
	break;
	case 3:
	tv.setText("Oracle 메뉴 선택");
	break;
	}
	return super.onOptionsItemSelected(item);
	}    
}