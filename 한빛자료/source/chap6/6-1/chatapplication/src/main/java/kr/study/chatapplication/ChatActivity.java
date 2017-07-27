package kr.study.chatapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mInputMessage = (EditText) findViewById(R.id.input_message); // 사용자 입력 필드
        mSendMessage = (Button) findViewById(R.id.send_message); // SEND 버튼
        mMessageLog = (LinearLayout) findViewById(R.id.message_log); // 입력 이력을 표시할 레이아웃
        mCpuMessage = (TextView) findViewById(R.id.cpu_message); // 컴퓨터의 응답
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
            String answer;
            TextView userMessage = new TextView(this);		// 새 TextView를 생성한다.
            userMessage.setText(inputText); 			// TextView에 입력한 텍스트를 설정한다.
            userMessage.setGravity(Gravity.END); // 오른쪽 맞춤
            mMessageLog.addView(userMessage, 0);		// TextView를 View의 맨 위에 설정한다.
            if (inputText.contains("안녕")) {
                answer = "안녕하세요";
            } else if (inputText.contains("피곤")) {
                answer = "고생했어요";
            } else if (inputText.contains("운세")) {
                double random = Math.random() * 5.1d;
                if (random < 1d) {
                    answer = "몹시 나쁨";
                } else if (random < 2d) {
                    answer = "나쁨";
                } else if (random < 3d) {
                    answer = "보통";
                } else if (random < 4d) {
                    answer = "행운";
                } else if (random < 5d) {
                    answer = "대박";
                } else {
                    answer = "운수대통";
                }
            } else if (inputText.contains("시간")) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR); // 시
                int minute = cal.get(Calendar.MINUTE); // 분
                int second = cal.get(Calendar.SECOND); // 초
                answer = String.format("현재 시각은 %1$d시 %2$d분 %3$d초입니다", hour, minute, second);
            } else {
                answer = "그거 괜찮네요";
            }
            final TextView cpuMessage = new TextView(this); // 내부 클래스에서 참조하기 위해 final 선언
            cpuMessage.setText(answer);
            cpuMessage.setGravity(Gravity.START);
            mInputMessage.setText("");
            TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.
                    getWidth(), 0, 0, 0);
            userMessageAnimation.setDuration(1000);
            userMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                // 클래스 이름이 선언되지 않은 내부 클래스를 익명 내부 클래스라고 한다.
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mMessageLog.addView(cpuMessage, 0);
                    TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(), 0, 0, 0);
                    cpuAnimation.setDuration(1000);
                    cpuMessage.setAnimation(cpuAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            userMessage.startAnimation(userMessageAnimation);
        }
    }

}

