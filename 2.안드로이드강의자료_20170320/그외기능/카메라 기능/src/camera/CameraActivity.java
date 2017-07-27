package com.mytest.ch4;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.mytest2.R;

public class CameraActivity extends Activity {
	// ī�޶� ���� �� ����� ���� ���
	private String filePath;
	private String folderName = "test";// ������
	private String fileName = "cameraImage"; // ���ϸ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_test);
	}
	
	public void takeCamera(View view){
		// ������ ���� ����
		// �ܺ������ ���
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();

		// ������ �� ���ϸ�
		String folderPath = path + File.separator + folderName;
		filePath = path + File.separator + folderName + File.separator +  fileName + ".jpg";
		
		// ���� ���� ���� �� ���� ����
		File fileFolderPath = new File(folderPath);
		fileFolderPath.mkdir();

		// ���� �̸� ����
		File file = new File(filePath);
		Uri outputFileUri = Uri.fromFile(file);		
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
		startActivity(intent);
	}
}

