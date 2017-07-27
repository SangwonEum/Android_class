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
		//�޴� �׸��� �����Ѵ�.(�׷���̵�,�����۾��̵�,����,�޴�����)
	menu.add(0, 0, 0, "�ڹ�"); 
	menu.add(0, 1, 0, "�ȵ���̵�");
	//menu.add(0, 1, 0, "�ȵ���̵�").setIcon(R.drawable.ic_launcher);
	menu.add(0, 2, 0, "JSP");
	menu.add(0, 3, 0, "Oracle");
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