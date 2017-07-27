package androidbook.ch04;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidbook.ch04.R;

public class TargetActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target);
    }

    public void onClickBackToPrevious(View v) {
        onBackPressed();
    }
}
