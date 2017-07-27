package com.mytest.ch3c;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.mytest2.R;

public class MainActivity extends Activity {

	final String TAG = "MainActivity";
	int mCurrentFragmentIndex;
	Fragment leftFragment,bodyFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frag_main2);
		//프래그먼트 매니저 얻어오기
		FragmentManager fm = getFragmentManager();
		//프래그먼트 만들기
		Fragment fragment = fm.findFragmentById(R.id.leftFragment);
		 /*ListView listView=fragment
		//프래그먼트 트랜젝션 
		FragmentTransaction tr = fm.beginTransaction();
		CounterFragment cf = new CounterFragment();
		//편집 설정
		tr.add(R.id.frame, cf, "counter");
		//실행
		tr.commit();*/

		
	
	}


}
