package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract.Data;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CallLogActivity extends Activity {

    ListView mListView;
    private static String[] mProjections = new String[] { Data._ID,
            CallLog.Calls.DATE, CallLog.Calls.NUMBER };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cont_main);

        Cursor cursor = getContentResolver().query(
                android.provider.CallLog.Calls.CONTENT_URI, mProjections, null,
                null, CallLog.Calls.DATE + " DESC");

        mListView = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, new String[] {
                        CallLog.Calls.DATE, CallLog.Calls.NUMBER }, new int[] {
                        android.R.id.text1, android.R.id.text2 });

        mListView.setAdapter(adapter);
    }
}
