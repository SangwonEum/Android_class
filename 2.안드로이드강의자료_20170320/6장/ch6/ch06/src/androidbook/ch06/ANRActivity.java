package androidbook.ch08;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ANRActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anr);
    }

    public void anr(View view) {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("ANR", "클릭됨");
    }
}