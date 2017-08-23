package com.tc.audioplayer.player;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by itcayman on 2017/8/20.
 */

public class PlayerUtils {

    public static void notifyListeners(List<PlayerListener> listenerList, Action1 action) {
        if (listenerList == null)
            return;
        for (int i = 0; i < listenerList.size(); i++) {
            PlayerListener listener = listenerList.get(i);
            listener.onPause();
        }

    }
}
