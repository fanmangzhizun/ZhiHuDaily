package cn.zengmingyang.mobile.zhihudaily.content;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.zengmingyang.mobile.zhihudaily.R;

/**
 * Created by simonla on 2016/11/7.
 * 下午3:45
 */

public class ToolbarBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private int mOffset = 0;
    private boolean mIsShow = true;

    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("toolbarBehavior", "ToolbarBehavior init");
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int scrollDistance = 50;
        if (mOffset > scrollDistance && mIsShow) {
            child.animate().alpha(255).setInterpolator(new FastOutSlowInInterpolator()).start();
            mIsShow = false;
            mOffset = 0;
        } else if (mOffset < -scrollDistance && !mIsShow) {
            child.animate().alpha(0).setInterpolator(new FastOutSlowInInterpolator()).start();
        }
        if ((mIsShow && dyConsumed > 0) || (!mIsShow && dyConsumed < 0)) {
            mOffset += dyConsumed;
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.toolbar_content;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        return true;
    }
}
