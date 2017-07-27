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
        
        //컨텍스트 메뉴에 등록 한다.
        //이제 버튼을 꾸~욱 누르면 메뉴가 나타난다.
        registerForContextMenu(image);
        registerForContextMenu(colorBtn);
        registerForContextMenu(sizeBtn);
    }
    
    
  //버튼에 따라 보여지는 메뉴를 구성한다.
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
  ContextMenuInfo menuInfo) {
 
  if(v == colorBtn ||v==image){
  //롱클릭이 발생한 뷰가 colorBtn이라면
  menu.add(0, 0, 1, "빨강");
  menu.add(0, 1, 1, "노랑");
  menu.add(0, 2, 1, "파랑");
  }else if(v == sizeBtn){
  //롱클릭이 발생한 뷰가 sizeBtn이라면
  menu.add(0, 3, 2, "작게");
  menu.add(0, 4, 2, "보통");
  menu.add(0, 5, 2, "크게");
  }
 
  super.onCreateContextMenu(menu, v, menuInfo);
  }
 
 
  //아이템을 선택하면 자동으로 실행되는 메소드
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