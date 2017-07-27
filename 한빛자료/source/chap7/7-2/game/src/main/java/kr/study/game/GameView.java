package kr.study.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

/**
 * 게임 화면을 그리기 위한 TextureView
 */
public class GameView extends TextureView implements TextureView.SurfaceTextureListener,View.OnTouchListener  {
    /**
     * 슈퍼 클래스에는 기본 생성자가 없으므로 인수가 있는 생성자를 명시적으로 호출한다.
     * @param  context Activity
     */
    private Thread mThread;
    volatile private boolean mIsRunnable;
    volatile private float mTouchedX;
    volatile private float mTouchedY;

    public void start(){
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                int i = 0 ;
                while (mIsRunnable) {
                    // 앱 실행 중에 반복 호출된다.
                    Canvas canvas = lockCanvas();
                    if (canvas == null) {
                        continue;
                    }
                    canvas.drawColor(Color.BLACK);
                    // mTouchedX와 mTouchedY를 중심으로 한다.
                    canvas.drawCircle(mTouchedX, mTouchedY, 50, paint);
                    unlockCanvasAndPost(canvas);
                    i++;
                    if (i > 1000) {
                        i =0 ;
                    }
                }

                Canvas canvas = lockCanvas();
                if(canvas == null){
                    return;
                }

                canvas.drawCircle(100,100,50,paint);
                unlockCanvasAndPost(canvas);

            }
        });
        mIsRunnable = true;
        mThread.start();
    }

    public void stop(){
        mIsRunnable = false;
    }

    public GameView(Context context) {
        super(context); // 슈퍼 클래스의 생성자를 인수 context로 호출한다.
        setSurfaceTextureListener(this);		//추가
        setOnTouchListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mTouchedX = event.getX();
        mTouchedY = event.getY();
        return true;
    }
}
