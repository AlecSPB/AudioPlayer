package com.tc.audioplayer.bussiness.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;

import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseActivity;
import com.tc.audioplayer.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tianchao on 2017/9/19.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_to_register)
    Button btnToRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    @OnClick(R.id.btn_to_register)
    public void toRegister() {
        Navigator.toRegisterActivity(this);
    }

    @OnClick(R.id.btn_login)
    public void login() {

    }
}
