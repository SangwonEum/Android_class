package androidbook.ch06;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class PreferencesActivity extends Activity {
    private CheckBox mCheck;
    private EditText mEdit;
    private RadioGroup mRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);

        mCheck = (CheckBox) findViewById(R.id.check);
        mEdit = (EditText) findViewById(R.id.edit);
        mRadio = (RadioGroup) findViewById(R.id.radio);
    }

    //화면에 설정한 값을  프레퍼런스에 저장한다.
    public void onClickSave(View v) {
        SharedPreferences preferences = getSharedPreferences("ch06", Activity.MODE_PRIVATE);

        boolean check = mCheck.isChecked();
        String edit = mEdit.getText().toString();
        int radio = mRadio.getCheckedRadioButtonId();

        Editor editor = preferences.edit();
        editor.putBoolean("check", check);
        editor.putString("edit", edit);
        editor.putInt("radio", radio);
        editor.commit();
    }

    //프레퍼런스에 저장한 값을 가지고온다.
    public void onClickLoad(View v) {
        SharedPreferences pref = getSharedPreferences("ch06", Activity.MODE_PRIVATE);

        boolean check = pref.getBoolean("check", false);
        String edit = pref.getString("edit", "");
        int radio = pref.getInt("radio", R.id.radioFirst);

        mCheck.setChecked(check);
        mEdit.setText(edit);
        mRadio.check(radio);
    }
}
