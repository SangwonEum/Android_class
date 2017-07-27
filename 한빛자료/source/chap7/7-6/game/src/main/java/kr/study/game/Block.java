package kr.study.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kenz on 15/04/19.
 */
public class Block implements DrawableItem {

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

    private boolean mIsCollision = false; // 충돌 상태를 기록하는 플래그

    /**
     * 공이 충돌했을 때 처리
     */
    public void collision() {
        mIsCollision = true; // 충돌했다는 것만 기록하고 실제 파괴는 draw()시에 한다.
    }

    private boolean mIsExist = true; // 블록이 존재하는가?

    /**
     * 블록이 존재하는가
     * @return 존재하면 true
     */
    public boolean isExist() {
        return mIsExist;
    }

    /**
     * @param canvas
     * @param paint
     */
    public void draw(Canvas canvas, Paint paint) {
        if (mIsExist) {
            // 내구성이 0 이상인 경우만
            if (mIsCollision) {
                mHard--;
                mIsCollision = false;
                if (mHard <= 0) {
                    mIsExist = false;
                    return;
                }
            }
// 색칠 부분 그리기
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mLeft, mTop, mRight, mBottom, paint); // 테두리 부분 그리기
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4f);
            canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);
        }
    }
}
