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
	 // xml ��ư onClick �̺�Ʈ ó��
	 public void onClick(View v) {
	  if (imageDownTextView != null
	    && imageDownTask.getStatus() != Status.FINISHED) {
		  imageDownTask.cancel(true);
	  }
}
	 
 // ���� Ŭ����
 private class ImageDownTask extends AsyncTask<String, Integer, Boolean> {

  // �۾��� ������ ������ �����ִ� UI
  @Override
  protected void onPreExecute() {
	  imageDownTextView.setText("�̹��� �ٿ�ε� ��...");
  }
  // �۾��� ������ (���� ������� �и��Ǿ� �ִ� Thread �� run() �� ��Ȱ)
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

  // �����尡 ����Ǵ� ���� ������ �κ�
  @Override
  protected void onProgressUpdate(Integer... downInfo) {

   int currentCount = downInfo[0];
   int totalCount = downInfo[1];

   imageDownTextView.setText("�ٿ�ε� ���� �̹��� �� : " + currentCount + " / "
     + totalCount);
  }
  // �����尡 ��ҵǾ��� ���
  @Override
  protected void onCancelled() {
	  imageDownTextView.setText("�ٿ�ε� ���");
   super.onCancelled();
  }

  // ������ �Ϸ� �� ���� �ϴ� �κ�
  @Override
  protected void onPostExecute(Boolean result) {
   if (true == result) {
	   imageDownTextView.setText("�̹��� �ٿ�ε� �Ϸ�");
   } else {
	   imageDownTextView.setText("�̹��� �ٿ�ε� ����");
   }
   super.onPostExecute(result);
  }
 }
}
	 
