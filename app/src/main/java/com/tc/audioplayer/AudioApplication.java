package com.tc.audioplayer;

import android.support.multidex.MultiDexApplication;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.FileUtil;
import com.tc.base.utils.SharedPreferencesUtil;
import com.tc.model.net.APIServiceProvider;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tianchao on 2017/8/4.
 */

public class AudioApplication extends MultiDexApplication {
    private static final String TAG = AudioApplication.class.getSimpleName();
    private static AudioApplication instance;

    public static AudioApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        PlayerManager.getInstance().startPlayService();
        OkHttpClient client = getModelConfig();
        APIServiceProvider.init(this, client);
        SharedPreferencesUtil.init(this);
        FirebaseApp.initializeApp(this);
        MobileAds.initialize(this, "ca-app-pub-7199806726993025~2598142485");

        File cacheDir = new File(FileUtil.PATH_LRC);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
    }

    private OkHttpClient getModelConfig() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = getRequestWithSign(chain);
                        return chain.proceed(request);
                    }
                })
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

    }

    /**
     * 给request添加新网关的header
     */
    public static Request getRequestWithSign(Interceptor.Chain chain) {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .header("User-Agent", "android_6.0.0.3;baiduyinyue")
                .header("deviceid", "99000968045159")
                .header("cuid", "C064D86BF3AD83C5AFF4AFA3DE3ACB30");
        return requestBuilder.build();
    }
}
