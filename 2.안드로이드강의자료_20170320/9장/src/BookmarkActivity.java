package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class BookmarkActivity extends Activity {

    ListView mListView;
    private static String[] mProjections = new String[] {
            Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cont_main);

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Browser.BOOKMARKS_URI,
                mProjections, Browser.BookmarkColumns.BOOKMARK + "=?",
                new String[] { "1" }, Browser.BookmarkColumns.VISITS
                        + " DESC limit 10");

        startManagingCursor(cursor);
        mListView = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor, new String[] {
                        Browser.BookmarkColumns.TITLE,
                        Browser.BookmarkColumns.URL }, new int[] {
                        android.R.id.text1, android.R.id.text2 });

        mListView.setAdapter(adapter);
    }
}