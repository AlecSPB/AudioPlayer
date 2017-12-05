package com.tc.audioplayer;

import android.content.ComponentName;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.multidex.MultiDexApplication;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.tc.audioplayer.config.CacheInterceptor;
import com.tc.audioplayer.player.PlayService;
import com.tc.audioplayer.utils.BuglyUtils;
import com.tc.audioplayer.utils.FileUtil;
import com.tc.base.utils.SharedPreferencesUtil;
import com.tc.base.utils.TLogger;
import com.tc.model.net.APIServiceProvider;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
        OkHttpClient client = getModelConfig();
        APIServiceProvider.init(this, client);
        SharedPreferencesUtil.init(this);
        FirebaseApp.initializeApp(this);
        MobileAds.initialize(this, "ca-app-pub-7199806726993025~2598142485");
        BuglyUtils.init(this);

        FileUtil.checkCacheDir();
        TLogger.d(TAG, "application init service");
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayService.PlayBinder binder = (PlayService.PlayBinder) service;
                TLogger.d(TAG, "onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this, PlayService.class);
        bindService(intent, serviceConnection, ContextWrapper.BIND_AUTO_CREATE);
    }

    private OkHttpClient getModelConfig() {
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        File cacheFile = new File(getCacheDir(), "[缓存目录]");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient client = new OkHttpClient.Builder()
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
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .build();
        return client;
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
