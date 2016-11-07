package cn.zengmingyang.mobile.zhihudaily.data.network;


import cn.zengmingyang.mobile.zhihudaily.data.model.BeforeNews;
import cn.zengmingyang.mobile.zhihudaily.data.model.News;
import cn.zengmingyang.mobile.zhihudaily.data.model.NewsContent;
import cn.zengmingyang.mobile.zhihudaily.data.model.StartImageWrapper;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by simonla on 2016/11/5.
 * 下午4:08
 */

interface ApiService {

    @GET("start-image/{wh}")
    Observable<StartImageWrapper> getStartImage(@Path("wh") String wh);

    @GET("news/latest")
    Observable<News> getLatest();

    @GET("news/before/{date}")
    Observable<BeforeNews> getBeforeNews(@Path("date") int date);

    @GET("news/{id}")
    Observable<NewsContent> getNewsContent(@Path("id") int id);

}
