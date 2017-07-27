package androidbook.ch08;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class NaverRankingActivity extends Activity {
    
    private TextView mMessageTextView;
    private NaverRankingReceiver mNaverRankingReceiver;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mMessageTextView = (TextView)findViewById(R.id.message);
        
        // 브로드캐스트 리시버 등록
        IntentFilter intentFilter = new IntentFilter(NaverRankingConstant.MESSAGE_NAVER_DATA_RECEIVED);
        mNaverRankingReceiver = new NaverRankingReceiver();
        registerReceiver(mNaverRankingReceiver, intentFilter);
        
        // 서비스 시작
        Intent intent = new Intent(this,NaverRankingService.class);
        startService(intent);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNaverRankingReceiver);
    }

    class NaverRankingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            
            if( intent == null )
                return;
            
            String message = intent.getStringExtra("message");
            mMessageTextView.setText(message);
        }
    }
}