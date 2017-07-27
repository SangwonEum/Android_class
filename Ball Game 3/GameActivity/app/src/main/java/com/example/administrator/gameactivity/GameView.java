package com.example.administrator.gameactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-07-26.
 */

public class GameView extends TextureView implements TextureView.SurfaceTextureListener , View.OnTouchListener{


    static final int BLOCK_COUNT = 100;

    private Thread mThread;
    private Thread mThread2;
    private Block block;

    private Pad mPad;
    private float mPadHalfWidth;

    //private ArrayList<Block> mItemList;
    //private ArrayList<DrawableItem> mItemListDrawable;
    private ArrayList<DrawableItem> mItemList;

    //@param context Activity;
    volatile private boolean mIsRunnable;
    volatile private boolean mIsRunnable2;

    volatile private float mTouchedX;
    volatile private float mTouchedY;


    private Ball mBall;
    private float mBallRadius;

    float mBlockWidth ;
    float mBlockHeight;

    private int mLife;

    float padTop;

    float padLeft;
    float padRight;

    ToneGenerator toneGenerator;
    Vibrator vibrator ;

    public GameView(Context context){
        super(context);
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
        mLife = 5;
        //mBlockWidth = width
        toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC,ToneGenerator.MAX_VOLUME);
        vibrator  = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);


    }

    private Block getBlock(float x, float y){
        int index = (int) (x/ mBlockWidth) + (int)(y/ mBlockHeight) * 10;
        Log.i("x;", x + "");
        Log.i("y;", y + "");
        Log.i("index;", index + "");
        if( 0 <= index && index < 50){
            Block block  = (Block) mItemList.get(index);
            if(block.isExist()){
                //mItemList.set(index,null);
               block.SetExistToFalse();
                return block;
            }
        }
        return null;
    }

    public boolean onTouch(View v, MotionEvent event){

        setOnTouchListener(this);
        mTouchedX = event.getX();
        mTouchedY = event.getY();
        return true;
    }

    public class Pad implements DrawableItem {

        private float mTop;
        private float mLeft;
        private float mBottom;
        private float mRight;

        public  Pad(float top, float bottom) {
            mTop = top;
            mBottom = bottom;
        }

        public void setLeftRight(float left, float right) {
            mLeft = left;
            mRight = right;
        }

        public void draw(Canvas canvas, Paint paint) {
            //paint.setColor(Color.LTGRAY);
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);

        }
        public float getmTop(){
            return mTop;
        }
    }

    public void start(){

        mThread2 = new Thread(new Runnable(){

            @Override
            public void run(){
               // Log.i("runngin","Thread 2");
                Paint paint = new Paint();
                //paint.setColor(Color.RED);
                //paint.setStyle(Paint.Style.FILL);


                while(mIsRunnable2) {
                    synchronized (GameView.this) {
                        if (!mIsRunnable2) {
                            break;
                        }
                        Canvas canvas2 = lockCanvas();
                        if (canvas2 == null) {
                            continue;
                        }
                        canvas2.drawColor(Color.BLACK);

                        float padLeft = mTouchedX - mPadHalfWidth;
                        float padRight = mTouchedX + mPadHalfWidth;
                        mPad.setLeftRight(padLeft,padRight);
                        mPad.draw(canvas2,paint);

                        mBall.move();

                        float ballTop = mBall.getmY() - mBallRadius;
                        float ballLeft = mBall.getmX() - mBallRadius;

                        float ballBottom = mBall.getmY() + mBallRadius;
                        float ballRight = mBall.getmX() + mBallRadius;

                        if(ballLeft < 0 && mBall.getmSpeedX() < 0 || ballRight >= getWidth() && mBall.getmSpeedX() > 0){
                            toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,10);
                            mBall.setmSpeedX(-mBall.getmSpeedX());
                        }
                        if(ballTop <0 || ballBottom > getHeight()){
                            toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,10);
                            mBall.setmSpeedX(-mBall.getmSpeedY());
                        }
                        if(ballTop > getHeight()){
                            if(mLife >0){
                                mLife--;
                                mBall.reset();
                            }
                        }

                        Block leftBlock = getBlock(ballLeft, mBall.getmY());
                        Block topBlock = getBlock(mBall.getmX(), ballTop);
                        Block rightBlock = getBlock(ballRight , mBall.getmY());
                        Block bottomBlock = getBlock(mBall.getmX(), ballBottom);

                       // Log.i("leftBlock:",leftBlock+"");
                        //Log.i("toptBlock:",topBlock+"");
                        //Log.i("rightBlock:",rightBlock+"");
                        //Log.i("bottomBlock:",bottomBlock+"");

                        if(leftBlock != null){
                            vibrator.vibrate(300);
                            toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,10);

                            mBall.setmSpeedX(-mBall.getmSpeedX());
                            leftBlock.collision();
                        }
                        if(topBlock != null){
                            vibrator.vibrate(300);
                            toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,10);

                            mBall.setmSpeedY(-mBall.getmSpeedY());
                            topBlock.collision();
                        }
                        if(rightBlock != null){
                            vibrator.vibrate(300);
                            toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,10);

                            mBall.setmSpeedY(-mBall.getmSpeedX());
                            rightBlock.collision();
                        }
                        if(bottomBlock != null){
                            vibrator.vibrate(300);
                            toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,10);

                            mBall.setmSpeedY(-mBall.getmSpeedY());
                            bottomBlock.collision();
                        }

                        padTop  = mPad.getmTop();
                       // Log.i("ballBottom:",ballBottom+"");
                       // Log.i("ballTop:",ballTop+"");
                       // Log.i("padTop:",padTop+"");
                       // Log.i("X:",mBall.getmX()+"");
                       // Log.i("Y:",mBall.getmY()+"");

                        float ballSpeedY = mBall.getmSpeedY();
                        float ballSpeedX = mBall.getmSpeedX() + (mBall.getmX() - mTouchedX)/10;
                        if(ballBottom > padTop  && ballBottom  - ballSpeedY < padTop && padLeft < ballRight && padRight > ballLeft){
                            if(ballSpeedY < mBlockHeight/3){
                                ballSpeedY *=-1.05f;
                            }else{
                                ballSpeedY  = -ballSpeedY;
                            }
                            if(ballSpeedX > mBlockWidth/5){
                                ballSpeedX =  mBlockWidth / 5;
                            }
                            mBall.setmSpeedY(ballSpeedY);
                            mBall.setmSpeedX(ballSpeedX);
                        }


                       if(ballTop < 0 || ballBottom > getHeight()){
                           mBall.setmSpeedY(-mBall.getmSpeedY());
                       }
                       if(ballTop < 0){
                           mBall.setmSpeedY(-mBall.getmSpeedY());
                       }
                       if(ballTop > getHeight()){
                           if(mLife> 0){
                               mLife--;
                               mBall.reset();
                           }
                       }





                        for (DrawableItem item : mItemList) {
                            item.draw(canvas2, paint);
                        }
                        unlockCanvasAndPost(canvas2);
                    }

                }//while 끝
                toneGenerator.release();
            }
        });

       /* mThread  = new Thread(new Runnable() {@Override
        public void run() {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            int i = 0;
            while(mIsRunnable) {
                //Log.i("runngin",i+"");
                Canvas canvas = lockCanvas();
                if (canvas == null) {
                    // Log.i("null",i+"");
                    continue;
                }
                canvas.drawColor(Color.BLACK);
                canvas.drawCircle(100,i,50,paint);
                unlockCanvasAndPost(canvas);
                i++;
                if(i > 1000){
                    i = 0;
                }
            }
        }});*/
        // mThread.start();
        //mIsRunnable = true;

        mIsRunnable2 = true;
        mThread2.start();

    }
    public void stop(){
        mIsRunnable = false;
    }
    public void readyObjects(int width, int height){
        //float blockWidth = width/10;
        //float blockHeight = height/20;

        mBlockWidth = width/ 10;
        mBlockHeight = height/20;


        mItemList = new ArrayList<DrawableItem>();
        for(int i=0; i < 50; i++){
            float blockTop = i/10 * mBlockHeight;
            float blockLeft = i%10 * mBlockWidth;
            float blockBottom = blockTop + mBlockHeight;
            float blockRight = blockLeft + mBlockWidth;
            int index = i;
            mItemList.add(new Block(blockTop, blockLeft,blockBottom, blockRight,i));
        }
        mPad = new Pad(height * 0.8f, height * 0.85f);
        mItemList.add(mPad);

        mPadHalfWidth = width/ 10;
        mBallRadius = width < height ? width/ 40 : height/40;
        mBall = new Ball(mBallRadius , width/2 , height/2);
        mItemList.add(mBall);
        //mBall = new Ball(mBallRadius , 0, 0);

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
       // Canvas canvas  = lockCanvas();
       // Paint paint  = new Paint();
       // paint.setStyle(Paint.Style.FILL);
       // paint.setColor(Color.RED);
       // canvas.drawCircle(300,300,200,paint);
       // unlockCanvasAndPost(canvas);
       readyObjects(width, height);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        readyObjects(width,height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        synchronized(this){
            return true;
        }

    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


}
