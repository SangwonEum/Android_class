package androidbook.ch04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SendResultActivity extends Activity {
    private TextView mNameTextView;
    private TextView mAgeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_result);

        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);

        mNameTextView = (TextView) findViewById(R.id.name);
        mAgeTextView = (TextView) findViewById(R.id.age);

        mNameTextView.setText(name);
        mAgeTextView.setText(Integer.toString(age));
    }

    public void onClickOk(View v) {
        String result = mNameTextView.getText().toString() + "/" + mAgeTextView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onClickCancel(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
