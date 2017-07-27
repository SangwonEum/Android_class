
package com.mytest.ch3d;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BaseActivity extends Activity {
	protected  static final int        MENU_ORACLE   = Menu.FIRST + 1; 
	protected static final  int        MENU_JAVA          = MENU_ORACLE + 1; 
	protected static final  int        MENU_ANDROID         = MENU_JAVA + 1; 
	protected static final  int        MENU_JSP               = MENU_ANDROID + 1; 
	TextView tv;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	//public abstract MenuItem add (int groupId, int itemId, int order, CharSequence title)
		//�޴� �׸��� �����Ѵ�.(�׷���̵�,�����۾��̵�,����,�޴�����)
		menu.add(0, MENU_ORACLE, 0, "�ڹ�"); 
		menu.add(0, MENU_JAVA, 0, "�ȵ���̵�");
		//menu.add(0, 1, 0, "�ȵ���̵�").setIcon(R.drawable.ic_launcher);
		menu.add(0, MENU_ANDROID, 0, "JSP");
		menu.add(0, MENU_JSP, 0, "Oracle");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	int choiceItem = item.getItemId();
	switch(choiceItem){
	case 0:
	tv.setText("�ڹ� �޴� ����");
	break;
	case 1:
	tv.setText("�ȵ���̵� �޴� ����");
	break;
	case 2:
	tv.setText("JSP �޴� ����");
	break;
	case 3:
	tv.setText("Oracle �޴� ����");
	break;
	}
	return super.onOptionsItemSelected(item);
	}    
}