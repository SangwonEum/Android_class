package com.mytest.ch10a;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class DrawBoardSurfaceActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DrawBoardSurface board = new DrawBoardSurface(this);
        setContentView(board);
    }
}
