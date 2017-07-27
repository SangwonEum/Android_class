package com.ch11.ex1;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TelCallActivity extends Activity implements OnClickListener {

    EditText mEditTextPhone;
    Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);

        mEditTextPhone = (EditText) findViewById(R.id.phone);
        mButton = (Button) findViewById(R.id.send);
        mButton.setOnClickListener(this);
    }

    private void call() {
        String phone = mEditTextPhone.getText().toString();

        if (phone.length() == 0) return;

        // 전화걸기
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.send:
            call();
            break;
        }
    }
}