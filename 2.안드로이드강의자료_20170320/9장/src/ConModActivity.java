package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ConModActivity extends Activity implements
        AdapterView.OnItemClickListener {

    private ListAdapter adapter;
    private ListView mListView;
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

        mListView = (ListView) findViewById(R.id.listview);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, new String[] {
                        Data._ID, Phone.DISPLAY_NAME }, new int[] {
                        android.R.id.text1, android.R.id.text2 });

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Cursor cursor = (Cursor) adapter.getItem(position);
        Long _id = cursor.getLong(0);
        String name = cursor.getString(1);
        createDialog(_id, name);

        Toast.makeText(ConModActivity.this, _id + " " + name,
                Toast.LENGTH_SHORT).show();
    }

    private void createDialog(final Long _id, final String name) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setText(name);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString().trim();
                modifyContacts(_id, value);
                Toast.makeText(getApplicationContext(), value,
                        Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }

    public void modifyContacts(Long _id, String name) {
        Uri contacts = Uri.withAppendedPath(
                ContactsContract.Contacts.CONTENT_URI, _id.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(Data.DISPLAY_NAME, name);
        getContentResolver().update(contacts, contentValues, null, null);
    }
}