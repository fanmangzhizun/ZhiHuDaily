package cn.zengmingyang.mobile.zhihudaily.data.network;


import cn.zengmingyang.mobile.zhihudaily.data.bean.BeforeNews;
import cn.zengmingyang.mobile.zhihudaily.data.bean.News;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsComment;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsContent;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsExtra;
import cn.zengmingyang.mobile.zhihudaily.data.bean.StartImageWrapper;
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

    @GET("story-extra/{id}")
    Observable<NewsExtra> getNewsExtra(@Path("id") int id);

    @GET("story/{id}/long-comments")
    Observable<NewsComment> getNewsLongComments(@Path("id") int id);

    @GET("story/{id}/short-comments")
    Observable<NewsComment> getNewsShortComments(@Path("id") int id);

}
