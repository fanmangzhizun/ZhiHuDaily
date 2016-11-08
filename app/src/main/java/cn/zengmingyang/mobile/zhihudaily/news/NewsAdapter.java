package cn.zengmingyang.mobile.zhihudaily.news;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.List;

import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.News;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by simonla on 2016/11/5.
 * 下午8:09
 */

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<News.StoriesBean> mStoriesBeen;
    private Context mContext;
    private List<News.TopStoriesBean> mTopStoriesBeen;

    private static final int IS_HEADER = 2;
    private static final int IS_FOOTER = 3;
    private static final int IS_NORMAL = 1;

    private final PublishSubject<Integer> onClickSubject = PublishSubject.create();


    NewsAdapter(List<News.StoriesBean> storiesBeen, Context context, List<News.TopStoriesBean> topStoriesBeen) {
        mStoriesBeen = storiesBeen;
        mContext = context;
        mTopStoriesBeen = topStoriesBeen;
    }

    void addMore(List<News.StoriesBean> storiesBeen) {
        mStoriesBeen.addAll(storiesBeen);
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IS_HEADER) {
            return new MyViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.banner_header_news_list, parent, false));
        } else if (viewType == IS_FOOTER) {
            return new MyViewHolder(LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.item_footer_news_list, parent, false));
        } else if (viewType == IS_NORMAL) {
            return new MyViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_news, parent, false));
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position != 0 && position != mStoriesBeen.size() + 1 && holder.getItemViewType() == IS_NORMAL) {
            position -= 1;
            News.StoriesBean storiesBean = mStoriesBeen.get(position);
            holder.mTvNewsTitle.setText(storiesBean.getTitle());
            Glide.with(mContext).load(storiesBean.getImages().get(0)).into(holder.mIvNewsImage);
            holder.mCardView.setOnClickListener(view -> onClickSubject.onNext(storiesBean.getId()));
            holder.mCardView.setCardElevation(1);
        }
        if (position == 0 && holder.getItemViewType() == IS_HEADER) {
            holder.mNewsListBanner.setAdapter(new BannerAdapter(holder.mNewsListBanner, mTopStoriesBeen));
            holder.mNewsListBanner.setHintView(new ColorPointHintView(mContext, Color.WHITE, Color.GRAY));
            holder.mNewsListBanner.setOnItemClickListener(position1 ->
                    onClickSubject.onNext(mTopStoriesBeen.get(position1).getId()));
        }
        if (position == mStoriesBeen.size() + 1 && holder.getItemViewType() == IS_FOOTER) {
            holder.mNewsListFooter.setText("加载中...");
        }
    }

    Observable<Integer> getPositionClicks(){
        return onClickSubject.asObservable();
    }

    @Override
    public int getItemCount() {
        return mStoriesBeen.size() + 2;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvNewsTitle;
        ImageView mIvNewsImage;
        RollPagerView mNewsListBanner;
        TextView mNewsListFooter;
        CardView mCardView;

        MyViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item_girl);
            mTvNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
            mIvNewsImage = (ImageView) itemView.findViewById(R.id.iv_news_image);
            mNewsListBanner = (RollPagerView) itemView.findViewById(R.id.news_list_banner);
            mNewsListFooter = (TextView) itemView.findViewById(R.id.news_list_footer);
        }
    }

    private class BannerAdapter extends LoopPagerAdapter {

        private List<News.TopStoriesBean> mTopStoriesBeen;

        BannerAdapter(RollPagerView viewPager, List<News.TopStoriesBean> topStoriesBeen) {
            super(viewPager);
            mTopStoriesBeen = topStoriesBeen;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            FrameLayout frameLayout= (FrameLayout)
                    LayoutInflater.from(container.getContext()).
                            inflate(R.layout.item_banner, container,false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.tv_item_banner);
            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.iv_item_banner);
            textView.setText(mTopStoriesBeen.get(position).getTitle());
            Glide.with(container.getContext()).load(mTopStoriesBeen.get(position).getImage())
                    .centerCrop().into(imageView);
            return frameLayout;
        }

        @Override
        public int getRealCount() {
            return mTopStoriesBeen.size();
        }
    }
}
