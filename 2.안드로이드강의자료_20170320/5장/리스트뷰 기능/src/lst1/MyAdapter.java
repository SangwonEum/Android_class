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
	LayoutInflater Inflater; //�������� ���̾ƿ��� �����ϴ� ���� 
	
	MyAdapter(Context context, int layoutId, ArrayList<MyBook> myDataArr){
	    this.context = context;
	   this.layoutId = layoutId;
	   this.myDataArr = myDataArr;
	   Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	//����Ʈ ���� �׸� ����
	@Override
	public int getCount() {
	     return myDataArr.size();
	}
	
	//�׸� ���� �� ���ϵǴ� ������
	@Override
	public String getItem(int position) {
	     return myDataArr.get(position).name;
	}
	
	/*@Override
	public MyBook getItem(int position) {
	     return myDataArr.get(position);
	}*/
	
	//������ �׸� ����
	@Override
	public long getItemId(int position) {
	     return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	            final int pos = position;
	          //����Ʈ���� ������ ��Ͽ� ����ͷ� ���޵� �����͸� ǥ���Ѵ�.
	if (convertView == null)  {
	                convertView = Inflater.inflate(layoutId, parent, false);
	            }
	            //my_row.xml�� �ؽ�Ʈ�信 arrayList�� �Ѿ�� ������ �ּҿ� ��ȭ��ȣ�� ǥ���Ѵ�.
	            TextView nameTv = (TextView)convertView.findViewById(R.id.tName);
	            nameTv.setText(myDataArr.get(position).name);
	            
	            TextView phoneTv = (TextView)convertView.findViewById(R.id.tPhone);
	            phoneTv.setText(myDataArr.get(position).phone);
	            
	             return convertView;
	}
}
