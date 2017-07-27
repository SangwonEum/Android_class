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
                    long startTime = System.currentTimeMillis();
                    // 앱 실행 중에 반복해서 호출된다.
                    synchronized (GameView.this) {
                        if (!mIsRunnable) {
                            break; // 루프를 종료한다.
                        }
                        Canvas canvas = lockCanvas();
                        if (canvas == null) {
                            continue; // 캔버스를 획득할 수 없으면 루프를 다시 시작한다.
                        }
                        if (mBall == null) {
                            continue;
                        }
                        canvas.drawColor(Color.BLACK);
                        float padLeft = mTouchedX - mPadHalfWidth;
                        float padRight = mTouchedX + mPadHalfWidth;
                        mPad.setLeftRight(padLeft, padRight);
                        mBall.move();
                        float ballTop = mBall.getY() - mBallRadius;
                        float ballLeft = mBall.getX() - mBallRadius;
                        float ballBottom = mBall.getY() + mBallRadius;
                        float ballRight = mBall.getX() + mBallRadius;
                        if (ballLeft < 0 && mBall.getSpeedX() < 0 || ballRight >= getWidth() && mBall.getSpeedX() > 0) {
                            mBall.setSpeedX(-mBall.getSpeedX());    // 가로방향 벽에 부딪혔으므로 가로 속도를 반전
                        }
                        if (ballTop < 0 || ballBottom > getHeight()) {
                            mBall.setSpeedY(-mBall.getSpeedY()); // 세로 방향 벽에 부딪혔으므로 세로 속도를 반전
                        }

                        for (DrawableItem item : mItemList) {
                            // mItemList의 내용이 하나씩 block에 전달된다
                            item.draw(canvas, paint);
                        }
                        unlockCanvasAndPost(canvas);
                    }
                    long sleepTime = 16 - System.currentTimeMillis() + startTime;
                    if (sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                        }
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
    private Ball mBall;
    private float mBallRadius;

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
        mBallRadius = width < height ? width / 40 : height / 40;
        mBall = new Ball(mBallRadius, width / 2, height / 2);
        mItemList.add(mBall);
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
