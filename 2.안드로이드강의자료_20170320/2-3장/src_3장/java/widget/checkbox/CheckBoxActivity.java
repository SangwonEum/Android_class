package androidbook.ch03.widget.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidbook.ch03.R;

public class CheckBoxActivity extends Activity {
    /** Called when the activity is first created. */
    CheckBox checkbox1;
    CheckBox checkbox2;
    Button button;
    TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.checkbox);
    checkbox1 = (CheckBox)findViewById(R.id.checkbox1);
    checkbox2 = (CheckBox)findViewById(R.id.checkbox2);
    button = (Button)findViewById(R.id.button);
    text = (TextView)findViewById(R.id.text);

        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
        // TODO Auto-generated method stub
        String str = null;
        if(checkbox1.isChecked()) 
        {
            str += "checkbox1 ";
        }
        if(checkbox2.isChecked())
        {
            str += "checkbox2 ";
        }
        if(str != null)
        {
            text.setText(str + "체크되었습니다.");
        }
        else
        {
            text.setText("체크되지 않았습니다.");
        }
            }
        });        
    }
}
