package androidbook.ch03.widget.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import androidbook.ch03.R;

public class RadioButtonActivity extends Activity {
    /** Called when the activity is first created. */
    RadioGroup group;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiobutton);
        group = (RadioGroup)findViewById(R.id.radiogroup);
        text = (TextView)findViewById(R.id.text);
group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
RadioButton radiobtn = (RadioButton)findViewById(checkedId);
                String str = radiobtn.getText().toString();
                text.setText(str);                
            }
        });        
    }
}
