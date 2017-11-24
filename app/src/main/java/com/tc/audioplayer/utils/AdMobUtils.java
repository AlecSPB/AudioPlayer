package com.tc.audioplayer.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.AudioApplication;
import com.tc.audioplayer.R;
import com.tc.audioplayer.utils.shell.CommandResult;
import com.tc.audioplayer.utils.shell.ShellUtils;
import com.tc.base.utils.TLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itcayman on 2017/9/27.
 */

public class AdMobUtils {
    private static final String TAG = AdMobUtils.class.getSimpleName();
    private static final String ADPAGE_NAMEW = "com.google.android.gms.ads.AdActivity";
    private static final String COMMAND_RESULT_FORMAT = "resultCode:%s,successMsg:%s,errorMsg:%s\n";
    private static final String CMD_CHMOD777 = "chmod 777 ls";
    private static final String CMD_LS = "ls";
    private static final String CMD_FOCUSED_ACTIVITY = "dumpsys activity | grep mFocusedActivity";
    private static final String CMD_PS = "ps";

    public static void activityLoop() {
        new Thread(() -> {
            TLogger.d(TAG, "activityLoop start");
            while (true) {
                ActivityManager manager = (ActivityManager) AudioApplication.getInstance()
                        .getSystemService(Context.ACTIVITY_SERVICE);
//            List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
//            for (int i = 0; i < processes.size(); i++) {
//                ActivityManager.RunningAppProcessInfo processInfo = processes.get(i);
//                TLogger.d(TAG, "process: " + processInfo.processName + " " + processInfo.pid);
//                String[] pkgList = processInfo.pkgList;
//                for (int j = 0; j < pkgList.length; j++) {
//                    TLogger.d(TAG, "pkg:" + pkgList[i]);
//                }
//            }

//                List<ActivityManager.AppTask> appTasks = manager.getAppTasks();
//                for (int i = 0; i < appTasks.size(); i++) {
//                    ActivityManager.AppTask appTask = appTasks.get(i);
//                    ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
//                    TLogger.d(TAG, "appTask: origActivity=" + taskInfo.origActivity + " "
//                            + " numActivities=" + taskInfo.numActivities + " description="
//                            + taskInfo.description + " topActivity=" + taskInfo.topActivity);
//                }

//                List<ActivityManager.RunningTaskInfo> infoList = manager.getRunningTasks(10);
//                for (int i = 0; i < infoList.size(); i++) {
//                    ActivityManager.RunningTaskInfo info = infoList.get(i);
//                    String shortClassName = info.topActivity.getShortClassName();    //类名
//                    String className = info.topActivity.getClassName();              //完整类名
//                    String packageName = info.topActivity.getPackageName();          //包名
//                    TLogger.d(TAG, "runningtask: shortClassName=" + shortClassName + " className="
//                            + className + " packageName: " + packageName);
//                }

//                List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);
//                ActivityManager.RunningTaskInfo info = list.get(0);
//                ComponentName componentName = info.topActivity;
//                String packageName = componentName.getPackageName();
//                String className = componentName.getClassName();
//                String shortName = componentName.getShortClassName();
//                TLogger.d(TAG, "top: packageName=" + packageName + " className="
//                        + className + " shortName: " + shortName);


                executeCmd();

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void executeCmd() {
        List<String> commands = new ArrayList<>();
//        commands.add(CMD_CHMOD777);
//        TLogger.d(TAG, "execute cmd: " + CMD_CHMOD777);
//        commands.add(CMD_LS);
//        commands.add(CMD_FOCUSED_ACTIVITY);
//        TLogger.d(TAG, "execute cmd: " + CMD_FOCUSED_ACTIVITY);
        commands.add(CMD_PS);
        TLogger.d(TAG, "execute cmd: " + CMD_PS);
        CommandResult result = ShellUtils.execCommand(commands, false, true);
        final String strResult = String.format(COMMAND_RESULT_FORMAT, result.result, result.successMsg, result.errorMsg);
        TLogger.d(TAG, "cmd result=" + strResult);
    }



    /**
     * Populates a {@link NativeContentAdView} object with data from a given
     * {@link NativeContentAd}.
     *
     * @param nativeContentAd the object containing the ad's assets
     * @param adView          the view to be populated
     */
    public static void populateContentAdView(NativeContentAd nativeContentAd,
                                       NativeContentAdView adView) {
        // Assign native ad object to the native view.
        adView.setNativeAd(nativeContentAd);

        adView.setHeadlineView(adView.findViewById(R.id.contentad_headline));
        adView.setImageView(adView.findViewById(R.id.contentad_image));

        // Some assets are guaranteed to be in every NativeContentAd.
        ((TextView) adView.getHeadlineView()).setText(nativeContentAd.getHeadline());

        List<NativeAd.Image> images = nativeContentAd.getImages();

        if (images.size() > 0) {
            ((ImageView) adView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        }
    }
}
