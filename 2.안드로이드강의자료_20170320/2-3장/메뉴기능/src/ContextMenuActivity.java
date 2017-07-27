package com.ch3;

import com.example.androidprj.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContextMenuActivity extends Activity {
	TextView tv;
	Button colorBtn;
	Button sizeBtn;
	ImageView image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu);
        
        image=(ImageView)findViewById(R.id.image);
        tv = (TextView)findViewById(R.id.testView);
        colorBtn = (Button)findViewById(R.id.colorBtn);
        sizeBtn = (Button)findViewById(R.id.sizeBtn);
        
        //���ؽ�Ʈ �޴��� ��� �Ѵ�.
        //���� ��ư�� ��~�� ������ �޴��� ��Ÿ����.
        registerForContextMenu(image);
        registerForContextMenu(colorBtn);
        registerForContextMenu(sizeBtn);
    }
    
    
  //��ư�� ���� �������� �޴��� �����Ѵ�.
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
  ContextMenuInfo menuInfo) {
 
  if(v == colorBtn ||v==image){
  //��Ŭ���� �߻��� �䰡 colorBtn�̶��
  menu.add(0, 0, 1, "����");
  menu.add(0, 1, 1, "���");
  menu.add(0, 2, 1, "�Ķ�");
  }else if(v == sizeBtn){
  //��Ŭ���� �߻��� �䰡 sizeBtn�̶��
  menu.add(0, 3, 2, "�۰�");
  menu.add(0, 4, 2, "����");
  menu.add(0, 5, 2, "ũ��");
  }
 
  super.onCreateContextMenu(menu, v, menuInfo);
  }
 
 
  //�������� �����ϸ� �ڵ����� ����Ǵ� �޼ҵ�
@Override
public boolean onContextItemSelected(MenuItem item) {
int selItemId = item.getItemId();
switch(selItemId){
case 0:
tv.setTextColor(Color.RED);
break;
case 1:
tv.setTextColor(Color.YELLOW);
break;
case 2:
tv.setTextColor(Color.BLUE);
break;
case 3:
tv.setTextSize(15);
break;
case 4:
tv.setTextSize(35);
break;
case 5:
tv.setTextSize(55);
break;
}
return super.onContextItemSelected(item);
}   
}