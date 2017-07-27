package com.mytest.ch3b;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mytest2.R;

public class FirstFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.first_fragment, container, false);

		Button button = (Button) v.findViewById(R.id.bt_ok);
		button.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.bt_ok:
			Toast.makeText(getActivity(), "OneFragment", Toast.LENGTH_SHORT)
					.show();
			break;

		}

	}

}
