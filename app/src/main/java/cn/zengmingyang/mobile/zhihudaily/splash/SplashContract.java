package cn.zengmingyang.mobile.zhihudaily.splash;

import cn.zengmingyang.mobile.zhihudaily.BasePresenter;
import cn.zengmingyang.mobile.zhihudaily.BaseView;
import cn.zengmingyang.mobile.zhihudaily.data.model.StartImageWrapper;

/**
 * Created by simonla on 2016/11/5.
 * 下午3:48
 */

class SplashContract {
    interface View extends BaseView<Presenter> {
        void success(StartImageWrapper startImageWrapper);

        void fail(Throwable error);
    }

    interface Presenter extends BasePresenter {
        void getImage(String wh);
    }
}
