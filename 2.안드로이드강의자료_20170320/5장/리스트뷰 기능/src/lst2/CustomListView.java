package com.ch3.lst;


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
ArrayList<MyItem> addressList;
MyAdapter mAdapter;

@Override
public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.list_custom);

     list = (ListView) findViewById(R.id.listView);

     addressList = new ArrayList<MyItem>();
     addressList.add(new MyItem(BitmapFactory.decodeResource(getResources(), 
    		 	R.drawable.ic_launcher), "홍길동",  "010-2222-0101") );
     addressList.add(new MyItem(BitmapFactory.decodeResource(getResources(),     
    		 	R.drawable.droid), "임꺽정", "010-3333-1111") );
     addressList.add(new MyItem(BitmapFactory.decodeResource(getResources(), 
    		 	R.drawable.test1), "이순신", "010-1212-0202") );
     addressList.add(new MyItem(BitmapFactory.decodeResource(getResources(), 
    		 	R.drawable.ic_launcher), "유관순","010-1313-0303") );

     mAdapter = new MyAdapter(this, R.layout.my_item, addressList);
     list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
     list.setAdapter(mAdapter);
}


}