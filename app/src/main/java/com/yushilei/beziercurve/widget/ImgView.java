package com.yushilei.beziercurve.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author by  yushilei.
 * @time 2016/9/13 -12:43.
 * @Desc
 */
public class ImgView extends ImageView {
    private PointF mPointF;

    public ImgView(Context context) {
        super(context);
    }

    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPointF(PointF pointF) {
        mPointF = pointF;
        setTranslationX(pointF.x);
        setTranslationY(pointF.y);
        setX(pointF.x);
        setY(pointF.y);
    }
}
