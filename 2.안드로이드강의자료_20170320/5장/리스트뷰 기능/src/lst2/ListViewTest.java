package com.ch3.lst;

import java.util.ArrayList;

import com.example.androidprj.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ListViewTest extends Activity implements OnClickListener {
 	ListView list;
 	Button btnAdd, btnDel;
 	EditText editText;
 	
 	ArrayList<String> dataArr;
 	ArrayAdapter<String> mAdapter;

 	@Override
 	public void onCreate(Bundle savedInstanceState) {
 	     super.onCreate(savedInstanceState);
 	     setContentView(R.layout.list_main);

 	     list = (ListView) findViewById(R.id.listView);
 	     editText = (EditText) findViewById(R.id.editText);
 	     btnAdd = (Button) findViewById(R.id.input);
 	     btnDel = (Button) findViewById(R.id.del);
 		
 	     btnAdd.setOnClickListener( this );
 	     btnDel.setOnClickListener( this );

 	     dataArr = new ArrayList<String>();

 	    //리스트 아이템 중에 한개만 선택 할 수 있는 라디오 박스를 표시한다.
 	    //mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice , dataArr);
 	    //list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
 	     
 	    //리스트 아이템 중에 여러개를 선택할 수 있는 체크박스를 표시한다.
 	     mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, dataArr);
 	     list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 	     list.setAdapter(mAdapter);
 	}

 	@Override
 	public void onClick(View v) {		
 	     if(v == btnAdd){ //에디트 텍스트의 내용을 리스트에 추가한다.
 	          String str = editText.getText().toString();
 	          if(str.length() != 0){
 	               editText.setText("");
 	               dataArr.add(str);
 	               mAdapter.notifyDataSetChanged();  //리스트뷰를 갱신한다.
 	          }			
 	     }else if(v == btnDel){ //체크 박스 체크여부를 조사해서 해당 아이템을 삭제한다.
 	          SparseBooleanArray checkArr = list.getCheckedItemPositions();
 	               if (checkArr.size() != 0) {
                            for (int i = list.getCount() -1; i > -1 ; i--) {
 	                         if (checkArr.get(i)) {
 	                              dataArr.remove(i);
 	                         }
 	                    }
 	               }

 	               list.clearChoices();
 			mAdapter.notifyDataSetChanged();  //리스트뷰를 갱신한다.
 			
 		}
 	}
}