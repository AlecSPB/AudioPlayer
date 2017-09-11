package com.sankuai.moviepro.permission.core;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.zhy.m.permission.PermissionProxy;

/**
 * Created by tianchao on 16/11/28.
 */

public abstract class BasePermissionProxy {
    private static final String SUFFIX = "$$PermissionProxy";
    protected Dialog dialog;
    protected RunTimePermission runTimePermission;

    BasePermissionProxy(RunTimePermission runTimePermission) {
        this.runTimePermission = runTimePermission;
    }

    public void destroy() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        destroyTarget();
    }

    protected abstract void destroyTarget();

    public abstract void requestPermission(PermissionParam param);

    /**
     * 注解了@PermissionDenied的方法中调用，获取权限失败，调用该方法会多次请求权限，直到请求成功，再回调@PermissionGrant注解方法
     */
    public abstract void permissionDenied(PermissionParam param);

    /**
     * 从系统的权限设置页返回，调用的方法
     */
    public abstract void onPermissionResult(PermissionParam param);

    /**
     * 使用自定义的弹窗请求一次跳转权限设置页面
     * 仍然回调注解 @PermissionGrant，@PermissionDenied
     */
    public abstract void requestPermissionShowDialog(PermissionParam param);

    /**
     * 检测是否已经获取到该权限
     */
    public boolean checkPermission(Context context, String permission) {
        if (null == context) {
            return false;
        }
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * 手动回调@PermissionDenied 请求失败
     *
     * @param activity 可以是Activity 或者 Fragment
     */
    protected void callPermissionDenied(Object activity, int requestCode) {
        PermissionProxy permissionProxy = getPermissionProxy(activity);
        if (permissionProxy == null) {
            return;
        }
        permissionProxy.denied(activity, requestCode);
    }

    protected PermissionProxy getPermissionProxy(Object activity) {
        try {
            Class clazz = activity.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + BasePermissionProxy.SUFFIX);
            return (PermissionProxy) injectorClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
//            Exception exception = new RuntimeException(String.format("can not find %s , something when compiler.", activity.getClass().getSimpleName() + SUFFIX));
//            exception.printStackTrace();
        }
        return null;
    }


    /**
     * 手动回调@PermissionGrant 请求成功
     *
     * @param activity 可以是Activity 或者 Fragment
     */
    protected void callPermissionGrant(Object activity, int requestCode) {
        PermissionProxy permissionProxy = getPermissionProxy(activity);
        if (permissionProxy == null) {
            return;
        }
        permissionProxy.grant(activity, requestCode);
    }

    public static class PermissionParam {
        public boolean isForce;//强制使用权限
        public boolean isFirstTime;//第一次请求
        public String permissionName;//权限名
        public int requestCode;//请求码
        public String rational;//不再询问后弹出Dialog的提示信息
        public boolean unUseSystem;  //不使用系统弹窗原则 当使用系统的时候  isForce会被设置为false 因为使用系统逻辑有可能获取不到。
    }
}
