package androidbook.ch03.widget.nameapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidbook.ch03.R;

public class NameApplication extends Activity {
	/** Called when the activity is first created. */
    TextView txt;
    EditText edit;
    Button btn_OK;
    Button btn_Cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_application);

        txt = (TextView)findViewById(R.id.text);
        edit = (EditText)findViewById(R.id.edittext);
        btn_OK = (Button)findViewById(R.id.ok);
        btn_Cancel = (Button)findViewById(R.id.cancel);

        btn_OK.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = null; 
                str = edit.getText().toString();
                txt.setText(str); 
            }
        });        
        btn_Cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();   
            }
        });

    }
}
