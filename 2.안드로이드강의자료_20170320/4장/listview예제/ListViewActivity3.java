package com.ch5.ex1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.androidprj.R;

public class ListViewActivity3 extends Activity {
	private ListView list;
	private ArrayList<String> dataList;
	private ArrayAdapter<String> adapter;
	private Intent intent;
	private int count=0;
	//private Button btn_add;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity3);
		list = (ListView)findViewById(R.id.list3);
        dataList = new ArrayList<String>();
        dataList.add("첫번째 목록");
        dataList.add("두번째 목록");
        dataList.add("세번째 목록");
        dataList.add("네번째 목록");
        dataList.add("다섯번째 목록");
        dataList.add("여섯번째 목록");
        dataList.add("일곱번째 목록");
        dataList.add("여덟번째 목록");
        dataList.add("아홉번째 목록");
        dataList.add("열번째 목록");
        
               
        /*
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout my_btn = (LinearLayout) inflater.inflate(R.layout.my_btn, null);
        list.addFooterView(my_btn,null,false);  //setAdapter 호출 전에 호출되어야 한다.
        
        btn_add = (Button) findViewById(R.id.btn_add);
        
        btn_add.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
            	dataList.add("새 목록 "+Integer.toString(++count)+"추가");
       		 	adapter.notifyDataSetChanged(); //리스트뷰를 갱신한다.
            }
        });
        */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        list.setAdapter(adapter);
        list.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); //새로 추가된 아이템으로 스크롤이 이동한다.
	}
    
	
	public void addItem(View view){
		dataList.add("새 목록 "+Integer.toString(++count)+"추가");
		 	adapter.notifyDataSetChanged(); //리스트뷰를 갱신한다.
	}
}




