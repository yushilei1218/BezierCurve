package com.yushilei.beziercurve.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yushilei.beziercurve.R;

import java.util.Random;

/**
 * @author by  yushilei.
 * @time 2016/9/13 -10:52.
 * @Desc
 */
public class ParabolaView extends ViewGroup implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    private Random mRandom;
    private ImageView shop;
    private PointF mControl;
    private PointF mPointF = new PointF();


    public ParabolaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRandom = new Random();
    }

    int padding = 10;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int measuredWidth = child.getMeasuredWidth();
            if (child.getId() == R.id.id_shop) {
                child.layout(padding, getMeasuredHeight() - padding - height,
                        padding + measuredWidth, getMeasuredHeight() - padding);
            } else {
                int randomX = getRandomX(measuredWidth);
                int randomY = getRandomY(height);
                child.layout(randomX - measuredWidth / 2, randomY - height / 2, randomX + measuredWidth / 2, randomY + height / 2);
                child.setOnClickListener(this);
            }
        }
    }

    private int getRandomX(int mw) {
        return mRandom.nextInt(getWidth() - 2 * padding - mw / 2) + padding + mw / 2;
    }

    private int getRandomY(int mh) {
        return mRandom.nextInt(getHeight() - 2 * padding - mh / 2) + padding + mh / 2;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        shop = (ImageView) findViewById(R.id.id_shop);
    }

    String TAG = "Parabola";

    @Override
    public void onClick(View v) {
        PointF start = new PointF(v.getX(), v.getY());
        PointF end = new PointF(shop.getX(), shop.getY());
        Log.d(TAG, "start X=" + start.x + ";y=" + start.y + ";end x=" + end.x + ";end y=" + end.y);
        mControl = new PointF(shop.getX() - shop.getWidth() / 2, 0);

        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(v, "rotation", 0, 360 * 4);
        rotateAnim.setDuration(1000);

        ObjectAnimator animator = ObjectAnimator.ofObject(((ImgView) v), "mPointF", new PointEvaluator(), start, end);
        animator.addUpdateListener(this);
        animator.addListener(this);
        animator.setDuration(1000);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator, rotateAnim);
        set.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        PointF p = (PointF) animation.getAnimatedValue();
        ((ImgView) ((ObjectAnimator) animation).getTarget()).setPointF(p);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        View target = (View) ((ObjectAnimator) animation).getTarget();
//        removeView(target);
        target.setVisibility(INVISIBLE);
        int height = shop.getHeight();
        int width = shop.getWidth();
        shop.setPivotX(getX() + width / 2);
        shop.setPivotY(getY() + height / 2);
        ObjectAnimator a = ObjectAnimator.ofFloat(shop, "scaleX", 1f, 1.2f);
        a.setRepeatMode(ValueAnimator.REVERSE);
        a.setRepeatCount(1);
        ObjectAnimator b = ObjectAnimator.ofFloat(shop, "scaleY", 1f, 1.2f);
        b.setRepeatMode(ValueAnimator.REVERSE);
        b.setRepeatCount(1);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(200);
        set.playTogether(a, b);
        set.start();


    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }


    public class PointEvaluator implements TypeEvaluator<PointF> {
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            Log.d(TAG, "fraction=" + fraction + ";startValue x=" + startValue.x + ";y=" + startValue.y + ";endValue x=" + endValue.x + ";y=" + endValue.y);
            return getBezierPoint(startValue, endValue, mControl, fraction);
        }
    }

    private PointF getBezierPoint(PointF start, PointF end, PointF control, float t) {
        mPointF.x = (1 - t) * (1 - t) * start.x + 2 * (1 - t) * control.x + t * t * end.x;
        mPointF.y = (1 - t) * (1 - t) * start.y + 2 * (1 - t) * control.y + t * t * end.y;
        return mPointF;
    }
}
