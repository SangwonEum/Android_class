package com.example.atest.ch5;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.atest.R;

public class RandomActivity  extends Activity{
	Button btn;
	TextView tView;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.random);
		
        //btn = (CheckBox) findViewById(R.id.checkExternal);
        tView = (TextView) findViewById(R.id.tValue);
	}
	
	public void generateValue(View v)  throws IOException{
		int rNum=(int)(Math.random()*1000)%10;
		Log.d("TAG","³­¼ö : "+rNum);
		//tView.setText(rNum);
		tView.setText(Integer.toString(rNum));
		
	}

}
