package androidbook.ch08;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class NaverRankingService extends Service{
    
    public static final int MESSAGE_NETWORK_DATA = 200;
    private static boolean isThreadCreated = false;
    private static NetworkThread networkThread;
    private Handler handler;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        setHandler();
        
        if( !isThreadCreated )
        {
            networkThread = new NetworkThread();
            networkThread.setLoopCount(100);
            networkThread.setUrl(NaverRankingConstant.NAVER_OPEN_API_URL);
            networkThread.setEncoding("UTF-8");
            networkThread.setHandler(handler);
            networkThread.setDaemon(true);
            networkThread.start();
            isThreadCreated = true;
        }
    }
    
    public void setHandler()
    {
        handler = new Handler() {
            
            @Override
            public void handleMessage(Message message) {
                
                switch (message.what) {
                case MESSAGE_NETWORK_DATA:
                    String html = (String)message.obj;
                    Intent intent = new Intent(NaverRankingConstant.MESSAGE_NAVER_DATA_RECEIVED);
                    intent.putExtra("message",html);
                    sendBroadcast(intent);
                    break;
                }
            }
        };
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}