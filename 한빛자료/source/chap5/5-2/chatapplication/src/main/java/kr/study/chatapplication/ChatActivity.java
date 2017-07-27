package kr.study.chatapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    private TextView mUserMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mInputMessage = (EditText) findViewById(R.id.input_message); // 사용자 입력 필드
        mSendMessage = (Button) findViewById(R.id.send_message); // SEND 버튼
        mMessageLog = (LinearLayout) findViewById(R.id.message_log); // 입력 이력을 표시할 레이아웃
        mCpuMessage = (TextView) findViewById(R.id.cpu_message); // 컴퓨터의 응답
        mUserMessage = (TextView) findViewById(R.id.user_message); // 사용자 입력한 내용
        mSendMessage.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mSendMessage)) {
            // SEND 버튼이 눌렸을 때의 처리
            String inputText = mInputMessage.getText().toString();
            mUserMessage.setText(inputText);
            mCpuMessage.setText("그거 괜찮네요");
            mInputMessage.setText("");

        }

    }
}
