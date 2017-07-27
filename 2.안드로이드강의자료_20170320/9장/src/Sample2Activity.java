package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Sample2Activity extends Activity {

    ListView mListView;
    private static String[] mProjections = new String[] { Data._ID,
            Data.DISPLAY_NAME, };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cont_main);

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, mProjections, null,
                null, null);

        startManagingCursor(cursor);
        mListView = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, new String[] {
                        Data._ID, Phone.DISPLAY_NAME }, new int[] {
                        android.R.id.text1, android.R.id.text2 });

        mListView.setAdapter(adapter);
    }
}
