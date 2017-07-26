package com.example.administrator.gameactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

    private ArrayList<Block> mItemList;

    private boolean mIsCollision = false;
    private boolean misExist = true;


    public void collision(){
        mIsCollision = true;
    }

    public boolean isExist(){
        return misExist;
    }


    public Block(float top, float left, float bottom, float right){
        mTop  = top;
        mLeft = left;
        mBottom = bottom;
        mRight = right;
        mHard = 1;

    }
    public void draw(Canvas canvas, Paint paint){
        if(misExist){
            if(mIsCollision){
                mHard--;
                mIsCollision = false;
                if(mHard <= 0){
                    misExist = false;
                    return;
                }
            }
        }

        if(mHard >0){
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mLeft,mTop,mRight,mBottom,paint);

            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mLeft,mTop,mRight,mBottom,paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4f);
            canvas.drawRect(mLeft,mTop,mRight,mBottom,paint);
        }
    }




}
