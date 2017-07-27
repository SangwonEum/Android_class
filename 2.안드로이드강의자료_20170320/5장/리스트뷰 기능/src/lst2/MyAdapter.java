package com.ch3.lst;

import java.util.ArrayList;

import com.example.androidprj.R;

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
	ArrayList<MyItem> myDataArr;
	LayoutInflater Inflater;
    MyAdapter(Context context, int layoutId, ArrayList<MyItem> myDataArr){
	    this.context = context;
	   this.layoutId = layoutId;
	   this.myDataArr = myDataArr;
	   Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
	     return myDataArr.size();
	}
	
	@Override
	public String getItem(int position) {
	     return myDataArr.get(position).name;
	}
	
	@Override
	public long getItemId(int position) {
	     return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	            final int pos = position;
	           // 1�� ����
	if (convertView == null)  {
	                convertView = Inflater.inflate(layoutId, parent, false);
	            }
	            
	            ImageView leftImg = (ImageView)convertView.findViewById(R.id.left_image);
	            leftImg.setImageBitmap(myDataArr.get(position).myImg);
	
	            TextView nameTv = (TextView)convertView.findViewById(R.id.name_tv);
	            nameTv.setText(myDataArr.get(position).name);
	            
	            TextView phoneTv = (TextView)convertView.findViewById(R.id.phone_tv);
	            phoneTv.setText(myDataArr.get(position).phone);
	            
	            //2�� ����
				 Button btn = (Button)convertView.findViewById(R.id.sendBtn);
				            btn.setOnClickListener(new Button.OnClickListener()  {
				                public void onClick(View v)  {
				                    String str = myDataArr.get(pos).name + "���� ��ȭ��ȣ�� [ "
				                    			+myDataArr.get(pos).phone+" ] �Դϴ�.";
				                    Toast.makeText(context, str,Toast.LENGTH_SHORT).show();
				                    }
				              });
	
	             return convertView;
	}
}
