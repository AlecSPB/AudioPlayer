package com.tc.audioplayer.utils;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tc.base.utils.DeviceUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by itcayman on 2017/9/28.
 */

public class BuglyUtils {
    public static void init(Context context) {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setDeviceID(DeviceUtils.getDeviceId(context));//设置设备号
        strategy.setAppChannel("GooglePlay");  //设置渠道
        strategy.setAppVersion("1.0.0");  //App的版本
        strategy.setAppPackageName(context.getPackageName());  //App的包名
        goForGround(context);
        refreshUserId();
        CrashReport.putUserData(context, "deviceid", DeviceUtils.getDeviceId(context));
        CrashReport.putUserData(context, "channel", "GooglePlay");
        CrashReport.initCrashReport(context, "cd8ad19242", false, strategy);
    }

    public static void refreshUserId() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String username = "游客";
        if (user != null) {
            username = "用户：" + user.getEmail();
        }
        CrashReport.setUserId(username);
    }

    public static void goForGround(Context context) {
        CrashReport.setIsAppForeground(context, true);
    }

    public static void goBackGround(Context context) {
        CrashReport.setIsAppForeground(context, false);
    }
}
