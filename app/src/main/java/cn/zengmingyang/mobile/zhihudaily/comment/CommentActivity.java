package cn.zengmingyang.mobile.zhihudaily.comment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsComment;
import cn.zengmingyang.mobile.zhihudaily.data.network.RequestManger;
import cn.zengmingyang.mobile.zhihudaily.news.NewsActivity;
import rx.Subscriber;

@RequiresApi(api = Build.VERSION_CODES.M)
public class CommentActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar_comment)
    Toolbar mToolbarComment;
    @BindView(R.id.rv_comments)
    RecyclerView mRvComments;
    @BindView(R.id.activity_comment)
    CoordinatorLayout mActivityComment;
    @BindView(R.id.srl_comment)
    SwipeRefreshLayout mSrlComment;

    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        init();
        mId = getIntent().getIntExtra(NewsActivity.INTENT_FLAG, 0);
        mRvComments.setLayoutManager(new LinearLayoutManager(this));
        mSrlComment.setRefreshing(true);
        getData();
    }

    private void init() {
        setSupportActionBar(mToolbarComment);
        ActionBar actionBar = getSupportActionBar();
        mToolbarComment.setTitle("评论");
        mToolbarComment.setNavigationOnClickListener(view -> this.finish());
        if (actionBar != null) {
            actionBar.setTitle("评论");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        mSrlComment.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private void getData() {
        RequestManger.getInstance().getNewsComments(new Subscriber<NewsComment>() {
            ArrayList<NewsComment.CommentsBean> mCommentsBeen = new ArrayList<>();
            @Override
            public void onCompleted() {
                CommentAdapter commentAdapter = new CommentAdapter
                        (mCommentsBeen, CommentActivity.this);
                mRvComments.setAdapter(commentAdapter);
                mSrlComment.setRefreshing(false);
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                mSrlComment.setRefreshing(false);
                Toast.makeText(CommentActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(NewsComment newsComment) {
                mCommentsBeen.addAll(newsComment.getComments());
            }
        }, mId);
    }
}
