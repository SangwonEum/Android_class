package androidbook.ch08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SampleServiceControlActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
    }
    //서비스를 호출한다.
    public void onStartService(View view) {
        Intent intent = new Intent(this, SampleService.class);
        startService(intent);
    }

    //서비스를 중단한다.
    public void onStopService(View view) {
        Intent intent = new Intent(this, SampleService.class);
        stopService(intent);
    }
}
