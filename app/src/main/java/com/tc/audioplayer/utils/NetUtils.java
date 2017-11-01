package com.tc.audioplayer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tc.audioplayer.AudioApplication;

/**
 * Created by itcayman on 2017/11/1.
 */

public class NetUtils {

    public static boolean isNetworkAvailable() {
        if (AudioApplication.getInstance() == null) {
            return false;
        }
        ConnectivityManager conmgr = (ConnectivityManager) AudioApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conmgr == null) {
            return false;
        }

        // 修改解决判断网络时的崩溃
        // mobile 3G Data Network
        try {
            NetworkInfo net3g = conmgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (net3g != null) {
                NetworkInfo.State mobile = net3g.getState();// 显示3G网络连接状态
                if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                    return true;
                }
            }
        } catch (Exception e) {
        }

        try {
            NetworkInfo netwifi = conmgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (netwifi != null) {
                NetworkInfo.State wifi = netwifi.getState(); // wifi
                // 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
                if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                    return true;
                }
            }
        } catch (Exception e) {
        }

        return false;
    }
}
