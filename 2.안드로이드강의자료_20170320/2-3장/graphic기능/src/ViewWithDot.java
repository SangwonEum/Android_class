package com.mytest.ch3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public   class ViewWithDot extends View {

    public ViewWithDot(Context context) {
        super(context);
    }
    
    @Override protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);  //캔버스의 배경색을 노란색으로 설정한다.

        Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG); //Paint객체를 생성한다.
        circlePaint.setColor(Color.RED);  //원의 둘레색을 빨강으로 설정한다.
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/3, circlePaint);
        
    }
}

