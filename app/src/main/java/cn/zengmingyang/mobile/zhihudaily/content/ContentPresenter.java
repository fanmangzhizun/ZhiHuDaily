package cn.zengmingyang.mobile.zhihudaily.content;

import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsContent;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsExtra;
import cn.zengmingyang.mobile.zhihudaily.data.network.RequestManger;
import rx.Subscriber;

/**
 * Created by simonla on 2016/11/7.
 * 下午3:13
 */

class ContentPresenter implements ContentContract.Presenter {

    private ContentContract.View mView;

    ContentPresenter(ContentContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getContent(int id) {
        RequestManger.getInstance().getNewsContent(new Subscriber<NewsContent>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }

            @Override
            public void onNext(NewsContent newsContent) {
                mView.showContent(newsContent);
                mView.initToolbar(newsContent);
            }
        }, id);
    }

    @Override
    public void getExtra(int id) {
        RequestManger.getInstance().getNewsExtra(new Subscriber<NewsExtra>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }

            @Override
            public void onNext(NewsExtra newsExtra) {
                mView.showExtra(newsExtra);
            }
        }, id);
    }
}
