package com.ch3.lst3;


import java.util.ArrayList;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListView extends Activity{
ListView list;
ArrayList<MyBook> addressList;
MyAdapter mAdapter;

@Override
public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.list_custom);

     list = (ListView) findViewById(R.id.listView);

     addressList = new ArrayList<MyBook>();
     addressList.add(new MyBook("ȫ�浿",  "010-2222-0101") );
     addressList.add(new MyBook("�Ӳ���", "010-3333-1111") );
     addressList.add(new MyBook("�̼���", "010-1212-0202") );
     addressList.add(new MyBook("������","010-1313-0303") );
     
     //����Ϳ� �Է��� arraylist�� my_row�� ���ڷ� �Է��ؼ� ������ �ּҸ� ��Ͽ� ǥ���Ѵ�.
     mAdapter = new MyAdapter(this, R.layout.my_row, addressList);
     list.setAdapter(mAdapter);
}


}