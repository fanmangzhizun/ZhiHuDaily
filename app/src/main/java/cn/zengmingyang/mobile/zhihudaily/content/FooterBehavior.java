package cn.zengmingyang.mobile.zhihudaily.content;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by simonla on 2016/11/8.
 * 下午12:23
 */

public class FooterBehavior extends ContentBehavior {

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    void hide(View view) {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        view.animate().translationY(view.getHeight()+lp.bottomMargin).setInterpolator(INTERPOLATOR).start();
    }

    @Override
    void show(View view) {
        view.animate().translationY(0).setInterpolator(INTERPOLATOR).start();
    }
}
