package com.tc.audioplayer.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.PermissionCode;
import com.tc.audioplayer.permission.core.RunTimePermission;
import com.tc.base.utils.CollectionUtil;
import com.tc.base.utils.TLogger;
import com.zhy.m.permission.MPermissions;

import java.util.ArrayList;

/**
 * Created by itcayman on 2017/9/11.
 */

public class BaseActivity extends AppCompatActivity implements IPermissionActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private ArrayList<RunTimePermission> mRunTimePermissions = new ArrayList<>();

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
}
