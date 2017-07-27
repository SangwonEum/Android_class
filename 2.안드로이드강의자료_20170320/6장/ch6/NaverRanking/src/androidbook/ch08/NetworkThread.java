package androidbook.ch08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;

public class NetworkThread extends Thread {
    
    private Handler handler; 
    private String url;
    private String encoding;
    private int loopCount = 0;
    
    @Override
    public void run() {
        
        for( int i = 0 ; i < loopCount ; i++)
        {
            try
            {
                String html = download(this.url,this.getEncoding());
                Message message = Message.obtain(handler,NaverRankingService.MESSAGE_NETWORK_DATA,html);
                handler.sendMessage(message);
                
                Thread.sleep(NaverRankingConstant.DATA_INTERVAL);
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String download(String urlString,String encode) throws IOException
    {
        String html = null;
        HttpURLConnection urlCon = null;
        
        URL url = new URL(urlString);
        urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setUseCaches(false);
        urlCon.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/533.2 (KHTML, like Gecko) Chrome/5.0.342.3 Safari/533.2");
        
        html = getContents(urlCon.getInputStream(),encode);
        urlCon.disconnect();
        
        return html;
    }
    
    public static String getContents(InputStream is,String encode) throws IOException
    {
        if( is == null )
            return null;
        
        String s = null;
        StringBuffer dataBuffer = new StringBuffer();
        
        BufferedReader resultStreamBuffer = new BufferedReader(new InputStreamReader(is,encode));
        while((s = resultStreamBuffer.readLine()) != null)
        {
            dataBuffer.append(s);
            dataBuffer.append("\r\n");
        }
        resultStreamBuffer.close();
        
        return dataBuffer.toString();
    }   

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setLoopCount(int loopCount) {
        this.loopCount = loopCount;
    }

    public int getLoopCount() {
        return loopCount;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }

}
