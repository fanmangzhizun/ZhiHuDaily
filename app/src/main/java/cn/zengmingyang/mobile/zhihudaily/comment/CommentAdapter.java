package cn.zengmingyang.mobile.zhihudaily.comment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsComment;
import cn.zengmingyang.mobile.zhihudaily.databinding.ItemCommentBinding;
import cn.zengmingyang.mobile.zhihudaily.utils.TimeUtils;
import cn.zengmingyang.mobile.zhihudaily.widget.BindingSimpleHolder;

/**
 * Created by simonla on 2016/11/8.
 * 下午7:57
 */


class CommentAdapter extends RecyclerView.Adapter<BindingSimpleHolder<ItemCommentBinding>> {

    private List<NewsComment.CommentsBean> mCommentsBeen;
    private Context mContext;

    CommentAdapter(List<NewsComment.CommentsBean> commentsBeen, Context context) {
        mCommentsBeen = commentsBeen;
        mContext = context;
    }

    @Override
    public BindingSimpleHolder<ItemCommentBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCommentBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_comment, parent, false);
        return new BindingSimpleHolder<>(binding.getRoot(),binding);
    }

    @Override
    public void onBindViewHolder(BindingSimpleHolder<ItemCommentBinding> holder, int position) {
        NewsComment.CommentsBean commentsBean = mCommentsBeen.get(position);
        Glide.with(mContext).load(commentsBean
                .getAvatar()).fitCenter().into(holder.getBinding().ivComment);
        holder.getBinding().setComment(commentsBean);
        holder.getBinding().tvData.setText(TimeUtils.timeStampToStr(commentsBean.getTime()));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCommentsBeen.size();
    }
}
