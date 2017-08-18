package com.tc.model.utils;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.tc.model.exception.ServerInnerErrorException;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by tianchao on 2017/8/3.
 * 异常分析的工具类
 */

public class ExceptionUtils {

    public static boolean isServerException(Throwable throwable) {
        return throwable instanceof ServerInnerErrorException || throwable.getCause() instanceof ServerInnerErrorException;
    }

    public static boolean isRequestException(Throwable throwable) {
        return isNetException(throwable)
                || isTimeOutException(throwable);
    }

    public static boolean isNetException(Throwable throwable) {
        return throwable instanceof HttpException
                || throwable instanceof MalformedJsonException
                || throwable instanceof JsonSyntaxException
                || throwable instanceof java.net.UnknownHostException
                || throwable instanceof java.net.ConnectException;
    }

    public static boolean isTimeOutException(Throwable throwable) {
        return throwable instanceof SocketTimeoutException || throwable instanceof ConnectTimeoutException;
    }
}
