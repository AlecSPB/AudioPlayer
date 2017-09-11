package com.tc.audioplayer.permission.core;

import android.support.v4.app.ActivityCompat;

import com.tc.audioplayer.permission.PermissionUtil;
import com.tc.base.utils.CompatibleUtils;
import com.tc.base.utils.SharedPreferencesUtil;
import com.zhy.m.permission.MPermissions;


/**
 * Created by itcayman on 16/11/28.
 */

public class ActivityPermissionProxy extends BasePermissionProxy {

    private IPermissionActivity mActivity;

    public ActivityPermissionProxy(IPermissionActivity activity, RunTimePermission runTimePermission) {
        super(runTimePermission);
        this.mActivity = activity;
    }

    @Override
    public void requestPermission(final PermissionParam param) {
        if (CompatibleUtils.isDestroyed(mActivity.getActivity())) {
            return;
        }
        if (!checkPermission(mActivity.getActivity(), param.permissionName)) {
            mActivity.initPermission(runTimePermission);
        } else {
            callPermissionGrant(mActivity, param.requestCode);
            return;
        }
        if (param.unUseSystem && (!param.isForce && !param.isFirstTime && !ActivityCompat.shouldShowRequestPermissionRationale(mActivity.getActivity(), param.permissionName))) {
            dialog = PermissionUtil.popPermissionDialog(mActivity.getActivity(), param.requestCode, false, param.rational, new PermissionUtil.PermissionListener() {
                @Override
                public void clickPositive() {
                }

                @Override
                public void clickNegative() {
                    //拒绝时 回调注解了@PermissionDenied的方法
                    callPermissionDenied(mActivity, param.requestCode);
                }
            });
        } else {
            MPermissions.requestPermissions(mActivity.getActivity(), param.requestCode, param.permissionName);
            SharedPreferencesUtil.putBoolean(SharedPreferencesUtil.STATUS, param.permissionName, false);
        }
    }

    @Override
    public void permissionDenied(PermissionParam param) {
        if (CompatibleUtils.isDestroyed(mActivity.getActivity())) {
            return;
        }
        if (param.isForce) {
            if (param.unUseSystem && (!ActivityCompat.shouldShowRequestPermissionRationale(mActivity.getActivity(), param.permissionName))) {
                dialog = PermissionUtil.popPermissionDialog(mActivity.getActivity(), param.requestCode, true, param.rational);
            } else {
                MPermissions.requestPermissions(mActivity.getActivity(), param.requestCode, param.permissionName);
            }
        }
    }

    @Override
    public void onPermissionResult(PermissionParam param) {
        if (CompatibleUtils.isDestroyed(mActivity.getActivity())) {
            return;
        }
        if (checkPermission(mActivity.getActivity(), param.permissionName)) {
            callPermissionGrant(mActivity, param.requestCode);
        } else {
            callPermissionDenied(mActivity, param.requestCode);
            if (param.isForce) {
                mActivity.finish();
            }
        }
    }

    @Override
    public void requestPermissionShowDialog(final PermissionParam param) {
        if (CompatibleUtils.isDestroyed(mActivity.getActivity())) {
            return;
        }
        if (!checkPermission(mActivity.getActivity(), param.permissionName)) {
            mActivity.initPermission(runTimePermission);
        } else {
            callPermissionGrant(mActivity, param.requestCode);
            return;
        }
        dialog = PermissionUtil.popPermissionDialog(mActivity.getActivity(), param.requestCode, false, param.rational, new PermissionUtil.PermissionListener() {
            @Override
            public void clickPositive() {
            }

            @Override
            public void clickNegative() {
                //拒绝时 回调注解了@PermissionDenied的方法
                callPermissionDenied(mActivity, param.requestCode);
            }
        });
    }

    @Override
    protected void destroyTarget() {
        mActivity = null;
    }
}
