package com.tc.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * User: tianchao
 * Date: 16/4/6
 * Time: 上午10:45
 * PS: 学如逆水行舟，不进则退
 * <p>
 * 针对低版本系统的API做的一些兼容
 */
public class CompatibleUtils {

    public static void setBackground(Context context, View view, int resourceId) {
        if (context == null || view == null)
            return;
        Drawable drawable = resourceId > 0 ? context.getResources().getDrawable(resourceId) : null;
        setBackground(view, drawable);
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setHasTransientState(View view, boolean hasTransientState) {
        if (view == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setHasTransientState(hasTransientState);
        }
    }

    /**
     * 在2.3以上使用新的接口，减少文件写入操作
     *
     * @param editor
     */
    public static void apply(SharedPreferences.Editor editor) {
        if (editor == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void removeGlobalLayoutListener(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener layoutListener) {
        if (observer == null || layoutListener == null)
            return;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            observer.removeGlobalOnLayoutListener(layoutListener);
        } else {
            observer.removeOnGlobalLayoutListener(layoutListener);
        }
    }

    public static void setHomeAsUpIndicator(ActionBar actionBar, int resId) {
        if (actionBar == null)
            return;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            actionBar.setHomeAsUpIndicator(resId);
        }
    }

    /**
     * 在4.1版本之前, 当这里传递的资源ID是另一个Drawable资源的别名, 则该函数不能正确地获取到最终配置density。
     * 即别名资源（alias resource）的density配置不同于实际资源的, 返回的Drawable对象的density将不正确, 这样缩放时将出错。
     */
    public static Drawable getDrawable(Context context, int resId) {
        if (context == null)
            return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resId, context.getTheme());
        } else {
            return context.getResources().getDrawable(resId);
        }
    }

    public static boolean isDestroyed(AppCompatActivity activity) {
        if (activity == null)
            return true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return activity.isDestroyed();
        } else {
            return activity.getSupportFragmentManager().isDestroyed();
        }
    }
}
