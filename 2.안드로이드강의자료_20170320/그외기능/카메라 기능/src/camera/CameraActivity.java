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
	// 카메라 찍은 후 저장될 파일 경로
	private String filePath;
	private String folderName = "test";// 폴더명
	private String fileName = "cameraImage"; // 파일명

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_test);
	}
	
	public void takeCamera(View view){
		// 저장할 파일 설정
		// 외부저장소 경로
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();

		// 폴더명 및 파일명
		String folderPath = path + File.separator + folderName;
		filePath = path + File.separator + folderName + File.separator +  fileName + ".jpg";
		
		// 저장 폴더 지정 및 폴더 생성
		File fileFolderPath = new File(folderPath);
		fileFolderPath.mkdir();

		// 파일 이름 지정
		File file = new File(filePath);
		Uri outputFileUri = Uri.fromFile(file);		
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
		startActivity(intent);
	}
}

