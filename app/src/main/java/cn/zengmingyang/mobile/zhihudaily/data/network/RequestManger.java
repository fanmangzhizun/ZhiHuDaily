package cn.zengmingyang.mobile.zhihudaily.data.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.zengmingyang.mobile.zhihudaily.data.model.BeforeNews;
import cn.zengmingyang.mobile.zhihudaily.data.model.News;
import cn.zengmingyang.mobile.zhihudaily.data.model.NewsContent;
import cn.zengmingyang.mobile.zhihudaily.data.model.StartImageWrapper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by simonla on 2016/11/5.
 * 下午3:57
 */

public class RequestManger {

    private ApiService mApiService;
    private static final String TAG = "RequestManger";

    private RequestManger() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(3, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    private static class SingletonHolder {
        static final RequestManger INSTANCE = new RequestManger();
    }

    public static RequestManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getStartImage(Subscriber<StartImageWrapper> subscriber, String wh) {
        mApiService
                .getStartImage(wh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getLatestNews(Subscriber<News> subscriber) {
        mApiService
                .getLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getBeforeNews(Subscriber<News.StoriesBean> newsSubscriber, int date) {
        mApiService
                .getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BeforeNews, Observable<News.StoriesBean>>() {
                    @Override
                    public Observable<News.StoriesBean> call(BeforeNews beforeNews) {
                        List<News.StoriesBean> list = new ArrayList<>();
                        for (BeforeNews.StoriesBean storiesBean : beforeNews.getStories()) {
                            News.StoriesBean storiesBean1 = new News.StoriesBean();
                            storiesBean1.setId(storiesBean.getId());
                            storiesBean1.setTitle(storiesBean.getTitle());
                            storiesBean1.setImages(storiesBean.getImages());
                            storiesBean1.setGa_prefix(storiesBean.getGa_prefix());
                            storiesBean1.setType(storiesBean.getType());
                            list.add(storiesBean1);
                        }
                        return Observable.from(list);
                    }
                })
                .subscribe(newsSubscriber);
    }

    public void getNewsContent(Subscriber<NewsContent> contentSubscriber, int id) {
        mApiService
                .getNewsContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(newsContent -> {
                    int begin = newsContent.getBody().indexOf("<div class=\"img-place-holder") ;
                    int last = newsContent.getBody().indexOf("<div class=\"question\">");
                    String first = newsContent.getBody().substring(0, begin);
                    String second = newsContent.getBody().substring(last,
                            newsContent.getBody().length() - last);
                    newsContent.setBody(first + second);
                    newsContent.setBody("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n" +
                            "\n" + newsContent.getBody());

                    return newsContent;
                })
                .subscribe(contentSubscriber);
    }
}
