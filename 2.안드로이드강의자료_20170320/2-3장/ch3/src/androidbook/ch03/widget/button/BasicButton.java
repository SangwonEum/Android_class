package androidbook.ch03.widget.button;

import android.app.Activity;
import android.os.Bundle;
import androidbook.ch03.R;

public class BasicButton extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_button);
    }
}
