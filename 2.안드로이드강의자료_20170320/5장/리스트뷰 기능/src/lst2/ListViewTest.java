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

 	    //����Ʈ ������ �߿� �Ѱ��� ���� �� �� �ִ� ���� �ڽ��� ǥ���Ѵ�.
 	    //mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice , dataArr);
 	    //list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
 	     
 	    //����Ʈ ������ �߿� �������� ������ �� �ִ� üũ�ڽ��� ǥ���Ѵ�.
 	     mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, dataArr);
 	     list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 	     list.setAdapter(mAdapter);
 	}

 	@Override
 	public void onClick(View v) {		
 	     if(v == btnAdd){ //����Ʈ �ؽ�Ʈ�� ������ ����Ʈ�� �߰��Ѵ�.
 	          String str = editText.getText().toString();
 	          if(str.length() != 0){
 	               editText.setText("");
 	               dataArr.add(str);
 	               mAdapter.notifyDataSetChanged();  //����Ʈ�並 �����Ѵ�.
 	          }			
 	     }else if(v == btnDel){ //üũ �ڽ� üũ���θ� �����ؼ� �ش� �������� �����Ѵ�.
 	          SparseBooleanArray checkArr = list.getCheckedItemPositions();
 	               if (checkArr.size() != 0) {
                            for (int i = list.getCount() -1; i > -1 ; i--) {
 	                         if (checkArr.get(i)) {
 	                              dataArr.remove(i);
 	                         }
 	                    }
 	               }

 	               list.clearChoices();
 			mAdapter.notifyDataSetChanged();  //����Ʈ�並 �����Ѵ�.
 			
 		}
 	}
}