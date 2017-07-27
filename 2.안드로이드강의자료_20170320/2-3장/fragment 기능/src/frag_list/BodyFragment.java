package com.mytest.ch3c;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mytest2.R;

public class BodyFragment extends Fragment {
	int index=0;
	ImageView imageView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.body_fragment, container, false);
		imageView=(ImageView) v.findViewById(R.id.imageView1);
		//Button button = (Button) v.findViewById(R.id.bt_ok);
		return v;
	}
	
	/*public void update(){
		if(index==0){
			imageView.setImageResource(R.drawable.gallery2);
			index=1;
		}else{
			imageView.setImageResource(R.drawable.gallery1);
			index=0;
		}
		
	}*/
	
	public void update(int idx){
		switch(idx){
		case 0:
			imageView.setImageResource(R.drawable.gallery1);
			break;
		case 1:
			imageView.setImageResource(R.drawable.gallery2);
			break;
		case 2:
			imageView.setImageResource(R.drawable.gallery3);
			break;
		case 3:
			imageView.setImageResource(R.drawable.gallery4);
			break;
		default:
			imageView.setImageResource(R.drawable.gallery1);
		}
	}
}
