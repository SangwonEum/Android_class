package com.mytest.ch3b;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mytest2.R;

public class MainActivity extends FragmentActivity implements OnClickListener {

	final String TAG = "MainActivity";

	int mCurrentFragmentIndex;
	public final static int FRAGMENT_FIRST = 0;
	public final static int FRAGMENT_SECOND = 1;
	public final static int FRAGMENT_THIRD = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_oneFragment = (Button) findViewById(R.id.bt_firstFragment);
		bt_oneFragment.setOnClickListener(this);
		Button bt_twoFragment = (Button) findViewById(R.id.bt_secondFragment);
		bt_twoFragment.setOnClickListener(this);
		Button bt_threeFragment = (Button) findViewById(R.id.bt_thirdFragment);
		bt_threeFragment.setOnClickListener(this);

		mCurrentFragmentIndex = FRAGMENT_FIRST;

		fragmentReplace(mCurrentFragmentIndex);
	}

	public void fragmentReplace(int reqNewFragmentIndex) {

		Fragment newFragment = null;

		Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);

		newFragment = getFragment(reqNewFragmentIndex);

		// replace fragment
		 FragmentTransaction transaction = getFragmentManager().beginTransaction();

		transaction.replace(R.id.fragment_layout, newFragment);

		// Commit the transaction
		transaction.commit();

	}

	private Fragment getFragment(int idx) {
		Fragment newFragment = null;

		switch (idx) {
		case FRAGMENT_FIRST:
			newFragment = new FirstFragment();
			break;
		case FRAGMENT_SECOND:
			newFragment = new SecondFragment();
			break;
		case FRAGMENT_THIRD:
			newFragment = new ThirdFragment();
			break;

		default:
			Log.d(TAG, "Unhandle case");
			break;
		}

		return newFragment;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.bt_firstFragment:
			mCurrentFragmentIndex = FRAGMENT_FIRST;
			fragmentReplace(mCurrentFragmentIndex);
			break;
		case R.id.bt_secondFragment:
			mCurrentFragmentIndex = FRAGMENT_SECOND;
			fragmentReplace(mCurrentFragmentIndex);
			break;
		case R.id.bt_thirdFragment:
			mCurrentFragmentIndex = FRAGMENT_THIRD;
			fragmentReplace(mCurrentFragmentIndex);
			break;

		}

	}

}
