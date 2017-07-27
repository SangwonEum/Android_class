package com.mytest.ch6;

import com.mytest2.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity {
	private ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);
        mProgressBar = (ProgressBar) findViewById(R.id.btnProgressBar);
    }

    public void onClick(View view) {
        new BackgroundTask().execute();  //async 태스크를 실행한다.
        //new BackgroundTask().execute("Test");  
    }

    //
    private class BackgroundTask extends AsyncTask<String, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AsyncTaskActivity.this, "BackgroundTask 시작전",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Long doInBackground(String... datas) {

            long total = 0;
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                publishProgress(i);  //백그라운드에서 중간 처리 상태를 알리려고 호출한다. 그러면 onProgressUpdate가 호출된다.
                total = i;
            }

            return total;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            mProgressBar.setProgress(progress[0]);
        }

        
        @Override
        //백그라운드 작업이 완료되면 호출딘다.
        protected void onPostExecute(Long result) {
            Toast.makeText(AsyncTaskActivity.this, "BackgroundTask 종료됨",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
   
}