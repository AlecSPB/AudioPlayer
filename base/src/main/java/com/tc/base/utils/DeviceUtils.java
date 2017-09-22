package com.tc.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by itcayman on 2017/9/22.
 */

public class DeviceUtils {
    public static final int CPU_TYPE_UNDEFIND = 0; // 未指定的指令集
    public static final int CPU_TYPE_ARM = 1; // ARM 指令集
    public static final int CPU_TYPE_X86 = 2; // x86 指令集
    public static final int CPU_TYPE_MIPS = 3; // mips 指令集
    private static int sCPUType = CPU_TYPE_UNDEFIND; // 默认未指定cpu架构

    private DeviceUtils() {

    }

    /**
     * 获取设备机型.
     *
     * @return 设备机型字符串
     */
    public static String getDeviceType() {
        return Build.MODEL;
    }

    /**
     * 获取系统版本.
     *
     * @return 当前版本
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取应用版本名称.
     *
     * @param context 上下文
     * @return 应用版本名
     */
    public static String getAppVersionName(Context context) {
        PackageInfo pi = getPackageInfo(context);
        if (null != pi) {
            return pi.versionName;
        }
        return null;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    public static String getMobileBrand() {
        return Build.BOARD;
    }

    /**
     * 获取显示矩阵。
     *
     * @param context 上下文
     * @return 返回矩阵
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        try {
            WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dm;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度。
     *
     * @param context 上下文
     * @return 宽度
     */
    public static int getScreenWidthPx(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕高度。
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getScreenHeightPx(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 获取当前手机的类型.
     *
     * @return DeviceUtils.CPU_TYPE_ARM or DeviceUtils.CPU_TYPE_X86 or
     * DeviceUtils.CPU_TYPE_MIPS or
     */
    public static int getCpuType() {
        // 已经检查过cpu类型，直接返回
        if (sCPUType != CPU_TYPE_UNDEFIND) {
            return sCPUType;
        }

        int result = CPU_TYPE_UNDEFIND;

        String cpuType = Build.CPU_ABI;

        if (cpuType.indexOf("arm") >= 0) {
            result = CPU_TYPE_ARM;
        } else if (cpuType.indexOf("x86") >= 0) {
            result = CPU_TYPE_X86;
        } else if (cpuType.indexOf("mips") >= 0) {
            result = CPU_TYPE_MIPS;
        }
        // 缓存cpu类型
        sCPUType = result;
        return result;
    }

    /**
     * 获取设备号，imei号。
     *
     * @param context 上下文
     * @return 设备id
     */
    public static String getIMEI(Context context) {
        String Imei = ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE)).getDeviceId();
        return Imei;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

//    /**
//     * 获取状态栏高度
//     * */
//    public static int getStatusBarHeight(Activity activity) {
//        Rect rect = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//        return rect.top;
//    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
