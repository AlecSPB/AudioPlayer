package com.tc.model.usecase;

import com.tc.model.net.APIServiceProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by tianchao on 2017/8/3.
 */

public abstract class BaseCase<T> {
    public static final int MINUTE = 60;
    public static final int HOUR = MINUTE * 60;
    public static final int DAY = HOUR * 24;

    protected T api;

    public BaseCase() {
        Type type = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType) type).getActualTypeArguments()[0];
        api = APIServiceProvider.create((Class<T>) t);
    }
}
