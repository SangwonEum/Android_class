package com.mytest.ch3a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {
	protected  static final int DIALOG_LOADING_ID = 0;
    protected  static final int DIALOG_FINISH_ID = 1;
    
    protected  static final int DIALOG_SAVE_ID = 2;
    protected  static final int DIALOG_UPDATE_ID = 3;
    
    protected  AlertDialog.Builder builder;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  builder = new AlertDialog.Builder(this);
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
	    case DIALOG_SAVE_ID:
	        builder.setMessage("save Dialog");
	        builder.setPositiveButton("확인", null);
	        dialog = builder.create();
	        break;
	        
	    case DIALOG_UPDATE_ID:
	        builder.setMessage("update Dialog");
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
