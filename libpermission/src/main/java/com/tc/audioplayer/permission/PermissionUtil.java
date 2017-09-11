package com.tc.audioplayer.permission;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.tc.audioplayer.permission.core.RunTimePermission;
import com.tc.base.utils.CompatibleUtils;
import com.zhy.m.permission.MPermissions;

/**
 * Created by itcayman on 16/9/9.
 * 权限请求Util
 */
public class PermissionUtil {

    /**
     * 检测是否获取到权限
     */
    public static boolean checkSelfPermission(Context context, String permission) {
        if (null == context) {
            return false;
        }
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * 在一个权限请求Dialog 请求多个权限
     */
    public static void requestPermissions(AppCompatActivity activity, int requestCode, RunTimePermission... permissions) {
        if (CompatibleUtils.isDestroyed(activity)) {
            return;
        }
        String[] runTimePermissions = new String[permissions.length];
        for (int i = 0; i < runTimePermissions.length; i++) {
            runTimePermissions[i] = permissions[i].getRequestPermission();
        }
        MPermissions.requestPermissions(activity, requestCode, runTimePermissions);
    }

    /**
     * 跳转到系统权限设置页
     */
    public static Dialog popPermissionDialog(final Activity activity, final int requestCode, final boolean force, String msg) {
        return popPermissionDialog(activity, requestCode, force, msg, null);
    }

    /**
     * 跳转到系统权限设置页
     */
    public static Dialog popPermissionDialog(final Activity activity, final int requestCode, final boolean force, String msg, final PermissionListener listener) {
        if (null == activity) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.permission_btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null)
                    listener.clickPositive();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivityForResult(intent, requestCode);
            }
        });
        builder.setNegativeButton(R.string.permission_btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null)
                    listener.clickNegative();
                if (force && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        });
        Dialog dialog = null;
        if (!activity.isFinishing()) {
            dialog = builder.create();
            dialog.show();
        }
        return dialog;
    }

    public interface PermissionListener {
        public void clickPositive();

        public void clickNegative();
    }
}
