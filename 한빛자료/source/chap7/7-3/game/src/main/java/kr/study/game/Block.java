package kr.study.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block {
    private final float mTop;
    private final float mLeft;
    private final float mBottom;
    private final float mRight;
    private int mHard;

    public Block(float top, float left, float bottom, float right) {
        mTop = top;
        mLeft = left;
        mBottom = bottom;
        mRight = right;
        mHard = 1;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (mHard > 0) {
            // 내구성이 0 이상일 때만
            // 색칠 부분 그리기
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);
            // 테두리선 부분 그리기
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4f);
            canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);
        }
    }
}
