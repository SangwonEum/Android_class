package kr.study.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

import java.util.ArrayList;

/**
 * 게임 화면을 그리기 위한 TextureView
 */
public class GameView extends TextureView implements TextureView.SurfaceTextureListener,View.OnTouchListener {
    private static final String KEY_LIFE = "life";
    private static final String KEY_GAME_START_TIME = "game_start_time";
    private static final String KEY_BALL = "ball";
    private static final String KEY_BLOCK = "block";

    static final int BLOCK_COUNT = 100;
    volatile private boolean mIsRunnable;
    volatile private float mTouchedX;
    volatile private float mTouchedY;
    private Thread mThread;
    private int mLife;
    private long mGameStartTime;
    private Handler mHandler;
    private final Bundle mSavedInstanceState;

    /**
     * 슈퍼 클래스에는 기본 생성자가 없으므로 인수가 있는 생성자를 명시적으로 호출한다
     *
     * @param context Activity
     */

    public GameView(final Context context, Bundle savedInstanceState) {
        super(context); // 부모 클래스의 생성자를 인수 context로 호출한다.
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
        mSavedInstanceState = savedInstanceState; // 인수로 가져온 Bundle을 멤버 변수에 저장한다.

        mHandler = new Handler() {
            // UI Thread에서 실행되는 핸들러
            @Override
            public void handleMessage(Message message) {
                // 실행할 처리
                Intent intent = new Intent(context, ClearActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtras(message.getData());
                context.startActivity(intent);
            }
        };
    }


    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LIFE, mLife);
        outState.putLong(KEY_GAME_START_TIME, mGameStartTime);
        outState.putBundle(KEY_BALL, mBall.save(getWidth(), getHeight()));
        for (int i = 0; i < BLOCK_COUNT; i++) {
            outState.putBundle(KEY_BLOCK + String.valueOf(i), mBlockList.get(i).save());
        }
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
                    long startTime = System.currentTimeMillis();
                    synchronized (GameView.this) {
                        if (!mIsRunnable) {
                            break; // 루프를 종료한다.
                        }
                        Canvas canvas = lockCanvas();
                        if (canvas == null) {
                            continue; // 캔버스를 가져올 수 없을 때는 루프를 다시 한다.
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
                            mBall.setSpeedX(-mBall.getSpeedX()); // 가로 방향 벽에 부딪혔으므로 가로 속도 반전
                        }
                        if (ballTop < 0) {
                            mBall.setSpeedY(-mBall.getSpeedY()); // 세로 방향 벽에 부딪혔으므로 세로 속도 반전
                        }
                        if (ballTop > getHeight()) {
                            if (mLife > 0) {
                                mLife--;
                                mBall.reset();
                           } else {
                            unlockCanvasAndPost(canvas);
                            Message message = Message.obtain();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(ClearActivity.EXTRA_IS_CLEAR, false);
                            bundle.putInt(ClearActivity.EXTRA_BLOCK_COUNT, getBlockCount());
                            bundle.putLong(ClearActivity.EXTRA_TIME,System.currentTimeMillis() - mGameStartTime);
                            message.setData(bundle);
                            mHandler.sendMessage(message);
                            return;
                        }

                    }
                        // 블록과 공의 충돌 판정
                        Block leftBlock = getBlock(ballLeft, mBall.getY());
                        Block topBlock = getBlock(mBall.getX(), ballTop);
                        Block rightBlock = getBlock(ballRight, mBall.getY());
                        Block bottomBlock = getBlock(mBall.getX(), ballBottom);
                        boolean isCollision = false;
                        // 충돌한 블록이 존재하면 충돌 처리를 한다.
                        if (leftBlock != null) {
                            mBall.setSpeedX(-mBall.getSpeedX());
                            leftBlock.collision();
                        }
                        if (topBlock != null) {
                            mBall.setSpeedY(-mBall.getSpeedY());
                            topBlock.collision();
                        }
                        if (rightBlock != null) {
                            mBall.setSpeedX(-mBall.getSpeedX());
                            rightBlock.collision();
                        }
                        if (bottomBlock != null) {
                            mBall.setSpeedY(-mBall.getSpeedY());
                            bottomBlock.collision();
                        }
                        float padTop = mPad.getTop();
                        float ballSpeedY = mBall.getSpeedY();
                        if (ballBottom > padTop && ballBottom - ballSpeedY < padTop && padLeft < ballRight && padRight > ballLeft) {
                            if (ballSpeedY < mBlockHeight / 3) {
                                ballSpeedY *= -1.05f;
                            } else {
                                ballSpeedY = -ballSpeedY;
                            }
                            float ballSpeedX = mBall.getSpeedX() + (mBall.getX() - mTouchedX) / 10;
                            if (ballSpeedX > mBlockWidth / 5) {
                                ballSpeedX = mBlockWidth / 5;
                            }
                            mBall.setSpeedY(ballSpeedY);
                            mBall.setSpeedX(ballSpeedX);

                        }
                        for (DrawableItem item : mItemList) {
                            // mItemList의 내용이 하나씩 item에 전달된다.
                            item.draw(canvas, paint);
                        }
                        unlockCanvasAndPost(canvas);
                        if(isCollision && getBlockCount() == 0){
                            Message message = Message.obtain();
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(ClearActivity.EXTRA_IS_CLEAR, true);
                            bundle.putInt(ClearActivity.EXTRA_BLOCK_COUNT,0);
                            bundle.putLong(ClearActivity.EXTRA_TIME, System.currentTimeMillis() - mGameStartTime); message.setData(bundle);
                            mHandler.sendMessage(message);
                        }
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
    private ArrayList<Block> mBlockList;
    private Pad mPad;
    private float mPadHalfWidth;
    private Ball mBall;
    private float mBallRadius;
    private float mBlockWidth;
    private float mBlockHeight;

    public void readyObjects(int width, int height) {
        mBlockWidth = width / 10;
        mBlockHeight = height / 20;
        mItemList = new ArrayList<DrawableItem>();
        mBlockList = new ArrayList<Block>();
        for (int i = 0; i < BLOCK_COUNT ; i++) {
            float blockTop = i / 10 * mBlockHeight;
            float blockLeft = i % 10 * mBlockWidth;
            float blockBottom = blockTop + mBlockHeight;
            float blockRight = blockLeft + mBlockWidth;
            mBlockList.add(new Block(blockTop, blockLeft, blockBottom, blockRight));
        }
        mItemList.addAll(mBlockList);
        mPad = new Pad(height * 0.8f, height * 0.85f);
        mItemList.add(mPad);
        mPadHalfWidth = width / 10;
        mBallRadius = width < height ? width / 40 : height / 40;
        mBall = new Ball(mBallRadius, width / 2, height / 2);
        mItemList.add(mBall);
        mLife = 5;
        mGameStartTime = System.currentTimeMillis();
        if (mSavedInstanceState != null) {
            mLife = mSavedInstanceState.getInt(KEY_LIFE);
            mGameStartTime = mSavedInstanceState.getLong(KEY_GAME_START_TIME);
            mBall.restore(mSavedInstanceState.getBundle(KEY_BALL), width, height);
            for (int i = 0; i < BLOCK_COUNT; i++) {
                mBlockList.get(i).restore(mSavedInstanceState.getBundle(KEY_BLOCK + String.valueOf(i)));
            }
        }

    }

    private int getBlockCount(){
        int count = 0;
        for(Block block :mBlockList){
            if(block.isExist()){
                count ++; }
        }
        return count;
    }

    private Block getBlock(float x, float y) {
        int index = (int) (x / mBlockWidth) + (int) (y / mBlockHeight) * 10;
        if (0 <= index && index < BLOCK_COUNT) {
            Block block = (Block) mItemList.get(index);
            if (block.isExist()) {
                return block;
            }
        }
        return null;
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
