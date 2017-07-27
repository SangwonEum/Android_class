package com.example.administrator.gameactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.ToneGenerator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-07-26.
 */

public class Block implements  DrawableItem{
    private float mTop;
    private float mLeft;
    private float mBottom;
    private float mRight;
    private int mHard;
    private int index;

    private ArrayList<Block> mItemList;

    private boolean mIsCollision = false;
    private boolean misExist = true;




    public void collision(){

        mIsCollision = true;
    }

    public boolean isExist(){
         return misExist;
    }

    public void SetExistToFalse(){
        misExist = false;
    }


    public Block(float top, float left, float bottom, float right,int i){
        mTop  = top;
        mLeft = left;
        mBottom = bottom;
        mRight = right;
        mHard = 1;
        index = i;


    }


    public void draw(Canvas canvas, Paint paint) {
        if (misExist) {
            if (mIsCollision) {
               // toneGenerator.startTone(ToneGenerator.TONE_DTMF_0,30);
                mHard--;
                mIsCollision = false;
                if (mHard <= 0) {
                    misExist = false;
                    return;
                }
            }


            if (mHard > 0) {
                if (index == 4 || index == 48) {
                    paint.setColor(Color.RED);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);

                    paint.setColor(Color.RED);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);

                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(4f);
                    canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);

                } else {


                    paint.setColor(Color.BLUE);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);

                    paint.setColor(Color.BLUE);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);

                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(4f);
                    canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);
                }
            }
        }
    }



}
