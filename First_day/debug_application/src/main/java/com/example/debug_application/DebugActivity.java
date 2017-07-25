package com.example.debug_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        TextView textview  = (TextView) findViewById(R.id.textView);
        textview.setText(calc(10)+"");


    }

   private int calc(int x){
       int result = 0;
       for(int i=0; i < x; i++){
           result ++;
           Log.d("result:",String.valueOf(result));
       }
       return result;
   }

}
