package cn.zengmingyang.mobile.zhihudaily.splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zengmingyang.mobile.zhihudaily.R;
import cn.zengmingyang.mobile.zhihudaily.data.bean.StartImageWrapper;
import cn.zengmingyang.mobile.zhihudaily.news.NewsActivity;
import cn.zengmingyang.mobile.zhihudaily.utils.FastBlurUtil;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    @BindView(R.id.iv_start_image)
    ImageView mIvStartImage;
    @BindView(R.id.tv_splash)
    TextView mTvSplash;
    @BindView(R.id.activity_splash)
    LinearLayout mActivitySplash;
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
        }, 6000);
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void success(StartImageWrapper startImageWrapper) {

        Glide.with(this).load(startImageWrapper.getImg()).asBitmap()
                .into(new ImageViewTarget<Bitmap>(mIvStartImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        mIvStartImage.setImageBitmap(resource);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            long time1 = System.currentTimeMillis();
                            mActivitySplash.setBackground(new BitmapDrawable(getResources(),
                                    FastBlurUtil.doBlur(resource, 60, false)));
                            long time2 = System.currentTimeMillis();
                            Log.d("time", "setResource: " + (time2 - time1));
                        }
                    }
                });
        mTvSplash.setText(startImageWrapper.getText());
        startActivity();
    }

    @Override
    public void fail(Throwable error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        startActivity();
    }
}
