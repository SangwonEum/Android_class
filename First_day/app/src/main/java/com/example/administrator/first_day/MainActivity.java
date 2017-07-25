package com.example.administrator.first_day;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int a = 0;
        for(int i=0; i <= 100;i++){
            a+=i;
        }

       TextView textView = (TextView) findViewById(R.id.textView);
       textView.setText(calc(a));
    }

    private String calc(int n){

        int a = 0;
        for(int i=0; i <= n ; i++){
            a+= i;
        }
        String s  = "결과 값은 " + a ;

        return s;
    }
}
