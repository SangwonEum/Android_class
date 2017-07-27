package kr.study.debugapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        TextView textView = (TextView) findViewById(R.id.debugtext);
        String value;
        int i = 1;
        if (i < 3) {
            value = "foo";
        } else {
            value = "";
        }
        textView.setText(value);
    }
}
