package androidbook.ch04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WithExtraActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.with_extra);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);

        TextView nameTextView = (TextView) findViewById(R.id.name);
        TextView ageTextView = (TextView) findViewById(R.id.age);
        nameTextView.setText("name=" + name);
        ageTextView.setText("age=" + age);
    }
}
