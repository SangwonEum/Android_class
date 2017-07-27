package androidbook.ch06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class StorageActivity extends Activity {
    private static final String FILENAME = "test.txt";
    private EditText mText;
    private CheckBox checkExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage);

        checkExternal = (CheckBox) findViewById(R.id.checkExternal);
        mText = (EditText) findViewById(R.id.text);
    }

    public void onClickSave(View v) throws IOException {
        if (checkExternal.isChecked())
            saveToExternal(mText.getText().toString());
        else
            saveToInternal(mText.getText().toString());
    }

    public void onClickLoad(View v) throws IOException {
        if (checkExternal.isChecked())
            mText.setText(loadFromExternal());
        else
            mText.setText(loadFromInternal());
    }
    //입력한 텍스트를 파일에 쓴다.
    private void saveToInternal(String text) throws IOException {
        FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
    }
    
    //파일에서 텍스트를 읽어 온다.
    private CharSequence loadFromInternal() throws IOException {
        StringBuffer readed = new StringBuffer();
        int ch;
        FileInputStream fis = openFileInput(FILENAME);
        while ((ch = fis.read()) != -1)
            readed.append((char)ch);
        fis.close();
        return readed.toString();
    }

    private void saveToExternal(String text) throws IOException {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes());
            fos.close();
        }
        else {
            Toast.makeText(this, "SD카드가 연결되지 않았습니다.", Toast.LENGTH_SHORT);
        }
    }

    private CharSequence loadFromExternal() throws IOException {
        String state = Environment.getExternalStorageState();
        StringBuffer readed = new StringBuffer();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            int ch;
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir, FILENAME);
            FileInputStream fis = new FileInputStream(file);
            while ((ch = fis.read()) != -1)
                readed.append((char)ch);
            fis.close();
        }
        else {
            Toast.makeText(this, "SD카드가 연결되지 않았습니다.", Toast.LENGTH_SHORT);
        }
        return readed.toString();
    }
}
