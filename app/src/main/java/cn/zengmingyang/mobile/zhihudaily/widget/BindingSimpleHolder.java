package cn.zengmingyang.mobile.zhihudaily.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by simonla on 2016/11/9.
 * 下午8:39
 */

public class BindingSimpleHolder<T> extends RecyclerView.ViewHolder {

    private T mBinding;

    public T getBinding() {
        return mBinding;
    }

    public BindingSimpleHolder(View itemView, T binding) {
        super(itemView);
        mBinding = binding;
    }

}
