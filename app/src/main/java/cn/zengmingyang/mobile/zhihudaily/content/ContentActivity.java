package cn.zengmingyang.mobile.zhihudaily.content;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsContent;
import cn.zengmingyang.mobile.zhihudaily.data.bean.NewsExtra;
import cn.zengmingyang.mobile.zhihudaily.news.NewsActivity;

public class ContentActivity extends AppCompatActivity implements ContentContract.View {

    public static final String TAG = "ContentActivity";

    @BindView(R.id.wv_news_content)
    WebView mWvNewsContent;
    @BindView(R.id.toolbar_content)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.fl_content)
    CoordinatorLayout mFlContent;
    @BindView(R.id.sv_content)
    NestedScrollView mSvContent;
    @BindView(R.id.iv_news_content)
    ImageView mIvNewsContent;
    @BindView(R.id.tv_news_content_title)
    TextView mTvNewsContentTitle;
    @BindView(R.id.tv_content_star)
    TextView mTvContentStar;
    @BindView(R.id.tv_content_comment)
    TextView mTvContentCommit;

    private ContentContract.Presenter mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        init();
        int id = (getIntent().getIntExtra(NewsActivity.INTENT_FLAG, 0));
        mPresenter.getContent(id);
        mPresenter.getExtra(id);
    }

    @Override
    public void initToolbar(NewsContent content) {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> {
            if (mWvNewsContent.canGoBack()) {
                mWvNewsContent.goBack();
                return;
            }
            ContentActivity.this.finish();
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void showExtra(NewsExtra newsExtra) {
        mTvContentCommit.setText(String.valueOf(newsExtra.getComments()));
        mTvContentStar.setText(String.valueOf(newsExtra.getPopularity()));
    }

    private void init() {
        new ContentPresenter(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWvNewsContent.canGoBack()) {
            mWvNewsContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setPresenter(ContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        error.printStackTrace();
    }

    @Override
    public void showContent(NewsContent newsContent) {
        Glide.with(this).load(newsContent.getImage()).centerCrop().into(mIvNewsContent);
        mTvNewsContentTitle.setText(newsContent.getTitle());
        mWvNewsContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(String.valueOf(request.getUrl()));
                }
                return true;
            }
        });
        mWvNewsContent.loadDataWithBaseURL("file:///android_asset/",
                newsContent.getBody(), "text/html", "utf-8", null);
    }
}
