package kr.study.game;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball implements DrawableItem {
    private float mX;
    private float mY;
    private float mSpeedX;
    private float mSpeedY;
    private final float mRadius;

    public float getSpeedX() {
        return mSpeedX;
    }

    public float getSpeedY() {
        return mSpeedY;
    }

    public float getY() {
        return mY;
    }

    public float getX() {
        return mX;
    }

    public void setSpeedX(float speedX) {
        mSpeedX = speedX;
    }

    public void setSpeedY(float speedY) {
        mSpeedY = speedY;
    }

    public Ball(float radius, float initialX, float initialY) {
        mRadius = radius;
        mSpeedX = radius / 5;
        mSpeedY = -radius / 5;
        mX = initialX;
        mY = initialY;
    }

    public void move() {
        mX += mSpeedX;
        mY += mSpeedY;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mX, mY, mRadius, paint);
    }
}
