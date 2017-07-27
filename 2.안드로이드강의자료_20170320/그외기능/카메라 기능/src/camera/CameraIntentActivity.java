package com.mytest.ch10c;

import java.io.File;
import java.util.List;

import com.mytest2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class CameraIntentActivity extends Activity implements OnClickListener {
	private final int TAKE_CAMERA = 100;		// ī�޶� ����Ʈ ��� �� onActivityResult���� ����� requestCode
	private final String TAG = "CameraIntentActivity";

	private Button btnCameraIntent;
	private ImageView ivResult;
	
	// ī�޶� ���� �� ����� ���� ���
	private String filePath;
	private String folderName = "test";// ������
	private String fileName = "cameraImage"; // ���ϸ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_intent);

		// �� ���� �Ҵ�
		btnCameraIntent = (Button) findViewById(R.id.btnCameraIntent);
		ivResult = (ImageView) findViewById(R.id.ivResult);

		// ��ư Ŭ�������� ����
		btnCameraIntent.setOnClickListener(this);
	}

	// onClick �̺�Ʈ
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnCameraIntent:
			Intent intent = new Intent();
			Camera camera = Camera.open();
			Camera.Parameters parameters = camera.getParameters();
			List<Size> sizeList = parameters.getSupportedPictureSizes();
			// ī�޶� SupportedPictureSize��� ��� �α�
			// for(int i=0; i<sizeList.size(); i++){
				// Size size = sizeList.get(i);
				//	Log.d(TAG, "Width : " + size.width + ", Height : " + size.height);
			// }
			// ���ϴ� ����ȭ ����� 1280x720 ���� ����
			Camera.Size size =  getOptimalPictureSize(parameters.getSupportedPictureSizes(), 1280, 720);
			Log.d(TAG, "Selected Optimal Size : (" + size.width + ", " + size.height + ")");
			parameters.setPreviewSize(size.width,  size.height);
			parameters.setPictureSize(size.width,  size.height);
			camera.setParameters(parameters);
			camera.release();
			
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
			
			
			// ī�޶� �۵���Ű�� Action���� ����Ʈ ����, OutputFileURI �߰�
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
			// requestCode�����ؼ� ����Ʈ ����
			startActivityForResult(intent, TAKE_CAMERA);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK){
			if(requestCode == TAKE_CAMERA){
				// ī�޶� ��� �׼� ��, ������ ������ ��Ʈ������ ���� �̹����信 ����
				BitmapFactory.Options options = new BitmapFactory.Options();
			    options.inPreferredConfig = Config.RGB_565;
			    Bitmap bm = BitmapFactory.decodeFile(filePath, options);
			    ivResult.setImageBitmap(bm);
			    
				// Background�� Drawable�� ����� ��쿣 �Ʒ��� ���� ���
				//	Drawable drawable = new BitmapDrawable(bm);
				//	btnCameraIntent.setBackground(drawable);

			    
			    // Intent�� "data"�� �Ѿ�� ��Ʈ���� �̹����信 ���� (���ػ� ����ϸ� �Ѿ�ͼ� ��� �Ұ�)
//			    Bitmap bm = (Bitmap)data.getExtras().get("data");
//			    ivResult.setImageBitmap(bm);
			    
				// File ���� ���� (������� ����)
//				try {
//					// �ܺ������ ���
//					String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//
//					// ������ �� ���ϸ�
//					String folderName = "Arcanelux";// ������
//					String fileName = "CameraIntent"; // ���ϸ�
//
//					// ���� ��� �� ���� ���
//					String folderPath = path + "/" + folderName;
//					String filePath = folderPath + "/" + fileName + ".png";
//
//					// ���� ���� ���� �� ���� ����
//					File fileFolderPath = new File(folderPath);
//					fileFolderPath.mkdir();
//
//					// ���� �̸� ����
//					File file = new File(filePath);
//					FileOutputStream fos = new FileOutputStream(file);
//
//					// ��Ʈ���� PNG������� �����Ͽ� ����
//					if (fos != null){ 
//						bm.compress(Bitmap.CompressFormat.PNG, 100, fos); 
//						fos.close(); 
//					}
//
//					// �α� �� �佺Ʈ
//					String logMessage = "File Save Success, File : " + filePath;
//					Toast.makeText(getApplicationContext(), logMessage, Toast.LENGTH_LONG).show();
//					Log.d(TAG, logMessage);
//				} catch (Exception e)	{
//					e.printStackTrace();
//					Log.d(TAG, "File Save Failed");
//				} 
			}
		}
	}

	
	// ������ �ػ󵵿� ���� ����ȭ �� ī�޶� ĸ�� ������ �����ִ� �Լ�
	private Size getOptimalPictureSize(List<Size> sizeList, int width, int height){
		Log.d(TAG, "getOptimalPictureSize, ���� width,height : (" + width + ", " + height + ")");
		Size prevSize = sizeList.get(0);
		Size optSize = sizeList.get(1);
		for(Size size : sizeList){
			// ���� ������� ���ϴ� �������� ����
			int diffWidth = Math.abs((size.width - width));
			int diffHeight = Math.abs((size.height - height));

			// ���� ������� ���ϴ� �������� ����
			int diffWidthPrev = Math.abs((prevSize.width - width));
			int diffHeightPrev = Math.abs((prevSize.height - height));

			// ������� ����ȭ ������� ���ϴ� �������� ����
			int diffWidthOpt = Math.abs((optSize.width - width));
			int diffHeightOpt = Math.abs((optSize.height - height));

			// ���� ������� ���� �������� ���λ����� ���̰� ���� ��� && ������� ����ȭ �� ���γ��� ���̺��� ���� ���γ��� ���̰� ���ų� ���� ��쿡�� ����
			if(diffWidth < diffWidthPrev && diffHeight <= diffHeightOpt){
				optSize = size;
				Log.d(TAG, "���λ����� ���� / ���� ���λ����� : " + prevSize.width + ", �� ���λ����� : " + optSize.width);
			}
			// ���� ������� ���� �������� ���λ����� ���̰� ���� ��� && ������� ����ȭ �� ���α��� ���̺��� ���� ���α��� ���̰� ���ų� ���� ��쿡�� ����
			if(diffHeight < diffHeightPrev && diffWidth <= diffWidthOpt){
				optSize = size;
				Log.d(TAG, "���λ����� ���� / ���� ���λ����� : " + prevSize.height + ", �� ���λ����� : " + optSize.height);
			}

			// ������� ����� ����� ���� ������� ����
			prevSize = size;
		}
		Log.d(TAG, "��� OptimalPictureSize : " + optSize.width + ", " + optSize.height);
		return optSize;
	}

}
