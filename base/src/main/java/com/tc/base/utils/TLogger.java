package com.tc.base.utils;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by tianchao on 2017/8/5.
 */

public class TLogger {
    public static final String TAG = "TLogger";
    private static boolean isEnable = true;

    static {
        //初始化Logger
        if (isEnable) {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(2)         // (Optional) How many method line to show. Default 2
                    .methodOffset(5)        // (Optional) Skips some method invokes in stack trace. Default 5
                    .tag(TAG)               // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                    .build();

            com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
            com.orhanobut.logger.Logger.addLogAdapter(new DiskLogAdapter());
        }
    }

    private TLogger() {
    }

    public static void setEnable(boolean enable){
        isEnable = enable;
    }

    public static boolean isEnable() {
        return isEnable;
    }

    /**
     * 打印任意非String对象
     *
     * @param object
     */
    public static void v(Object object) {
        if (!isEnable) return;

        if (object == null) {
            com.orhanobut.logger.Logger.v("empty object!");
            return;
        }

        com.orhanobut.logger.Logger.d(object);
    }

    /**
     * 打印格式化字符串
     *
     * @param message
     * @param args
     */
    public static void v(String message, Object... args) {
        if (!isEnable) return;

        com.orhanobut.logger.Logger.v(message, args);
    }

    /**
     * 指定自定义tag，打印格式化字符串
     *
     * @param tag
     * @param message
     * @param args
     */
    public static void v(String tag, String message, Object... args) {
        if (!isEnable) return;

        if (tag == null) {
            v(message, args);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).v(message, args);
    }


    /**
     * 打印任意非String对象
     *
     * @param object
     */
    public static void d(Object object) {
        if (!isEnable) return;

        if (object == null) {
            com.orhanobut.logger.Logger.d("empty object!");
            return;
        }

        com.orhanobut.logger.Logger.d(object);
    }

    /**
     * 打印格式化字符串
     *
     * @param message
     * @param args
     */
    public static void d(String message, Object... args) {
        if (!isEnable) return;

        com.orhanobut.logger.Logger.d(message, args);
    }


    /**
     * 指定自定义tag，打印格式化字符串
     *
     * @param tag
     * @param message
     * @param args
     */
    public static void d(String tag, String message, Object... args) {
        if (!isEnable) return;

        if (tag == null) {
            d(message, args);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).d(message, args);
    }

    /**
     * 打印格式化字符串
     *
     * @param message
     * @param args
     */
    public static void i(String message, Object... args) {
        if (!isEnable) return;

        com.orhanobut.logger.Logger.i(message, args);
    }

    /**
     * 指定自定义tag，打印格式化字符串
     *
     * @param tag
     * @param message
     * @param args
     */
    public static void i(String tag, String message, Object... args) {
        if (!isEnable) return;

        if (tag == null) {
            i(message, args);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).i(message, args);
    }

    /**
     * 打印格式化字符串
     *
     * @param message
     * @param args
     */
    public static void w(String message, Object... args) {
        if (!isEnable) return;

        com.orhanobut.logger.Logger.w(message, args);
    }

    /**
     * 指定自定义tag，打印格式化字符串
     *
     * @param tag
     * @param message
     * @param args
     */
    public static void w(String tag, String message, Object... args) {
        if (!isEnable) return;

        if (tag == null) {
            w(message, args);
            return;
        }


        com.orhanobut.logger.Logger.t(tag).w(message, args);
    }

    /**
     * 打印格式化字符串
     *
     * @param message
     * @param args
     */
    public static void e(String message, Object... args) {
        if (!isEnable) return;

        com.orhanobut.logger.Logger.e(message, args);
    }

    /**
     * 指定自定义tag，打印格式化字符串
     *
     * @param tag
     * @param message
     * @param args
     */
    public static void e(String tag, String message, Object... args) {
        if (!isEnable) return;

        if (tag == null) {
            e(message, args);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).e(message, args);
    }

    /**
     * 打印格式化字符串
     *
     * @param message
     * @param args
     */
    public static void e(Throwable tr, String message, Object... args) {
        if (!isEnable) return;

        com.orhanobut.logger.Logger.e(tr, message, args);
    }

    /**
     * 指定自定义tag，打印格式化字符串
     *
     * @param tag
     * @param message
     * @param args
     */
    public static void e(String tag, Throwable tr, String message, Object... args) {
        if (!isEnable) return;

        if (tag == null) {
            e(tr, message, args);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).e(tr, message, args);
    }

    // TODO: 2017/7/12 将错误记录到服务器
    public static void reportError(Throwable tr, String message, Object... args) {
    }

    /**
     * 可视化打印json内容
     *
     * @param json
     */
    public static void json(String json) {
        if (!isEnable) return;

        if (TextUtils.isEmpty(json)) {
            d("empty json!");
            return;
        }

        com.orhanobut.logger.Logger.json(json);
    }

    /**
     * 自定义tag，可视化打印json内容
     *
     * @param tag
     * @param json
     */
    public static void json(String tag, String json) {
        if (!isEnable) return;

        if (tag == null) {
            json(json);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).json(json);
    }

    /**
     * 可视化打印xml内容
     *
     * @param xml
     */
    public static void xml(String xml) {
        if (!isEnable) return;

        if (TextUtils.isEmpty(xml)) {
            d("empty xml!");
            return;
        }

        com.orhanobut.logger.Logger.xml(xml);
    }

    /**
     * 自定义tag，可视化打印xml内容
     *
     * @param tag
     * @param xml
     */
    public static void xml(String tag, String xml) {
        if (!isEnable) return;

        if (tag == null) {
            xml(xml);
            return;
        }

        com.orhanobut.logger.Logger.t(tag).xml(xml);
    }
}