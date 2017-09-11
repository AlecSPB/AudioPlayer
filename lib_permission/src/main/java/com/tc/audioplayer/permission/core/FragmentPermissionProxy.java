package com.sankuai.moviepro.permission.core;

import android.support.v4.app.ActivityCompat;

import com.sankuai.moviepro.common.utils.SharedPreferencesUtil;
import com.sankuai.moviepro.permission.MovieProPermissionUtil;
import com.zhy.m.permission.MPermissions;

/**
 * Created by tianchao on 16/11/28.
 */

public class FragmentPermissionProxy extends BasePermissionProxy {

    private IPermissionFragment mFragment;

    public FragmentPermissionProxy(IPermissionFragment fragment, RunTimePermission runTimePermission) {
        super(runTimePermission);
        this.mFragment = fragment;
    }

    @Override
    public void requestPermission(final PermissionParam param) {
        if (mFragment == null || !mFragment.isAdded()) {
            return;
        }
        if (!checkPermission(mFragment.getContext(), param.permissionName)) {
            mFragment.getPermissionActivity().initPermission(runTimePermission);
        } else {
            callPermissionGrant(mFragment, param.requestCode);
            return;
        }
        if (param.unUseSystem && (!param.isForce && !param.isFirstTime && !ActivityCompat.shouldShowRequestPermissionRationale(mFragment.getPermissionActivity().getActivity(), param.permissionName))) {
            dialog = MovieProPermissionUtil.popPermissionDialog(mFragment.getPermissionActivity().getActivity(), param.requestCode, false, param.rational, new MovieProPermissionUtil.PermissionListener() {
                @Override
                public void clickPositive() {
                }

                @Override
                public void clickNegative() {
                    //拒绝时 回调注解了@PermissionDenied的方法
                    callPermissionDenied(mFragment, param.requestCode);
                }
            });
        } else {
            MPermissions.requestPermissions(mFragment.getFragment(), param.requestCode, param.permissionName);
            SharedPreferencesUtil.putBoolean(SharedPreferencesUtil.STATUS, param.permissionName, false);
        }
    }

    @Override
    public void permissionDenied(PermissionParam param) {
        if (mFragment == null || !mFragment.isAdded()) {
            return;
        }
        if (param.isForce) {
            if (param.unUseSystem && (!ActivityCompat.shouldShowRequestPermissionRationale(mFragment.getPermissionActivity().getActivity(), param.permissionName))) {
                dialog = MovieProPermissionUtil.popPermissionDialog(mFragment.getPermissionActivity().getActivity(), param.requestCode, true, param.rational);
            } else {
                MPermissions.requestPermissions(mFragment.getFragment(), param.requestCode, param.permissionName);
            }
        }
    }

    @Override
    public void onPermissionResult(PermissionParam param) {
        if (mFragment == null || !mFragment.isAdded()) {
            return;
        }
        if (!checkPermission(mFragment.getContext(), param.permissionName)) {
            callPermissionGrant(mFragment, param.requestCode);
        } else {
            callPermissionDenied(mFragment, param.requestCode);
            if (param.isForce) {
                mFragment.getPermissionActivity().getActivity().finish();
            }
        }
    }

    @Override
    public void requestPermissionShowDialog(final PermissionParam param) {
        if (mFragment == null || !mFragment.isAdded()) {
            return;
        }
        if (!checkPermission(mFragment.getContext(), param.permissionName)) {
            mFragment.getPermissionActivity().initPermission(runTimePermission);
        } else {
            callPermissionGrant(mFragment, param.requestCode);
            return;
        }
        dialog = MovieProPermissionUtil.popPermissionDialog(mFragment.getPermissionActivity().getActivity(), param.requestCode, false, param.rational, new MovieProPermissionUtil.PermissionListener() {
            @Override
            public void clickPositive() {
            }

            @Override
            public void clickNegative() {
                //拒绝时 回调注解了@PermissionDenied的方法
                callPermissionDenied(mFragment, param.requestCode);
            }
        });
    }

    @Override
    protected void destroyTarget() {
        mFragment = null;
    }
}
