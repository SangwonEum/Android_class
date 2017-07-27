package com.ch1a;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogSample01 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DialogSample01.this.finish();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setTitle("제목");
        builder.setIcon(R.drawable.ic_launcher); //아이콘 설정

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
        

        String[] items = new String[] { "목록1", "목록2", "목록3" };
        boolean[] checkedItems = new boolean[] { false, true, false };
        //리스트에 체크박스로 설정하기
       /* builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Log.d("DialogSample", "목록번호 : " + which + " " + isChecked);
            }
        });*/
        
        //리스트에 라디오 박스로 표시하기
          builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("DialogSample", "목록번호 : " + which + " " + which);
			}
		});
            

    }
}