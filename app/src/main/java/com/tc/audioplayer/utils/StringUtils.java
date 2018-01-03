package com.tc.audioplayer.utils;

/**
 * Created by itcayman on 2017/10/23.
 */

public class StringUtils {
    public static String replaceEm(String str) {
        if(str == null){
            return "";
        }
        str = str.replaceAll("<em>", "");
        str = str.replaceAll("</em>", "");
        return str;
    }
}
