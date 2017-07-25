package com.example.button_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    Button button1;
    Button button2;
    TextView displayMesage;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        displayMesage = (TextView) findViewById(R.id.textView);

         x =  Integer.parseInt(displayMesage.getText().toString());
        Log.d("result:",String.valueOf(x));


    }
    @Override
    public void onClick(View view){
        if(view.equals(button1)){
            x = x--;
            displayMesage.setText(Integer.toString(x));
           // displayMesage.setText(button1.getText().toString());

        }else{
            x  = x++;
            displayMesage.setText(Integer.toString(x));
           // displayMesage.setText(button2.getText().toString());
        }

    }

}
