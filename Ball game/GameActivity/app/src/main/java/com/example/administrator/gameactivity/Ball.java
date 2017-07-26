package com.example.administrator.gameactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017-07-26.
 */

public class Ball implements DrawableItem{
    private float mX;
    private float mY;
    private float mSpeedX;
    private float mSpeedY;
    private final float mRadius;

    public Ball(float radius, float initialX, float initialY){
        mRadius = radius;
        mSpeedX = radius/5;
        mSpeedY = -radius/5;
        mX = initialX;
        mY = initialY;

    }
    public void move(){
        mX += mSpeedX;
        mY += mSpeedY;
    }

    public float getmSpeedX(){
        return mSpeedX;
    }
    public float getmSpeedY(){
        return mSpeedY;
    }
    public float getmY(){
        return mY;

    }
    public  float getmX(){
        return mX;
    }

    public void setmSpeedX(float speedX){
        mSpeedX  = speedX;

    }
    public void setmSpeedY(float speedY){
        mSpeedY = speedY;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mX,mY, mRadius,paint);
    }
}
