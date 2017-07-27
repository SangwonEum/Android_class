package com.ch1.ex4;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.androidprj.R;

public class PickerDialogActivity  extends Activity implements OnDateSetListener,OnTimeSetListener{
	DatePickerDialog dateDialog;
	TimePickerDialog timeDialog;
	TextView text;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_picker);
		text=(TextView) findViewById(R.id.message);
		
		//DatePickerDialog 생성
		/* 1.Context
		 * 2.Listener
		 * 3.year
		 * 4.month
		 * 5.day
		 * 
		 */
		Calendar c=Calendar.getInstance();
		dateDialog=new DatePickerDialog(this,this,
										c.get(Calendar.YEAR),
										c.get(Calendar.MONTH),
										c.get(Calendar.DAY_OF_MONTH));
		
		/*
		 * TimePickerDialog 생성
		 * 1.Context
		 * 2.Listener
		 * 3.hour
		 * 4.minute
		 * 5.24시간 표기 여부
		 */
		
		timeDialog=new TimePickerDialog(this,this,
										c.get(Calendar.HOUR),
										c.get(Calendar.MINUTE),
										false);
	}
	
	public void showDate(View v){
		dateDialog.show();
	}
	
	public void showTime(View v){
		timeDialog.show();
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		String str=hourOfDay + "시" + minute+"분";
		text.setText(str);
		
	}

	@Override
	public void onDateSet(DatePicker arg0, int year, int monthOfDay, int dayOfMonth) {
		String str=year+ "년" +(monthOfDay + 1) +"월"+dayOfMonth + "일";
		text.setText(str);
		
	}
	
	
	

}
