package com.tc.audioplayer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.PermissionCode;
import com.tc.audioplayer.permission.core.RunTimePermission;
import com.tc.base.utils.CollectionUtil;
import com.tc.base.utils.TLogger;
import com.zhy.m.permission.MPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by itcayman on 2017/9/11.
 */

public class BaseActivity extends AppCompatActivity implements IPermissionActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected EventBus eventBus;
    private ArrayList<RunTimePermission> mRunTimePermissions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
        registerEventBus(configDefaultRigsterFlags());
    }

    @Override
    public void initPermission(RunTimePermission runTimePermission) {
        TLogger.d(TAG, "initPermission: " + runTimePermission.getRequestPermission());
        if (mRunTimePermissions != null) {
            for (RunTimePermission permission : mRunTimePermissions) {
                if (permission.getRequestPermission() == runTimePermission.getRequestPermission()) {
                    mRunTimePermissions.remove(permission);
                }
            }
            mRunTimePermissions.add(runTimePermission);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TLogger.d(TAG, "onActivityResult: requestCode=" + requestCode + " resultCode=" + resultCode);
        if (!CollectionUtil.isEmpty(mRunTimePermissions)) {
            if (PermissionCode.isSettingRequest(requestCode)) {
                for (RunTimePermission runTimePermission : mRunTimePermissions) {
                    if (runTimePermission.getRequestCode() == requestCode) {
                        runTimePermission.onPermissionResult();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        } catch (RuntimeException e) {
            TLogger.e(TAG, "onRequestPermissionsResult exception:", e.toString());
            /**
             * 在Fragment中使用请求权限，会先回调activity的onRequestPermissionsResult，原因 "？"
             * catch到this exception, 表明the activity's not PermissionProxy，会在Fragment的onRequestPermissionsResult中处理
             */
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventBus != null) {
            eventBus.unregister(this);
        }
    }

    /**
     * 当前Fragment作为一个订阅者，默认订阅周期为onActivityCreated()-----onDestroyView()
     * 如果需要改变订阅者的订阅周期，无须重写此方法
     * 请直接在子类的相应周期回调中调用eventBus.register***(this) 和 eventBus.unregister(this)即可
     *
     * @return
     */
    protected int configDefaultRigsterFlags() {
        return EventBusRegisterFlags.NOT_NEED_DEFAULT_REGISTER;
    }

    private void registerEventBus(int flag) {
        if (eventBus.isRegistered(this) || flag == EventBusRegisterFlags.NOT_NEED_DEFAULT_REGISTER)
            return;

        eventBus.register(this);
    }

}
