package com.tc.audioplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.tc.audioplayer.AudioApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by itcayman on 2017/9/7.
 */

public class InputMethodUtil {
    /**
     * 显示键盘
     *
     * @param editor
     */
    public static void show(final EditText editor) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) editor.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editor, 0);
            }
        }, 500);
    }

    /**
     * 隐藏键盘
     *
     * @param editor
     */
    public static void hidden(EditText editor) {
        InputMethodManager inputMethodManager = (InputMethodManager) editor.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editor.getWindowToken(), 0);
    }

    /**
     * 强制隐藏键盘
     *
     * @param activity
     */
    public static void forceHidden(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public static void forceHidden(Window window) {
        if (window == null) {
            return;
        }
        View view = window.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) AudioApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}
