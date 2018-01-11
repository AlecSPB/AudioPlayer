package com.tc.audioplayer.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by itcayman on 2018/1/11.
 */

public class MarketUtils {
    /**
     * 通过包名 在应用商店打开应用
     *
     * @param packageName 包名
     */
    public static void openApplicationMarket(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            String str = "market://details?id=" + packageName;
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        } catch (Exception e) {
            // 打开应用商店失败 可能是没有手机没有安装应用市场
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
            //这里存在一个极端情况就是有些用户浏览器也没有，再判断一次
            if (intent.resolveActivity(context.getPackageManager()) != null) { //有浏览器
                context.startActivity(intent);
            } else { //天哪，这还是智能手机吗？
                Toast.makeText(context, "天啊，您没安装应用市场，连浏览器也没有，您买个手机干啥？", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 跳转到推荐应用
     */
    public static void gotoRelatedApps(Context context) {
        Uri marketUri = Uri.parse("market://search?q=pub:雪峰娱乐");
        //if (myGlobal.currentLang.equals("en")) {
        //    marketUri = Uri.parse("market://search?q=Xuefeng Music");
        //}

        try {
            Intent i = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(context, "不可用 not available", Toast.LENGTH_LONG).show();
        }
    }
}
