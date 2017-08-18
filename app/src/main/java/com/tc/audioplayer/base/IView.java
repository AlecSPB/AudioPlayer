package com.tc.audioplayer.base;

/**
 * Created by tianchao on 2017/8/4.
 */

public interface IView<D> {
    void setData(D data);

    void handleThrowable(Throwable t);
}
