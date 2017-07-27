package androidbook.ch04;

import androidbook.ch04.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class IntentTestActivity extends Activity {
    private static final int REQUEST_CODE_1 = 101;
    private static final int REQUEST_CODE_2 = 102;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_test);
    }

    public void displayTargetActivity(View v) {
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }

    public void displayCallActivity(View v) {
        Uri number = Uri.parse("tel:01012345678");
        Intent dial = new Intent(Intent.ACTION_DIAL, number);
        startActivity(dial);
    }

    public void sendDataToActivity(View v) {
        Intent intent = new Intent(this, WithExtraActivity.class);
        intent.putExtra("name", "Bob");
        intent.putExtra("age", 21);
        startActivity(intent);
    }

    public void sendResultBack(View v) {
        Intent intent = new Intent(this, WithResultActivity.class);
        startActivityForResult(intent, REQUEST_CODE_1);
    }

    public void sendResultBack2(View v) {
        Intent intent = new Intent(this, SendResultActivity.class);
        intent.putExtra("name", "John");
        intent.putExtra("age", 23);
        startActivityForResult(intent, REQUEST_CODE_2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_CODE_1) {
                int resultInt = data.getIntExtra("result", 0);
                Toast.makeText(this, "RESULT=" + resultInt, Toast.LENGTH_SHORT).show();
            }
            else if (requestCode == REQUEST_CODE_2) {
                String resultString = data.getStringExtra("result");
                Toast.makeText(this, "RESULT=" + resultString, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        }
        
    }
}
