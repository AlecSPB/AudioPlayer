package com.tc.audioplayer;

import android.content.Context;
import android.content.Intent;

import com.tc.audioplayer.bussiness.PlayerDetailActivity;

/**
 * Created by itcayman on 2017/8/22.
 */

public class Navigator {
    /**
     * 播放详情页
     */
    public static void toPlayerDetailActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PlayerDetailActivity.class);
        context.startActivity(intent);
    }
}
