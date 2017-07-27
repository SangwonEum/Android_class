package com.mytest.ch5;

import java.util.ArrayList;

import com.mytest2.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class MyAdapter extends BaseAdapter{
	Context context;
	int layoutId;
	ArrayList<MyBook> myDataArr;
	LayoutInflater Inflater; //동적으로 레이아웃을 생성하는 변수 
	
	MyAdapter(Context context, int layoutId, ArrayList<MyBook> myDataArr){
	    this.context = context;
	   this.layoutId = layoutId;
	   this.myDataArr = myDataArr;
	   Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//리스트 뷰의 항목 개수
	@Override
	public int getCount() {
	     return myDataArr.size();
	}
	
	//항목 선택 시 리턴되는 데이터
	@Override
	public String getItem(int position) {
	     return myDataArr.get(position).name;
	}
	
	/*@Override
	public MyBook getItem(int position) {
	     return myDataArr.get(position);
	}*/
	
	//선택한 항목 순서
	@Override
	public long getItemId(int position) {
	     return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	            final int pos = position;
	          //리스트뷰의 각각의 목록에 어답터로 전달된 데이터를 표시한다.
	if (convertView == null)  {
	                convertView = Inflater.inflate(layoutId, parent, false);
	            }
	            //my_row.xml의 텍스트뷰에 arrayList로 넘어온 각각의 주소와 전화번호를 표시한다.
	            TextView nameTv = (TextView)convertView.findViewById(R.id.tName);
	            nameTv.setText(myDataArr.get(position).name);
	            
	            TextView phoneTv = (TextView)convertView.findViewById(R.id.tPhone);
	            phoneTv.setText(myDataArr.get(position).phone);
	            
	             return convertView;
	}
}
