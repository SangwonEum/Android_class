package androidbook.ch08;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ANRThread2Activity extends Activity {

    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anr);

        mButton = (Button) findViewById(R.id.Button01);
    }

    public void anr(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mButton.setText("ANR Clicked");
            }
        });
        thread.start();
    }
}