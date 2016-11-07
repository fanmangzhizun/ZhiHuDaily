package cn.zengmingyang.mobile.zhihudaily.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.zengmingyang.mobile.zhihudaily.R;

/**
 * Created by simonla on 2016/11/5.
 * 下午10:17
 */

public class BannerImageView extends ImageView {
    public static final String TAG = "BannerImageView";

    private Paint mPaint;
    private String mText;
    private BlurMaskFilter mBlurMaskFilter;
    private Bitmap mBitmap;
    //public static final int BLUR = 3;

    public void setText(String text) {
        mText = text;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public BannerImageView(Context context) {
        super(context);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    public BannerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    public BannerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BannerImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
        //mBlurMaskFilter = new BlurMaskFilter(BLUR, BlurMaskFilter.Blur.SOLID);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.reset();
        mPaint.setColor(getResources().getColor(R.color.material_color_blue_gray_800));
        mPaint.setAlpha(0);
        mPaint.setAntiAlias(true);
        canvas.drawRect(0, (80 * getHeight()) / 100, getWidth(), getHeight(), mPaint);
        mPaint.reset();
        mPaint.setColor(getResources().getColor(R.color.material_color_white));
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(58);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        //mPaint.setTextAlign(Paint.Align.CENTER);
        //mPaint.setMaskFilter(mBlurMaskFilter);
        if (mText != null) {
            if (mText.length() > 15) {
                canvas.drawText(mText.substring(0, 15), 15, getHeight() * 14 / 20, mPaint);
                canvas.drawText(mText.substring(15, mText.length() - 15), mText.length() - 15,
                        getHeight() * 18 / 20, mPaint);
            } else {
                canvas.drawText(mText, mText.length(), getHeight() * 17 / 20, mPaint);
            }
        }
        mPaint.reset();
    }
}
