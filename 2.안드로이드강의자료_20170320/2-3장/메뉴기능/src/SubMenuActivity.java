package com.ch3;

import com.example.androidprj.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubMenuActivity extends Activity {
TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        
        tv = (TextView)findViewById(R.id.testView);
    }
    
@Override
public boolean onCreateOptionsMenu(Menu menu) {
menu.add(0, 0, 0, "�ڹ�");
menu.add(0, 1, 0, "����Ŭ");
menu.add(0, 2, 0, "JSP");
SubMenu sub01 = menu.addSubMenu(11, 11, 0, "�����");
sub01.add(11, 12, 0, "�ȵ���̵�");
sub01.add(11, 13, 0, "������");
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
tv.setText("����Ŭ �޴� ����");
break;
case 2:
tv.setText("JSP �޴� ����");
break;
case 12:
tv.setText("�ȵ���̵� ����");
break;
case 13:
tv.setText("������ ����");
break;
}
return super.onOptionsItemSelected(item);
}  
}