package androidbook.ch04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WithResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.with_result);
    }

    public void onClickOk(View v) {
        Intent intent = new Intent();
        intent.putExtra("result", 12345);
        setResult(Activity.RESULT_OK, intent);  //인텐트에 정보를 저장한 후 호출한 액티비티로 전달한다.
        finish();
    }

    public void onClickCancel(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
