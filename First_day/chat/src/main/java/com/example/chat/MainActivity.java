package com.example.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    Button send;
    TextView displayMesage;
    TextView inputMessage;
    TextView currentView;
    LinearLayout ll;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.send_message);
        send.setOnClickListener(this);

        displayMesage = (TextView) findViewById(R.id.textView2);
        inputMessage = (TextView) findViewById(R.id.input_message);
        currentView = (TextView) findViewById(R.id.textView2);
        ll = (LinearLayout) findViewById(R.id.linearLayout);
        ll.removeAllViews();





    }

    @Override
    public void onClick(View view){
        if(view.equals(send)){

           //if(currentView.getId() == R.id.textView2) {
           //    Log.d("result:", inputMessage.getText().toString());
            //  displayMesage.setText(inputMessage.getText().toString());
            //  currentView.setBackgroundResource(R.drawable.user_message2);
            //ll.removeAllViews();
            //((ViewGroup)ll.getParent()).removeView(ll);
            TextView userMessage = new TextView(this);
            userMessage.setText(inputMessage.getText().toString());
            userMessage.setBackgroundResource(R.drawable.user_message);
            if(index %2 ==0){
                userMessage.setBackgroundResource(R.drawable.user_message);
                userMessage.setGravity(Gravity.NO_GRAVITY);
                //ll.addView(userMessage,-1);
                LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                userMessageLayout.gravity = Gravity.NO_GRAVITY;
                ll.addView(userMessage,-1,userMessageLayout);
                inputMessage.setText("");

            }else{
                userMessage.setBackgroundResource(R.drawable.user_message2);
                //userMessage.setGravity(Gravity.END);
                LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                userMessageLayout.gravity = Gravity.END;
                ll.addView(userMessage,-1,userMessageLayout);
                inputMessage.setText("");
            }

            index++;




           //    currentView = (TextView) findViewById(R.id.textView3);


           //}else{
           //    currentView.setText(inputMessage.getText().toString());
           //    currentView.setBackgroundResource(R.drawable.user_message);
           //    ll.addView(currentView);
           //}
        }

    }
}
