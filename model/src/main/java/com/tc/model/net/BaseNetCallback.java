package com.tc.model.net;

import com.tc.model.exception.ServerErrorThrowable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tianchao on 2017/8/3.
 * 对 retrofit 原生的 Callback 进行封装，原生的无论 http code 值为多少都会走 onResponse，
 * 只有在网络出现了问题例如没有开网络才会走 onFailure。
 * 利用 http code 将返回区分开。
 * 在 onFailure 中，若参数为 Throwable 类则是网络异常，若参数为 ServerErrorThrowable 类则为服务器异常
 */

public abstract class BaseNetCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponseSuccess(response.body());
        } else {
            ServerErrorThrowable throwable = new ServerErrorThrowable(response.code());
            onFailure(call, throwable);
        }
    }

    public abstract void onResponseSuccess(T responseBody);

}