package androidbook.ch02;

import androidbook.ch02.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SampleMain extends Activity {
    /** Called when the activity is first created. */
    TextView mText;
    Button mButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mText = (TextView)findViewById(R.id.text1);
        mButton = (Button)findViewById(R.id.btn1);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mText.setText(R.string.click_text);               
            }
        });
    }
}