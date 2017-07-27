package androidbook.ch04_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickBroadcastTest(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", "ȫ�浿");
        intent.putExtra("age", 23);
        
        intent.setAction("androidbook.ch04.action.BROADCAST_TEST");
        sendBroadcast(intent);
    }
}

