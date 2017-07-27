package kr.study.firstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.text);
        int a = 0;
        for (int i = 1; i <= 100; ) {
            a += i;
        }
        String s = String.valueOf(a);
        textView.setText(s);

    }

}
