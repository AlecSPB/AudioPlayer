package com.tc.audioplayer.permission.core;

import android.content.Context;

import com.tc.base.utils.SharedPreferencesUtil;


/**
 * 运行时权限
 * <p>
 * Created by itcayman on 16/9/9.
 * <p>
 * 申请权限请继承该类
 * <p>
 * 权限弹窗原则：
 * 1：优先弹出系统的申请权限弹窗；
 * 2：再次申请的系统弹窗会带有"不再显示"
 * 3：用户不选择不再显示 持续弹出带有"不再显示"的弹窗
 * 4：当用户选择了"不再显示"时，弹出自定义的dialog调转设置页面
 * <p>
 * 使用调用requestPermission
 * 注意：不要在onResume 中调用requestPermission 会造成调用死循环。
 *
 * @PermissionGrant 注解的方法中调用permissionGrant()获取权限成功
 * @PermissionDenied 注解的方法中调用permissionDenied()获取权限失败，失败会再次请求
 */
public abstract class RunTimePermission {

    private BasePermissionProxy mPermissionProxy;
    private BasePermissionProxy.PermissionParam mPermissionParam;

    //是否必须获取到该权限，默认是true，必须获取到权限，获取不到将不停地请求权限
    // false的时候将只弹出一次弹窗
    private boolean isForce = true;
    private boolean unUseSystem = true;
    private String rationalString;

    //在Activity中获取回调
    public RunTimePermission(IPermissionActivity activity) {
        rationalString = getRationalString(activity.getActivity());
        mPermissionProxy = new ActivityPermissionProxy(activity, this);
        init();
    }

    //在Fragment中获取回调
    public RunTimePermission(IPermissionFragment fragment) {
        rationalString = getRationalString(fragment.getContext());
        mPermissionProxy = new FragmentPermissionProxy(fragment, this);
        init();
    }

    private void init() {
        mPermissionParam = new BasePermissionProxy.PermissionParam();
    }

    //实现该方法设置权限的请求码，请求码在PermissionCode中指定
    public abstract int getRequestCode();

    //返回请求的具体权限
    public abstract String getRequestPermission();

    //不再询问后弹出Dialog的提示信息
    public abstract String getRationalString(Context context);

    /**
     * 设置弹出框提示文字
     *
     * @param rationalString
     */
    public void setRationalString(String rationalString) {
        this.rationalString = rationalString;
    }

    private void updatePermissionParam() {
        mPermissionParam.isForce = isForce;
        mPermissionParam.rational = this.rationalString;
        mPermissionParam.unUseSystem = this.unUseSystem;
        if (!unUseSystem) {
            isForce = false;
            mPermissionParam.isForce = false;
        }
        mPermissionParam.permissionName = getRequestPermission();
        mPermissionParam.requestCode = getRequestCode();
        mPermissionParam.isFirstTime = SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.STATUS, getRequestPermission(), true);
    }

    /**
     * 请求权限
     */
    public void requestPermission() {
        updatePermissionParam();
        mPermissionProxy.requestPermission(mPermissionParam);
    }

    /**
     * 使用自定义的弹窗请求一次跳转权限设置页面
     * 仍然回调注解 @PermissionGrant，@PermissionDenied
     */
    public void requestPermissionShowDialog() {
        updatePermissionParam();
        mPermissionParam.isForce = false;
        mPermissionProxy.requestPermissionShowDialog(mPermissionParam);
    }

    /**
     * 注解了@PermissionDenied的方法中调用，获取权限失败，调用该方法会多次请求权限，直到请求成功，再回调@PermissionGrant注解方法
     */
    public void permissionDenied() {
        updatePermissionParam();
        mPermissionProxy.permissionDenied(mPermissionParam);
    }

    /**
     * 从系统的权限设置页返回，调用的方法
     */
    public void onPermissionResult() {
        updatePermissionParam();
        mPermissionProxy.onPermissionResult(mPermissionParam);
    }

    /**
     * 注销资源
     */
    public void permissionDestroy() {
        if (mPermissionProxy != null) {
            mPermissionProxy.destroy();
            mPermissionProxy = null;
        }
    }

    /**
     * 设置是否强制获取权限，false的话将会只弹出一次弹窗
     *
     * @param isForce
     */
    public void setIsForce(boolean isForce) {
        this.isForce = isForce;
    }

    /**
     * 是否使用系统的弹窗逻辑 无自定义弹窗
     * 如果使用了系统逻辑 ，将会有可能拿不到权限，这样将IsForce屏蔽掉，不是强制获取
     */
    public void setUseSystem(boolean useSystem) {
        unUseSystem = !useSystem;
    }

}
