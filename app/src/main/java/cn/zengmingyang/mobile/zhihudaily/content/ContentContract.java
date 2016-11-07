package cn.zengmingyang.mobile.zhihudaily.content;

import cn.zengmingyang.mobile.zhihudaily.BasePresenter;
import cn.zengmingyang.mobile.zhihudaily.BaseView;
import cn.zengmingyang.mobile.zhihudaily.data.model.NewsContent;

/**
 * Created by simonla on 2016/11/7.
 * 下午3:12
 */

interface ContentContract {
    interface View extends BaseView<Presenter> {
        void showError(Throwable error);

        void showContent(NewsContent newsContent);

        void initToolbar(NewsContent newsContent);
    }

    interface Presenter extends BasePresenter {
        void getContent(int id);
    }
}
