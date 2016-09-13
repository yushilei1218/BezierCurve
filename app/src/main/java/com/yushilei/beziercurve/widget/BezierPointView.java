package com.yushilei.beziercurve.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by  yushilei.
 * @time 2016/9/13 -10:33.
 * @Desc
 */
public class BezierPointView extends View {
    List<PointF> mPointFs = new ArrayList<>();
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path mPath = new Path();

    TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    public BezierPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(25f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int padding = 10;
        PointF start = new PointF();
        PointF end = new PointF();
        PointF control = new PointF();
        start.x = padding;
        start.y = h - padding;

        end.x = w - padding;
        end.y = padding;

        control.x = padding;
        control.y = padding;
        float perT = 1f / 50f;
        for (int i = 0; i < 50; i++) {
            float t = perT + i * perT;
            PointF bezierPoint = getBezierPoint(start, end, control, t);
            mPointFs.add(bezierPoint);
        }

        mPath.moveTo(start.x, start.y);
        mPath.quadTo(control.x, control.y, end.x, end.y);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (int i = 0; i < mPointFs.size(); i++) {
            PointF pointF = mPointFs.get(i);
            canvas.drawPoint(pointF.x, pointF.y, mPaint);
        }

        canvas.drawTextOnPath("贝塞尔曲线等差找点示例", mPath, 500, 50, mTextPaint);
    }

    private PointF getBezierPoint(PointF start, PointF end, PointF control, float t) {
        PointF pointF = new PointF();
        pointF.x = (1 - t) * (1 - t) * start.x + 2 * (1 - t) * control.x + t * t * end.x;
        pointF.y = (1 - t) * (1 - t) * start.y + 2 * (1 - t) * control.y + t * t * end.y;
        return pointF;
    }
}
