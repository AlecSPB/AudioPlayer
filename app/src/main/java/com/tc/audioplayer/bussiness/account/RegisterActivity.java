package com.tc.audioplayer.bussiness.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseActivity;
import com.tc.audioplayer.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tianchao on 2017/9/19.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.root)
    ConstraintLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    @OnClick(R.id.tv_back)
    public void onBack() {
        finish();
    }

    @OnClick(R.id.btn_register)
    public void register() {

    }
}
