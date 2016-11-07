package cn.zengmingyang.mobile.zhihudaily.news;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by simonla on 2016/11/6.
 * 上午10:52
 */

abstract class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private boolean mIsShow = true;
    private int mScrollOffset = 0;
    private int previousTotal = 0;
    private boolean loading = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int scrollDistance = 20, currentPage = 0;
        if (mScrollOffset > scrollDistance && mIsShow) {
            onHide();
            mIsShow = false;
            mScrollOffset = 0;
        } else if (mScrollOffset < -scrollDistance && !mIsShow) {
            onShow();
            mIsShow = true;
            mScrollOffset = 0;
        }
        if ((mIsShow && dy > 0) || (!mIsShow && dy < 0)) {
            mScrollOffset += dy;
        }
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);

    public abstract void onShow();

    public abstract void onHide();

}
