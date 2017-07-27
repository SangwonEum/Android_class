package com.mytest.ch3a;


import com.mytest2.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogSample07 extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managed_dialog2);

        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_SAVE_ID);  //DialogFragment∑Œ ¥Î√ºµ 
            }
        });
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_UPDATE_ID);
            }
        });
    }

   
}
