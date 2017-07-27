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
        builder.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DialogSample01.this.finish();
            }
        }).setNegativeButton("���", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setTitle("����");
        builder.setIcon(R.drawable.ic_launcher); //������ ����

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
        

        String[] items = new String[] { "���1", "���2", "���3" };
        boolean[] checkedItems = new boolean[] { false, true, false };
        //����Ʈ�� üũ�ڽ��� �����ϱ�
       /* builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Log.d("DialogSample", "��Ϲ�ȣ : " + which + " " + isChecked);
            }
        });*/
        
        //����Ʈ�� ���� �ڽ��� ǥ���ϱ�
          builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("DialogSample", "��Ϲ�ȣ : " + which + " " + which);
			}
		});
            

    }
}