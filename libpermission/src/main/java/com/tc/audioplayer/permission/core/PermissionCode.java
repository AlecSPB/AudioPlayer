package com.tc.audioplayer.permission.core;

/**
 * Created by itcayman on 16/9/9.
 */
public class PermissionCode {
    public static final int READ_PHONE_STATE = 0x01;
    public static final int READ_EXTERNAL_STORAGE = 0x02;
    public static final int ACCESS_FINE_LOCATION = 0x03;

    public static boolean isSettingRequest(int code) {
        return code == READ_PHONE_STATE || code == READ_EXTERNAL_STORAGE || code == ACCESS_FINE_LOCATION;
    }
}
