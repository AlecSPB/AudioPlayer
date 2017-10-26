package com.tc.audioplayer.player;

import android.media.MediaPlayer;

import com.tc.base.utils.TLogger;

import java.util.List;

/**
 * Created by itcayman on 2017/8/19.
 */

public class MediaListener implements MediaPlayer.OnInfoListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener {

    private static final String TAG = MediaListener.class.getSimpleName();
    private Player player;

    public MediaListener(Player player) {
        this.player = player;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        TLogger.d(TAG, "onInfo: what=" + what + " extra=" + extra);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        TLogger.d(TAG, "onPrepared");
        mp.start();
        player.resetProgressa();
        player.onPrepared();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        TLogger.d(TAG, "onBufferingUpdate: percent=" + percent);
        List<PlayerListener> listenerList = player.playerListeners;
        for (int i = 0; i < listenerList.size(); i++) {
            PlayerListener listener = listenerList.get(i);
            listener.onBufferingUpdate(percent);
        }
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        TLogger.d(TAG, "onSeekComplete: position=" + mp.getCurrentPosition());
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        TLogger.d(TAG, "onCompletion");
        player.onCompletion();
        List<PlayerListener> listenerList = player.playerListeners;
        for (int i = 0; i < listenerList.size(); i++) {
            PlayerListener listener = listenerList.get(i);
            listener.onCompletion();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        TLogger.d(TAG, "onError: what=" + what + " extra=" + extra);
        return false;
    }
}
