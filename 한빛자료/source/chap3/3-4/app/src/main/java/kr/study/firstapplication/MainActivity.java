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
        int a=5;
        String s = String.valueOf(a);
        if(a%3==0){
            s += "는(은) 3의 배수!";
        }else if(a%5==0){
            s += "는(은) 3의 배수가 아니라 5의 배수!"; 		// 변경
        }else{
            s += "(는)은 3의 배수도 5의 배수도 아니다!";
        }
        textView.setText(s);
    }
}
