package cn.zengmingyang.mobile.zhihudaily.content;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by simonla on 2016/11/8.
 * 下午12:35
 */

abstract class ContentBehavior extends CoordinatorLayout.Behavior<View> {
    static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int mOffset = 0;
    private boolean mIsShow = true;

    ContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int fabBottomMargin = child.getBottom() + child.getHeight();
        int scrollDistance = 10;
        if (mOffset > scrollDistance && mIsShow) {
            hide(child);
            mIsShow = false;
            mOffset = 0;
        } else if (mOffset < -scrollDistance && !mIsShow) {
            show(child);
            mIsShow = true;
            mOffset = 0;
        }
        if ((mIsShow && dyConsumed > 0) || (!mIsShow && dyConsumed < 0)) {
            mOffset += dyConsumed;
        }
    }


    abstract void hide(final View view);

    abstract void show(final View view);
}
