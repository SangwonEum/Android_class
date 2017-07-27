package com.mytest.ch6;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.mytest2.R;

public class ImgDownloadActivity extends Activity {
	private TextView imageDownTextView;
	 private ImageDownTask imageDownTask;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  imageDownTextView = (TextView) findViewById(R.id.textView1);
	  imageDownTask = new ImageDownTask();
	  imageDownTask.execute("fileUrl_1", "fileUrl_2", "fileUrl_3",
	    "fileUrl_4", "fileUrl_5", "fileUrl_6", "fileUrl_7",
	    "fileUrl_8", "fileUrl_9");

	 }
	 // xml 버튼 onClick 이벤트 처리
	 public void onClick(View v) {
	  if (imageDownTextView != null
	    && imageDownTask.getStatus() != Status.FINISHED) {
		  imageDownTask.cancel(true);
	  }
}
	 
 // 내부 클래스
 private class ImageDownTask extends AsyncTask<String, Integer, Boolean> {

  // 작업자 스레드 시작전 보여주는 UI
  @Override
  protected void onPreExecute() {
	  imageDownTextView.setText("이미지 다운로드 중...");
  }
  // 작업자 스레드 (메인 스레드와 분리되어 있다 Thread 의 run() 의 역활)
  @Override
  protected Boolean doInBackground(String... imageUrls) {
   int totalCount = imageUrls.length;
   for (int i = 0; i <= totalCount; i++) {
    publishProgress(i, totalCount);
    try {
     Thread.sleep(1000);
    } catch (InterruptedException e) {
     return false;
    }
   }
   return true;
  }

  // 스레드가 진행되는 동안 동작할 부분
  @Override
  protected void onProgressUpdate(Integer... downInfo) {

   int currentCount = downInfo[0];
   int totalCount = downInfo[1];

   imageDownTextView.setText("다운로드 받은 이미지 수 : " + currentCount + " / "
     + totalCount);
  }
  // 스레드가 취소되었을 경우
  @Override
  protected void onCancelled() {
	  imageDownTextView.setText("다운로드 취소");
   super.onCancelled();
  }

  // 스레드 완료 시 동작 하는 부분
  @Override
  protected void onPostExecute(Boolean result) {
   if (true == result) {
	   imageDownTextView.setText("이미지 다운로드 완료");
   } else {
	   imageDownTextView.setText("이미지 다운로드 실패");
   }
   super.onPostExecute(result);
  }
 }
}
	 
