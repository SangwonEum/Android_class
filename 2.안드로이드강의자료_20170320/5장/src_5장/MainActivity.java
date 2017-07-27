package com.atest.ch5;

import com.atest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	private EditText editText1;
	private Button btn1,btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_main);
		editText1=(EditText) findViewById(R.id.edit1);
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
	}
	
	public void start(View v){
		Intent intent=new Intent(this,ChildActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(this, "onPaused called",Toast.LENGTH_SHORT).show();
		save();//����ȭ���� �ٸ� ȭ������ ������ �Է�â�� �޽����� �����۷����� �����Ѵ�.
	}
	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "onResume called",Toast.LENGTH_SHORT).show();
		SharedPreferences pref = getSharedPreferences("pref_temp", Activity.MODE_PRIVATE);
		String msg = pref.getString("msg", "");
		Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();

	}

	public void saveMessage(View v){
		save();//���� ��ư Ŭ�� �� �Է��� �޽����� �����۷����� �����Ѵ�.
	}
	
	public void loadMessage(View v){
		 SharedPreferences pref = getSharedPreferences("pref_temp", Activity.MODE_PRIVATE);
		 String msg = pref.getString("msg", "");
		 editText1.setText(msg);
		 Toast.makeText(this, "readede �޽����� �����۷������� �о� �Խ��ϴ�.",Toast.LENGTH_SHORT).show();
	}
	
	
	private void save(){
		SharedPreferences preferences = getSharedPreferences("pref_temp", Activity.MODE_PRIVATE);
        String msg = editText1.getText().toString();
        Editor editor = preferences.edit();
        editor.putString("msg", msg);
        editor.commit();
        Toast.makeText(this, "saved �޽����� �����۷����� �����߽��ϴ�.",Toast.LENGTH_SHORT).show();
	}
	
}



