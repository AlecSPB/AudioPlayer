package com.tc.audioplayer.config;

import android.text.TextUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tc.audioplayer.utils.NetUtils;
import com.tc.model.net.APIServiceProvider;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by itcayman on 2017/11/1.
 * 通过添加header方法进行设置，例如：
 * Call<CinemaCompBox> getCinemaCompBox(
 *
 * @Query("cinemaId") int cinemaId,
 * @Header(APIServiceProvider.IS_FRESH) boolean refresh,
 * @Header(APIServiceProvider.CACHE_TIME) int cacheTime
 * );
 * 1.没有设置缓存时间，直接请求，没有缓存(老接口)
 * 2.设置缓存时间
 * a)设置refresh参数为true，刷新请求
 * b)refresh参数为false或者null，走缓存
 */

public class CacheInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int CACHE_TIME = 60 * 3600;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        String cacheTime = request.header(APIServiceProvider.CACHE_TIME);
//        if (TextUtils.isEmpty(cacheTime)) {
//            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
//            return chain.proceed(request);
//        }
        if (TextUtils.isEmpty(request.header(APIServiceProvider.IS_FRESH)) || !Boolean.parseBoolean(request.header(APIServiceProvider.IS_FRESH))) {
            if (!NetUtils.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            } else {
                request = request.newBuilder()
                        .header("Cache-Control", "public,  max-stale=" + CACHE_TIME)
                        .build();
            }
        } else {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();

        }
        Response originalResponse = chain.proceed(request);

        try {
            String body = tryToReadResponse(originalResponse);
            if (!TextUtils.isEmpty(body) && !needCache(body)) {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "no-cache")
                        .removeHeader("Pragma")
                        .build();
            }
        } catch (Exception e) {
        }

        if (NetUtils.isNetworkAvailable()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME)
                    .removeHeader("Pragma")
                    .build();
        }
    }

    /**
     * 读取Response body的内容
     *
     * @param response
     * @return
     * @throws IOException
     */
    public String tryToReadResponse(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        //注意 >>>>>>>>> okhttp3.4.1这里变成了 !HttpHeader.hasBody(response)
        if (!HttpHeaders.hasBody(response)) {
            //END HTTP
        } else if (bodyEncoded(response.headers())) {
            //HTTP (encoded body omitted)
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    //Couldn't decode the response body; charset is likely malformed.
                    return "";
                }
            }
            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                return result;
            }
        }
        return "";
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    /**
     * 根节点存在error将不使用缓存
     *
     * @param text
     * @return
     */
    private boolean needCache(String text) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(text);
            if (rootElement.isJsonObject()) {
                JsonObject root = rootElement.getAsJsonObject();
                if (root.has("error")) {
                    return false;
                }
            }
        } catch (Exception e) {

        }
        return true;
    }
}
