package cn.zengmingyang.mobile.zhihudaily.comment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsComment;
import cn.zengmingyang.mobile.zhihudaily.databinding.ItemCommentBinding;
import cn.zengmingyang.mobile.zhihudaily.utils.TimeUtils;

/**
 * Created by simonla on 2016/11/8.
 * 下午7:57
 */

class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<NewsComment.CommentsBean> mCommentsBeen;
    private Context mContext;

    CommentAdapter(List<NewsComment.CommentsBean> commentsBeen, Context context) {
        mCommentsBeen = commentsBeen;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCommentBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_comment, parent, false);
        MyViewHolder holder = new MyViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsComment.CommentsBean commentsBean = mCommentsBeen.get(position);
        Glide.with(mContext).load(commentsBean
                .getAvatar()).fitCenter().into(holder.mItemCommentBinding.ivComment);
        holder.getItemCommentBinding().setComment(commentsBean);
        holder.getItemCommentBinding().executePendingBindings();
        holder.getItemCommentBinding().tvData.setText(TimeUtils.timeStampToStr(commentsBean.getTime()));
    }

    @Override
    public int getItemCount() {
        return mCommentsBeen.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemCommentBinding mItemCommentBinding;

        MyViewHolder(View itemView) {
            super(itemView);
        }

        ItemCommentBinding getItemCommentBinding() {
            return mItemCommentBinding;
        }

        void setBinding(ItemCommentBinding itemCommentBinding) {
            mItemCommentBinding = itemCommentBinding;
        }
    }
}
