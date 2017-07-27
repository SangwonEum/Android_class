package androidbook.ch08;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class ANRThread3Activity extends Activity {

    private static final int MESSAGE_NO = 2000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
            case MESSAGE_NO: {
                mButton.setText("ANR Clicked");
            }
                break;
            }
        }
    };

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
                Message message = Message.obtain(handler, MESSAGE_NO);
                handler.sendMessage(message);
            }
        });
        thread.start();
    }
}