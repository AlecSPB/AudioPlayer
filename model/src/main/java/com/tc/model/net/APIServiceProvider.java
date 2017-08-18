package com.tc.model.net;

import android.content.Context;

import com.tc.model.api.APIConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by tianchao on 2017/8/3.
 */

public class APIServiceProvider {
    //缓存设置
    public static final String IS_FRESH = "refresh";
    public static final String CACHE_TIME = "cache_time";
    public static final int DEFAULT_CACHE_TIME = 30 * 60;

    private static Retrofit retrofit;

    public static void init(Context context, OkHttpClient client) {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create());
            builder.baseUrl(APIConstant.URL_BASE);
            builder.callFactory(client);
            retrofit = builder.build();
        }
    }

    public static <T> T create(final Class<T> service) {
        try {
            return retrofit.create(service);
        } catch (Exception e) {
            throw new RuntimeException("Create service Exception by class!");
        }
    }
}
