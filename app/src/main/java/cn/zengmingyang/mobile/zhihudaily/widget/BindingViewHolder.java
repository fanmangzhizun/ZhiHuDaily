package cn.zengmingyang.mobile.zhihudaily.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by simonla on 2016/11/9.
 * 下午8:01
 */

public class BindingViewHolder<T1,T2,T3> extends RecyclerView.ViewHolder {

    private T1 mBinding;

    private T2 mHeaderBinding;

    private T3 mFooterBinding;

    public T1 getBinding() {
        return mBinding;
    }

    public T2 getHeaderBinding() {
        return mHeaderBinding;
    }

    public T3 getFooterBinding() {
        return mFooterBinding;
    }

    public BindingViewHolder(View itemView, T1 binding, T2 headerBinding, T3 footerBinding) {
        super(itemView);
        mBinding = binding;
        mHeaderBinding = headerBinding;
        mFooterBinding = footerBinding;
    }
}
