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
		//�����׸�Ʈ �Ŵ��� ������
		FragmentManager fm = getFragmentManager();
		//�����׸�Ʈ �����
		Fragment fragment = fm.findFragmentById(R.id.leftFragment);
		 /*ListView listView=fragment
		//�����׸�Ʈ Ʈ������ 
		FragmentTransaction tr = fm.beginTransaction();
		CounterFragment cf = new CounterFragment();
		//���� ����
		tr.add(R.id.frame, cf, "counter");
		//����
		tr.commit();*/

		
	
	}


}
