package androidbook.ch03.widget.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidbook.ch03.R;

public class ClickListenerButton extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicklistener_button);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 버튼이 클릭 되었을때 실행시킬 코드 추가
            }
        });

    }
}
