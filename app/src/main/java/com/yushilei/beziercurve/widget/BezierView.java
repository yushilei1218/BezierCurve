package com.yushilei.beziercurve.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author by  yushilei.
 * @time 2016/9/13 -09:54.
 * @Desc
 */
public class BezierView extends View {
    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(7f);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(25f);
    }

    Path mPath = new Path();
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    int mPadding = 20;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        int width = getWidth();
        int height = getHeight();
        mPath.moveTo(mPadding, height - mPadding);
        mPath.quadTo(mPadding, mPadding, width - mPadding, mPadding);

        canvas.drawTextOnPath("二阶贝塞尔曲线示例", mPath, 500, 50, mTextPaint);

        canvas.drawPath(mPath, mPaint);

    }
}
