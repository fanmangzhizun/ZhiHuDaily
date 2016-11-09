package cn.zengmingyang.mobile.zhihudaily.news;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.List;

import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.News;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsExtra;
import cn.zengmingyang.mobile.zhihudaily.data.network.RequestManger;
import cn.zengmingyang.mobile.zhihudaily.databinding.FooterNewsListBinding;
import cn.zengmingyang.mobile.zhihudaily.databinding.HeaderBannerBinding;
import cn.zengmingyang.mobile.zhihudaily.databinding.ItemBannerBinding;
import cn.zengmingyang.mobile.zhihudaily.databinding.ItemNewsBinding;
import cn.zengmingyang.mobile.zhihudaily.widget.BindingViewHolder;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by simonla on 2016/11/5.
 * 下午8:09
 */

class NewsAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemNewsBinding, HeaderBannerBinding,
        FooterNewsListBinding>> {

    private List<News.StoriesBean> mStoriesBeen;
    private Context mContext;
    private List<News.TopStoriesBean> mTopStoriesBeen;

    private static final int IS_HEADER = 2;
    private static final int IS_FOOTER = 3;
    private static final int IS_NORMAL = 1;

    private final PublishSubject<Integer> onClickSubject = PublishSubject.create();

    NewsAdapter(List<News.StoriesBean> storiesBeen, Context context,
                List<News.TopStoriesBean> topStoriesBeen) {
        mStoriesBeen = storiesBeen;
        mContext = context;
        mTopStoriesBeen = topStoriesBeen;
    }

    void addMore(List<News.StoriesBean> storiesBeen) {
        mStoriesBeen.addAll(storiesBeen);
        this.notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder<ItemNewsBinding, HeaderBannerBinding, FooterNewsListBinding>
    onCreateViewHolder(ViewGroup parent, int viewType) {
        HeaderBannerBinding bindingHeader = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.header_banner, parent, false);

        FooterNewsListBinding bindingFooter = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.footer_news_list, parent, false);

        ItemNewsBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_news, parent, false);

        if (viewType == IS_HEADER) {
            return new BindingViewHolder<>(
                    bindingHeader.getRoot(), binding, bindingHeader, bindingFooter
            );

        } else if (viewType == IS_FOOTER) {
            return new BindingViewHolder<>(
                    bindingFooter.getRoot(), binding, bindingHeader, bindingFooter
            );
        } else if (viewType == IS_NORMAL) {
            return new BindingViewHolder<>(
                    binding.getRoot(), binding, bindingHeader, bindingFooter
            );
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else if (position == mStoriesBeen.size() + 1) {
            return IS_FOOTER;
        } else {
            return IS_NORMAL;
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemNewsBinding, HeaderBannerBinding,
            FooterNewsListBinding> holder, int position) {
        if (position != 0 && position != mStoriesBeen.size() + 1 && holder.getItemViewType() ==
                IS_NORMAL) {
            position -= 1;
            News.StoriesBean storiesBean = mStoriesBeen.get(position);
            Glide.with(mContext)
                    .load(storiesBean.getImages().get(0))
                    .into(holder.getBinding().ivNewsImage);
            holder.getBinding().cvItemNews.
                    setOnClickListener(view -> onClickSubject.onNext(storiesBean.getId()));
            holder.getBinding().cvItemNews.setCardElevation(1);
            holder.getBinding().setNews(storiesBean);
            holder.getBinding().executePendingBindings();
            RequestManger.getInstance().getNewsExtra(new Subscriber<NewsExtra>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewsExtra newsExtra) {
                    holder.getBinding().setExtra(newsExtra);
                }
            },storiesBean.getId());
        }
        if (position == 0 && holder.getItemViewType() == IS_HEADER) {
            holder.getHeaderBinding().newsListBanner.setAdapter(new BannerAdapter(holder
                    .getHeaderBinding().newsListBanner,
                    mTopStoriesBeen));
            holder.getHeaderBinding().newsListBanner
                    .setHintView(new ColorPointHintView(mContext, Color.WHITE, Color.GRAY));
            holder.getHeaderBinding().newsListBanner.setOnItemClickListener(position1 ->
                    onClickSubject.onNext(mTopStoriesBeen.get(position1).getId()));
        }
    }

    Observable<Integer> getPositionClicks() {
        return onClickSubject.asObservable();
    }

    @Override
    public int getItemCount() {
        return mStoriesBeen.size() + 2;
    }

    private class BannerAdapter extends LoopPagerAdapter {

        private List<News.TopStoriesBean> mTopStoriesBeen;

        BannerAdapter(RollPagerView viewPager, List<News.TopStoriesBean> topStoriesBeen) {
            super(viewPager);
            mTopStoriesBeen = topStoriesBeen;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ItemBannerBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(container.getContext()), R.layout.item_banner, container, false);
            Glide.with(container.getContext()).load(mTopStoriesBeen.get(position).getImage())
                    .centerCrop().into(binding.ivItemBanner);
            binding.setTop(mTopStoriesBeen.get(position));
            return binding.getRoot();
        }

        @Override
        public int getRealCount() {
            return mTopStoriesBeen.size();
        }
    }
}
