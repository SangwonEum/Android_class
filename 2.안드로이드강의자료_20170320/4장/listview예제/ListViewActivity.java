package com.ch5.ex1;

import java.util.ArrayList;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends Activity implements OnItemClickListener{
	private ListView list;
	private ArrayList<String> dataList;
	private ArrayAdapter<String> adapter;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		list = (ListView)findViewById(R.id.list);
        dataList = new ArrayList<String>();
        dataList.add("첫번째 목록");
        dataList.add("두번째 목록");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this); //리스트 목록 클릭시 이벤트핸들러 설정
    }
	//리스트 목록 클릭시 이벤트 핸들러
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
    	String data=null;
		switch(position)
		{
		case 0 :
			intent = new Intent(ListViewActivity.this,ChildActivity1.class);
			data=dataList.get(position);
			intent.putExtra("data",data);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(ListViewActivity.this,ChildActivity2.class);
			data=dataList.get(position);
			intent.putExtra("data",data);
			startActivity(intent);
			break;
		}
		
	}
}
