package com.tc.audioplayer.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.utils.DimenUtils;
import com.tc.audioplayer.utils.StatusBarUtil;
import com.tc.audioplayer.widget.ClearableEditText;
import com.tc.audioplayer.widget.Minibar;
import com.tc.audioplayer.widget.blur.BlurImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itcayman on 2017/8/20.
 */

public abstract class ToolbarActivity extends SwipeBackActivity implements IView {
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.fl_content)
    protected FrameLayout flContent;
    @BindView(R.id.tv_retry)
    protected TextView tvRetry;
    @BindView(R.id.minibar)
    protected Minibar minibar;
    @BindView(R.id.bg_header)
    protected BlurImageView ivBg;
    @BindView(R.id.fl_toolbar)
    protected FrameLayout flToolbarContent;
    @BindView(R.id.cet_search)
    protected ClearableEditText cetSearch;
    @BindView(R.id.tv_cancel)
    protected TextView tvCancel;
    @BindView(R.id.tv_center_title)
    protected TextView tvCenterTitle;
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;

    private Drawable[] mAlphaDrawable;
    private LayerDrawable mLayerDrawable;
    private int mCurrentAlpha = -1;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base_with_toolbar);
        ButterKnife.bind(this);
//        registerEventBus(configDefaultRigsterFlags());

        initToolbar();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        LayoutInflater.from(this).inflate(layoutResID, flContent, true);
        StatusBarUtil.setTranslucentForImageView(this, 0, coordinatorLayout);
        toolbar.setNavigationOnClickListener((v) -> finish());
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        tvCancel.setOnClickListener((v) -> finish());
        tvRetry.setOnClickListener((v) -> onRefresh());
        swipeRefreshLayout.setOnRefreshListener(() -> onRefresh());
    }

    protected void setContentUnderToolbar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) swipeRefreshLayout.getLayoutParams();
        params.topMargin = (int) getResources().getDimension(R.dimen.header_height);
        swipeRefreshLayout.setLayoutParams(params);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent), 0);
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
        toolbar.setSubtitleTextColor(Color.LTGRAY);
        toolbar.setTitleMargin(0, 0, 0, DimenUtils.dp2px(this, 10));
        toolbar.setContentInsetStartWithNavigation(0);
    }

    public void setToolbarTitle(CharSequence title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((v) -> finish());
    }

    public void setToolbarCenterTitle(String title) {
        setToolbarTitle(toolbar, title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((v) -> finish());
    }

    public void setToolbarSubtitle(CharSequence subTitle) {
        toolbar.setSubtitle(subTitle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((v) -> finish());
    }

    /**
     * 背景图全屏显示
     */
    protected void setBgImageFitScreen() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivBg.getLayoutParams();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        ivBg.setLayoutParams(params);
        ivBg.setVisibility(View.VISIBLE);
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

    @Override
    public void handleThrowable(Throwable t) {
        swipeRefreshLayout.setRefreshing(false);
        tvRetry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setData(Object data) {
        tvRetry.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }
}
