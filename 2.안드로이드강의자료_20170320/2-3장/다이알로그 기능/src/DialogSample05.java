package com.ch1a;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogSample05 extends Activity {

    private static final int DIALOG_LOADING_ID = 0;
    private static final int DIALOG_FINISH_ID = 1;

    private AlertDialog.Builder builder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managed_dialog);

        builder = new AlertDialog.Builder(this);

        Button button = (Button) findViewById(R.id.button01);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_LOADING_ID);  //DialogFragment로 대체됨
            }
        });
        button = (Button) findViewById(R.id.button02);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_FINISH_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch (id) {
        case DIALOG_LOADING_ID:
            builder.setMessage("Loading Dialog");
            builder.setPositiveButton("확인", null);
            dialog = builder.create();
            Log.d("DialogSample", "onCreateDialog");
            break;
        case DIALOG_FINISH_ID:
            builder.setMessage("Finish Dialog");
            builder.setPositiveButton("확인", null);
            dialog = builder.create();
            break;
        default:
            dialog = null;
        }
        return dialog;
    }
    
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        switch (id) {
        case DIALOG_LOADING_ID:
            Log.d("DialogSample", "onPrepareDialog");
            break;
        case DIALOG_FINISH_ID:
            break;
        default:
        }
    }
}
