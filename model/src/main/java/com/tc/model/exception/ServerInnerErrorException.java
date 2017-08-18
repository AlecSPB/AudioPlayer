package com.tc.model.exception;

import java.io.IOException;

/**
 * Created by tianchao on 2017/8/3.
 */

public class ServerInnerErrorException extends IOException {
    private int code = 0;
    private String info;

    public ServerInnerErrorException(int code, String message) {
        super(message);
        this.code = code;
        this.info = null;
    }

    public ServerInnerErrorException(int code, String message, String info) {
        super(message);
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}