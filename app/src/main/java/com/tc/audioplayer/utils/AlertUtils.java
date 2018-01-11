package com.tc.audioplayer.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.tc.audioplayer.widget.alertview.AlertBuilder;
import com.tc.audioplayer.widget.alertview.Effectstype;

/**
 * Created by itcayman on 2018/01/11.
 * <p>
 * AlertDialog
 */
public class AlertUtils {

    public interface AlertListener {
        void ok();

        void cancel();
    }

    public static class SimpleAlertListener implements AlertListener{
        @Override
        public void ok() {

        }

        @Override
        public void cancel() {

        }
    }

    public static AlertListener mAlertListener;

    /**
     * 无取消按钮alert
     *
     * @param context
     * @param title
     * @param msg
     * @param okBtnName
     * @param alertListener
     */
    private static void showSingleBtnAlert(Context context, String title, String msg, String okBtnName, AlertListener alertListener) {
        mAlertListener = alertListener;
        final AlertBuilder removeDialogBuilder = AlertBuilder.getInstance(context);
        removeDialogBuilder.withTitle("" + title)
                .withMessage("" + msg)
                .withDuration(300)
                .withEffect(Effectstype.SlideBottom)
                .withOkButtonText(okBtnName + "")
                .setOnOkButtonClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAlertListener != null) {
                            mAlertListener.ok();
                        }
                        removeDialogBuilder.cancel();
                    }
                }).show();
    }

    /**
     * @param context
     * @param title
     * @param msg
     * @param okBtnName
     * @param cancelBtnName 为 "" 时则为singleAlert
     * @param alertListener
     */
    public static void showAlert(Context context, String title, String msg, String okBtnName, String cancelBtnName, AlertListener alertListener) {
        if (TextUtils.isEmpty(cancelBtnName)) {
            showSingleBtnAlert(context, title, msg, okBtnName, alertListener);
            return;
        }

        mAlertListener = alertListener;
        final AlertBuilder removeDialogBuilder = AlertBuilder.getInstance(context);
        removeDialogBuilder.withTitle("" + title)
                .withMessage("" + msg)
                .withDuration(300)
                .withEffect(Effectstype.SlideBottom)
                .withOkButtonText(okBtnName + "")
                .withCancelButtonText(cancelBtnName + "")
                .setOnOkButtonClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAlertListener != null) {
                            mAlertListener.ok();
                        }
                        removeDialogBuilder.cancel();

                    }
                }).setOnCacnelButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAlertListener != null) {
                    mAlertListener.cancel();
                }
                removeDialogBuilder.cancel();
            }
        }).show();
    }

}
