package com.tc.audioplayer.utils;

/**
 * Created by itcayman on 2017/8/23.
 */

public class AudioDurationUtil {

    /**
     * 时间日期转换
     *
     * @param seconds
     * @return
     */
    public static String secondsToString(int seconds) {
        String timeStr;
        int hour;
        int minute;
        int second;
        if (seconds <= 0)
            return "00:00";
        else {
            minute = seconds / 60;
            if (minute < 60) {
                second = seconds % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second) + "";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";

                minute = minute % 60;
                second = seconds - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second) + "";
            }
        }
        return timeStr;
    }

    /**
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "NaN";

                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分钟" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    /**
     * @param time
     * @return
     */
    public static String getTimeForFenMiao(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "NaN";

                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    /**
     * @param time
     * @return
     */
    public static String secToSecond(int time) {
        String timeStr;
        int hour;
        int minute;
        int second;

        if (time <= 0)
            return "";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                if (minute == 0)
                    timeStr = unitFormat(second) + "秒";
                else
                    timeStr = unitFormat(minute) + "分钟";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "NaN";

                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;

                if (hour == 0 && minute == 0)
                    timeStr = unitFormat(second) + "秒";
                else {
                    timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分钟";
                }
            }
        }
        return timeStr;
    }

    /**
     * unit格式化时间
     *
     * @param i
     * @return
     */
    private static String unitFormat(int i) {
        String retStr;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
