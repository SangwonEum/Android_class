package com.mytest.ch10c;

import com.mytest2.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainCameraActivity extends ActionBarActivity {
	public static String IMAGE_FILE = "photo1.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_main);

        final CameraSurfaceView cameraView = new CameraSurfaceView(getApplicationContext());
        FrameLayout previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
        previewFrame.addView(cameraView);

        // 버튼 이벤트 처리
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Camera.PictureCallback pic=new Camera.PictureCallback() {
                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            String outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(),
                            		bitmap, IMAGE_FILE, "Captured Image using Camera.");


                            if (outUriStr == null) {
                                Log.d("SampleCapture", "Image insert failed.");
                                return;
                            } else {
                                Uri outUri = Uri.parse(outUriStr);
                                //저장한 사진 파일을 미디어 DB에 등록한다.
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
                            }

                            Toast.makeText(getApplicationContext(), "카메라로 찍은 사진을 앨범에 저장했습니다.", Toast.LENGTH_LONG).show();

                            // restart
                            camera.startPreview();
                        } catch (Exception e) {
                            Log.e("SampleCapture", "Failed to insert image.", e);
                        }
                    }
                };
            	
                cameraView.capture(pic);
            }
        });

	}

     // 카메라 미리보기를 위한 서피스뷰 정의
    private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);

            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
        }
        public void surfaceCreated(SurfaceHolder holder) {
            //서피스 생성 시 카메라를 실행 해서 미리 보기를 한다.
        	camera = Camera.open();

            try {
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                Log.e("CameraSurfaceView", "Failed to set camera preview.", e);
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        	//서피스가 변경 되었을 시 미리보기를 한다.
        	camera.startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        public boolean capture(Camera.PictureCallback handler) {
            if (camera != null) {
                //카메라를 찍는다.
            	camera.takePicture(null, null, handler);
                return true;
            } else {
                return false;
            }
        }

    }
	
}
