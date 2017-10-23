package com.tc.audioplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tc.audioplayer.bussiness.account.LoginActivity;
import com.tc.audioplayer.bussiness.account.RegisterActivity;
import com.tc.audioplayer.bussiness.album.AlbumnDetailActivity;
import com.tc.audioplayer.bussiness.artist.ArtistDetailActivity;
import com.tc.audioplayer.bussiness.player.PlayerDetailActivity;
import com.tc.audioplayer.bussiness.search.SearchActivity;

/**
 * Created by itcayman on 2017/8/22.
 */

public class Navigator {
    public static final int REQUEST_CODE_REGISTER = 1001;

    /**
     * 播放详情页
     */
    public static void toPlayerDetailActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PlayerDetailActivity.class);
        context.startActivity(intent);
    }

    public static void toSearchActivity(Context context, String keyword) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("keyword", keyword);
        context.startActivity(intent);
    }

    public static void toMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void toLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void toRegisterActivity(Activity context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }

    public static void toAlbumnDetailActivity(Context context, String albumnid) {
        Intent intent = new Intent(context, AlbumnDetailActivity.class);
        intent.putExtra("albumnid", albumnid);
        context.startActivity(intent);
    }

    public static void toArtistDetailActivity(Context context, String tinguid, String albumnid,
                                              String author, String authorImg, String country) {
        Intent intent = new Intent(context, ArtistDetailActivity.class);
        intent.putExtra("albumnid", albumnid);
        intent.putExtra("tinguid", tinguid);
        intent.putExtra("author", author);
        intent.putExtra("authorImg", authorImg);
        intent.putExtra("country", country);
        context.startActivity(intent);
    }
}
