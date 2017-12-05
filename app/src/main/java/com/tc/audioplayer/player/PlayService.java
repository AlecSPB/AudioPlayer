package com.tc.audioplayer.player;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tc.base.utils.TLogger;

/**
 * Created by tianchao on 2017/8/6.
 */

public class PlayService extends Service {
    private static final String TAG = PlayService.class.getSimpleName();
    public static final int STATE_IDLE = 0;
    public static final int STATE_PREPARING = 1;
    public static final int STATE_PLAYING = 2;
    public static final int STATE_PAUSE = 3;
    private IPlayer player;
    private int mPlayState = STATE_IDLE;

    @Override
    public void onCreate() {
        super.onCreate();
        TLogger.d(TAG, "PlayService onCreate");
        player = Player.getInstance();
        PlayerManager.getInstance().registerPlayer(player);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            TLogger.d(TAG, "PlayService onStartCommand: flags=" + flags + " startId=" + startId
                    + " action=" + intent.getAction());
            switch (intent.getAction()) {
                case PlayActions.MEDIA_PLAY_PAUSE:
                    playPause();
                    break;
                case PlayActions.MEDIA_PREV:
                    prev();
                    break;
                case PlayActions.MEDIA_NEXT:
                    next();
                    break;
                case PlayActions.MEDIA_SKIP:
                    break;
                default:
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playPause() {
        player.play();
    }

    private void start() {

    }

    private void prev() {

    }

    private void next() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }

    public class PlayBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }
}
