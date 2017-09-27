package com.tc.audioplayer.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.tc.audioplayer.AudioApplication;
import com.tc.audioplayer.utils.shell.CommandResult;
import com.tc.audioplayer.utils.shell.ShellUtils;
import com.tc.base.utils.TLogger;

/**
 * Created by itcayman on 2017/9/27.
 */

public class AdMobUtils {
    private static final String TAG = AdMobUtils.class.getSimpleName();
    private static final String ADPAGE_NAMEW = "com.google.android.gms.ads.AdActivity";
    private static final String COMMAND_RESULT_FORMAT = "resultCode:%s,successMsg:%s,errorMsg:%s\n";
    private static final String CMD_FOCUSED_ACTIVITY = "adb shell dumpsys activity | grep mFocusedActivity";

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

                executeCmd(CMD_FOCUSED_ACTIVITY);

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void executeCmd(String cmd) {
        TLogger.d(TAG, "execute cmd: " + cmd);
        CommandResult result = ShellUtils.execCommand(CMD_FOCUSED_ACTIVITY, true, true);
        final String strResult = String.format(COMMAND_RESULT_FORMAT, result.result, result.successMsg, result.errorMsg);
        TLogger.d(TAG, "cmd result=" + strResult);
    }
}
