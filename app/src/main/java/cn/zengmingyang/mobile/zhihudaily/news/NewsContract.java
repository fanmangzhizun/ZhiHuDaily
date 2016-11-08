package cn.zengmingyang.mobile.zhihudaily.news;

import java.util.List;

import cn.zengmingyang.mobile.zhihudaily.BasePresenter;
import cn.zengmingyang.mobile.zhihudaily.BaseView;
import cn.zengmingyang.mobile.zhihudaily.data.bean.News;

/**
 * Created by simonla on 2016/11/5.
 * 下午4:51
 */

interface NewsContract {
    interface View extends BaseView<Presenter> {
        void showList(List<News.StoriesBean> list, List<News.TopStoriesBean> topList);

        void showError(Throwable error);

        void showAddMore(List<News.StoriesBean> storiesBeen);
    }

    interface Presenter extends BasePresenter {
        void addMore(int page);

        void refresh();
    }
}
