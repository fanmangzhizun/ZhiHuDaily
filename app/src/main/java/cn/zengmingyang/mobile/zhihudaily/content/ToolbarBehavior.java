package cn.zengmingyang.mobile.zhihudaily.content;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by simonla on 2016/11/7.
 * 下午3:45
 */

public class ToolbarBehavior extends ContentBehavior {

    ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    void hide(View view) {
        view.animate().translationY(-view.getHeight()).setInterpolator(INTERPOLATOR).start();
        //view.animate().alpha(0).setInterpolator(INTERPOLATOR).start();
    }

    @Override
    void show(View view) {
        view.animate().translationY(0).setInterpolator(INTERPOLATOR).start();
        //view.animate().alpha(255).setInterpolator(INTERPOLATOR).start();
    }
}
