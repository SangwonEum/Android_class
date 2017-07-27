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
menu.add(0, 0, 0, "자바");
menu.add(0, 1, 0, "오라클");
menu.add(0, 2, 0, "JSP");
SubMenu sub01 = menu.addSubMenu(11, 11, 0, "모바일");
sub01.add(11, 12, 0, "안드로이드");
sub01.add(11, 13, 0, "아이폰");
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
tv.setText("오라클 메뉴 선택");
break;
case 2:
tv.setText("JSP 메뉴 선택");
break;
case 12:
tv.setText("안드로이드 선택");
break;
case 13:
tv.setText("아이폰 선택");
break;
}
return super.onOptionsItemSelected(item);
}  
}