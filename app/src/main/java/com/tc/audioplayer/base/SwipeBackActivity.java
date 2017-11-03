package com.tc.audioplayer.base;

import android.os.Bundle;
import android.view.View;

import com.tc.audioplayer.R;
import com.tc.audioplayer.utils.swipeback.SwipeBackActivityBase;
import com.tc.audioplayer.utils.swipeback.SwipeBackActivityHelper;
import com.tc.audioplayer.utils.swipeback.SwipeBackLayout;
import com.tc.audioplayer.utils.swipeback.SwipeUtils;

/**
 * Created by itcayman on 2017/11/3.
 */

public class SwipeBackActivity extends BaseActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.common_slide_in_right, R.anim.common_none);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null) {
            return mHelper.findViewById(id);
        }
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        SwipeUtils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.common_none, R.anim.common_slide_out_right);
    }
}
