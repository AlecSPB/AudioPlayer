package com.tc.audioplayer.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;
import com.tc.audioplayer.R;
import com.tc.audioplayer.utils.StatusBarUtil;
import com.tc.audioplayer.widget.Minibar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itcayman on 2017/8/20.
 */

public class ToolbarActivity extends AppCompatActivity {
    protected EventBus eventBus;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_content)
    protected FrameLayout flContent;
    @BindView(R.id.tv_retry)
    TextView tvRetry;
    @BindView(R.id.minibar)
    public Minibar minibar;

    private Drawable[] mAlphaDrawable;
    private LayerDrawable mLayerDrawable;
    private int mCurrentAlpha = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base_with_toolbar);
        ButterKnife.bind(this);
        eventBus = new EventBus();
        eventBus.register(this);
//        registerEventBus(configDefaultRigsterFlags());

        initToolbar();
        setSupportActionBar(toolbar);
        LayoutInflater.from(this).inflate(layoutResID, flContent, true);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        toolbar.setNavigationOnClickListener((v) -> finish());
    }

    /**
     * 初始化toolbar的样式
     */
    private void initToolbar() {
        mAlphaDrawable = new Drawable[2];
        mAlphaDrawable[0] = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        mAlphaDrawable[1] = new ColorDrawable(getResources().getColor(android.R.color.transparent));
        mLayerDrawable = new LayerDrawable(mAlphaDrawable);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitleMargin(0, 1, 0, 1);
    }

    public void setToolbarTitle(CharSequence title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    public void setToolbarSubtitle(CharSequence subTitle) {
        toolbar.setSubtitle(subTitle);
        setSupportActionBar(toolbar);
    }

    /**
     * 改变toolbar的颜色透明度
     */
    protected void changeToolBarColor(int alpha) {
        if (mCurrentAlpha == alpha) {
            return;
        }
        mCurrentAlpha = alpha;
        mAlphaDrawable[0].setAlpha(alpha);
        mAlphaDrawable[1].setAlpha(255 - alpha);
        toolbar.setBackgroundDrawable(mLayerDrawable);
        toolbar.getBackground().setAlpha(0);
    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }
}
