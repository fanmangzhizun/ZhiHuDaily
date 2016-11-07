package cn.zengmingyang.mobile.zhihudaily.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.model.StartImageWrapper;
import cn.zengmingyang.mobile.zhihudaily.news.NewsActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    @BindView(R.id.iv_start_image)
    ImageView mIvStartImage;
    @BindView(R.id.tv_splash)
    TextView mTvSplash;
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        new SplashPresenter(this);
        mPresenter.getImage("1080*1920");
    }

    private void startActivity() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, NewsActivity.class));
            SplashActivity.this.finish();
        }, 4000);
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void success(StartImageWrapper startImageWrapper) {
        Glide.with(this).load(startImageWrapper.getImg()).centerCrop().into(mIvStartImage);
        mTvSplash.setText(startImageWrapper.getText());
        startActivity();
    }

    @Override
    public void fail(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        startActivity();
    }
}
