package com.ch5.ex1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidprj.R;

public class ListViewActivity1 extends Activity implements OnItemClickListener{
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
        dataList.add("세번째 목록");
        dataList.add("네번째 목록");
        dataList.add("다섯번째 목록");
        dataList.add("여섯번째 목록");
        dataList.add("일곱번째 목록");
        dataList.add("여덟번째 목록");
        dataList.add("아홉번째 목록");
        dataList.add("열번째 목록");
        dataList.add("열한번째 목록");
        dataList.add("열두번째 목록");
        dataList.add("열세번째 목록");
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
        dataList.add("열한번째 목록");
        dataList.add("열두번째 목록");
        dataList.add("열세번째 목록");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this); //리스트 목록 클릭시 이벤트핸들러 설정
        
        //리스트뷰가 스크롤 시 이벤트 핸들러
        list.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				Log.d("TAG","스크롤 됩니다.");
				Log.d("TAG","최상위 아이템번호: "+firstVisibleItem);
				Log.d("TAG","보이는 아이템 개수: "+visibleItemCount);
				Log.d("TAG","리스트뷰 아이템 총수 :"+totalItemCount);
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        list.addHeaderView(null); //리스트에 헤더 부분을 표시한다.
        list.addFooterView(null);  //리스트에서 푸터 부분을 표시한다.
        
    }
	//리스트 목록 클릭시 이벤트 핸들러
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
    	String data=null;
		switch(position)
		{
		case 0 :
			intent = new Intent(ListViewActivity1.this,ChildActivity1.class);
			data=dataList.get(position);
			intent.putExtra("data",data);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(ListViewActivity1.this,ChildActivity2.class);
			data=dataList.get(position);
			intent.putExtra("data",data);
			startActivity(intent);
			break;
		default:
			intent = new Intent(ListViewActivity1.this,ChildActivity1.class);
			data=dataList.get(position);
			intent.putExtra("data",data);
			startActivity(intent);
				
		}
		
	}
    
    
}


