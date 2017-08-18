package com.tc.model.exception;

/**
 * Created by tianchao on 2017/8/3.
 * 在 BaseNetCallback 中遇到服务器错误就会抛出这个类型的错误
 */

public class ServerErrorThrowable extends Throwable {
    private int code;

    public ServerErrorThrowable(int code) {
        super("Server Error! Response code is " + code);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
