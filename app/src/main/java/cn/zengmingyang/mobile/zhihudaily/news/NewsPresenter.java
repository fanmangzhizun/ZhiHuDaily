package cn.zengmingyang.mobile.zhihudaily.news;

import java.util.ArrayList;
import java.util.List;

import cn.zengmingyang.mobile.zhihudaily.data.bean.News;
import cn.zengmingyang.mobile.zhihudaily.data.network.RequestManger;
import cn.zengmingyang.mobile.zhihudaily.utils.TimeUtils;
import rx.Subscriber;

/**
 * Created by simonla on 2016/11/5.
 * 下午4:50
 */

class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View mView;

    public static final String TAG = "NewsPresenter";

    NewsPresenter(NewsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        RequestManger.getInstance().getLatestNews(new Subscriber<News>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }

            @Override
            public void onNext(News news) {
                mView.showList(news.getStories(),news.getTop_stories());
            }
        });
    }

    @Override
    public void addMore(int page) {
        List<News.StoriesBean> list = new ArrayList<>();
        RequestManger.getInstance().getBeforeNews(new Subscriber<News.StoriesBean>() {
            @Override
            public void onCompleted() {
                mView.showAddMore(list);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }

            @Override
            public void onNext(News.StoriesBean storiesBean) {
                list.add(storiesBean);
            }
        }, TimeUtils.pageAdapter(page));
    }

    @Override
    public void refresh() {
       this.start();
    }


}
