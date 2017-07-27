package com.mytest.ch3c;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mytest2.R;

public class LeftFragment extends Fragment  implements OnItemClickListener{
	String[] itemName={"Ã¹¹øÂ° ²É","µÎ¹øÂ° ²É","¼¼¹øÂ° ²É","³×¹øÂ° ²É" };
	ArrayAdapter aAdapter;
			
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.left_fragment, container, false);
		
		ListView listView = (ListView) v.findViewById(R.id.leftListView1);
		aAdapter=new ArrayAdapter(inflater.getContext(),android.R.layout.simple_list_item_1,itemName);
		listView.setAdapter(aAdapter);
		listView.setOnItemClickListener(this);
		return v;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int idx, long arg3) {
		Toast.makeText(v.getContext(), "item clicked", Toast.LENGTH_LONG).show();
		FragmentManager fm = getFragmentManager();
		BodyFragment fragment = (BodyFragment)fm.findFragmentById(R.id.bodyFragment);
		//fragment.update();
		fragment.update(idx);
		
	}

}
