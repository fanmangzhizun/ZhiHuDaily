package cn.zengmingyang.mobile.zhihudaily.splash;

import cn.zengmingyang.mobile.zhihudaily.data.bean.StartImageWrapper;
import cn.zengmingyang.mobile.zhihudaily.data.network.RequestManger;
import rx.Subscriber;

/**
 * Created by simonla on 2016/11/5.
 * 下午3:48
 */

class SplashPresenter implements SplashContract.Presenter {

    public static final String TAG = "SplashPresenter";
    
    private SplashContract.View mView;

    SplashPresenter(SplashContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void getImage(String wh) {
        RequestManger.getInstance().getStartImage(new Subscriber<StartImageWrapper>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.fail(e);
            }

            @Override
            public void onNext(StartImageWrapper startImageWrapper) {
                mView.success(startImageWrapper);
            }
        },wh);
    }
}
