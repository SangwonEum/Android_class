package com.example.administrator.gameactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017-07-26.
 */

public class GameActivity extends AppCompatActivity {

    private GameView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mView = new GameView(this);
        setContentView(mView);

    }

    @Override
    protected void onResume(){
        super.onResume();
        mView.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mView.stop();
    }
}
