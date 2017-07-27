package androidbook.ch03.widget.pizza;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import androidbook.ch03.R;

public class PizzaActivity extends Activity {
    /** Called when the activity is first created. */
    RadioGroup radiogroup;
    RadioButton radioPizza;
    CheckBox checkKetchup;
    CheckBox checkPickle;
    CheckBox checkSource;
    Button button;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pizza);

    text = (TextView)findViewById(R.id.text);
    checkKetchup = (CheckBox)findViewById(R.id.cb_ketchup);
    checkPickle = (CheckBox)findViewById(R.id.cb_pickle);
    checkSource = (CheckBox)findViewById(R.id.cb_source);
    radiogroup = (RadioGroup)findViewById(R.id.radiogroup);
    button = (Button)findViewById(R.id.button);

    radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

        public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        radioPizza = (RadioButton)findViewById(checkedId);              
        }
    });        
    button.setOnClickListener(new OnClickListener() {

        public void onClick(View v) {
        // TODO Auto-generated method stub
        String strPizza ="";
        String strOption ="";
        if(radioPizza != null){
            strPizza = radioPizza.getText().toString()+"를 주문하셨습니다\n";
            if(checkKetchup.isChecked()){
            strOption += checkKetchup.getText(); 
            }
            if(checkPickle.isChecked()){
            strOption += checkPickle.getText();
            }
            if(checkSource.isChecked()){
            strOption += checkSource.getText();
            }                              
            text.setText(strPizza+strOption);
        }
        else {
            text.setText("피자를 선택해 주세요");
        }
        }
    });
}
}
