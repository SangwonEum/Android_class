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
     addressList.add(new MyBook("홍길동",  "010-2222-0101") );
     addressList.add(new MyBook("임꺽정", "010-3333-1111") );
     addressList.add(new MyBook("이순신", "010-1212-0202") );
     addressList.add(new MyBook("유관순","010-1313-0303") );
     
     //어댑터에 입력한 arraylist와 my_row를 인자로 입력해서 각각의 주소를 목록에 표시한다.
     mAdapter = new MyAdapter(this, R.layout.my_row, addressList);
     list.setAdapter(mAdapter);
}


}