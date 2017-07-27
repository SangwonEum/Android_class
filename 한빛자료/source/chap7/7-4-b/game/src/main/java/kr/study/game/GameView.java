package kr.study.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

import java.util.ArrayList;

/**
 * 게임 화면을 그리기 위한 TextureView
 */
public class GameView extends TextureView implements TextureView.SurfaceTextureListener,View.OnTouchListener {
    private Thread mThread;
    volatile private boolean mIsRunnable;
    volatile private float mTouchedX;
    volatile private float mTouchedY;

    /**
     * 슈퍼 클래스에는 기본 생성자가 없으므로 인수가 있는 생성자를 명시적으로 호출한다
     *
     * @param context Activity
     */

    public GameView(Context context) {
        super(context); // 슈퍼 클래스의 생성자를 인수 context로 호출한다.
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
    }

    public void start() {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                while (true) {
                    // 앱 실행 중에 반복하여 호출된다.
                    synchronized (GameView.this) {
                        if (!mIsRunnable) {
                            break; // 루프를 종료한다.
                        }
                        Canvas canvas = lockCanvas();
                        if (canvas == null) {
                            continue;
                        }
                        canvas.drawColor(Color.BLACK);
                        float padLeft = mTouchedX - mPadHalfWidth;
                        float padRight = mTouchedX + mPadHalfWidth;
                        mPad.setLeftRight(padLeft, padRight);
                        for (DrawableItem item : mItemList) {
                            // mItemList의 내용이 하나씩 block에 전달된다.
                            item.draw(canvas, paint);
                        }
                        unlockCanvasAndPost(canvas);
                    }
                }
            }
        });
        mIsRunnable = true;
        mThread.start();
    }


    public void stop() {
        mIsRunnable = false;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        readyObjects(width, height);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        readyObjects(width, height);
    }


    private ArrayList<DrawableItem> mItemList;
    private Pad mPad;
    private float mPadHalfWidth;

    public void readyObjects(int width, int height) {
        float blockWidth = width / 10;
        float blockHeight = height / 20;
        mItemList = new ArrayList<DrawableItem>();
        for (int i = 0; i < 100; i++) {
            float blockTop = i / 10 * blockHeight;
            float blockLeft = i % 10 * blockWidth;
            float blockBottom = blockTop + blockHeight;
            float blockRight = blockLeft + blockWidth;
            mItemList.add(new Block(blockTop, blockLeft, blockBottom, blockRight));
        }
        mPad = new Pad(height * 0.8f, height * 0.85f);
        mItemList.add(mPad);
        mPadHalfWidth = width / 10;
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        synchronized (this) {
            return true;
        }
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
