package cn.zengmingyang.mobile.zhihudaily.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.content.ContentActivity;
import cn.zengmingyang.mobile.zhihudaily.data.model.News;

import static cn.zengmingyang.mobile.zhihudaily.R.menu.main;

public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewsContract.View
        , SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "NewsActivity";

    public static final String INTENT_FLAG = "news_activity_content";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;
    @BindView(R.id.srl_news_list)
    SwipeRefreshLayout mSrlNewsList;
    @BindView(R.id.app_bar_news_list)
    AppBarLayout mAppBarNewsList;

    private NewsContract.Presenter mPresenter;
    private NewsAdapter mNewsAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("知乎日报");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (mDrawerLayout != null) {
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            mNavView.setNavigationItemSelectedListener(this);
        }
        new NewsPresenter(this);
        mSrlNewsList.setRefreshing(true);
        mRvNewsList.setLayoutManager(new LinearLayoutManager(this));
        mSrlNewsList.setOnRefreshListener(this);
        mRvNewsList.addOnScrollListener(new RecyclerScrollListener() {
            @Override
            public void onLoadMore(int page) {
                mPresenter.addMore(page);
            }

            @Override
            public void onShow() {

            }

            @Override
            public void onHide() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void showList(List<News.StoriesBean> list, List<News.TopStoriesBean> topStoriesBeanList) {
        mNewsAdapter = new NewsAdapter(list, this, topStoriesBeanList);
        mRvNewsList.setAdapter(mNewsAdapter);
        mSrlNewsList.setRefreshing(false);
        mNewsAdapter.getPositionClicks().subscribe(integer -> {
            Intent intent = new Intent(this, ContentActivity.class);
            intent.putExtra(INTENT_FLAG, integer);
            startActivity(intent);
        });
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        mSrlNewsList.setRefreshing(false);
    }

    @Override
    public void showAddMore(List<News.StoriesBean> storiesBeen) {
        mNewsAdapter.addMore(storiesBeen);
    }

}
