package com.tc.audioplayer.player;

import android.media.MediaPlayer;
import android.support.annotation.IntDef;
import android.util.Log;

import com.tc.model.entity.SongListItemEntity;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianchao on 2017/8/10.
 */

public class Player implements IPlayer, MediaPlayer.OnCompletionListener{
    private static final String TAG = "Player";
    private static volatile Player sInstance;

    private MediaPlayer mPlayer;
    private PlayList mPlayList;
    private List<PlayerListener> mCallbacks = new ArrayList<>(2);
    // Player status
    private boolean isPaused;

    public static final int SINGLE = 0;//单曲
    public static final int LOOP = 1;//循环
    public static final int LIST = 2;//列表
    public static final int SHUFFLE = 3;//随机

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SINGLE, LOOP, LIST, SHUFFLE})
    public @interface PlayMode {

    }

    public static
    @PlayMode
    int getDefault() {
        return LOOP;
    }

    public static
    @PlayMode
    int switchNextMode(@PlayMode int current) {
        switch (current) {
            case LOOP:
                return LIST;
            case LIST:
                return SHUFFLE;
            case SHUFFLE:
                return SINGLE;
            case SINGLE:
                return LOOP;
        }
        return getDefault();
    }


    private Player() {
        mPlayer = new MediaPlayer();
        mPlayList = new PlayList();
        mPlayer.setOnCompletionListener(this);
    }

    public static Player getInstance() {
        if (sInstance == null) {
            synchronized (Player.class) {
                if (sInstance == null) {
                    sInstance = new Player();
                }
            }
        }
        return sInstance;
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void setPlayList(PlayList playList) {
        if(playList == null){
            playList = new PlayList();
        }
        this.mPlayList = playList;
    }

    @Override
    public boolean play() {
        if (isPaused) {
            mPlayer.start();
            notifyPlayStatusChanged(true);
            return true;
        }
        if (mPlayList.prepare()) {
            SongListItemEntity song = mPlayList.getCurrentSong();
            try {
                mPlayer.reset();
                mPlayer.setDataSource(song.getPath());
                mPlayer.prepare();
                mPlayer.start();
                notifyPlayStatusChanged(true);
            } catch (IOException e) {
                Log.e(TAG, "play: ", e);
                notifyPlayStatusChanged(false);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean play(PlayList list) {
        return false;
    }

    @Override
    public boolean play(PlayList list, int startIndex) {
        return false;
    }

    @Override
    public boolean play(SongListItemEntity song) {
        return false;
    }

    @Override
    public boolean playLast() {
        return false;
    }

    @Override
    public boolean playNext() {
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public SongListItemEntity getPlayingSong() {
        return null;
    }

    @Override
    public boolean seekTo(int progress) {
        return false;
    }

    @Override
    public void setPlayMode(@PlayMode int playMode) {

    }

    private void notifyPlayStatusChanged(boolean isPlaying) {
        for (PlayerListener callback : mCallbacks) {
            callback.isPlay(isPlaying);
        }
    }

    private void notifyPlayLast(PlayList song) {
        for (PlayerListener callback : mCallbacks) {
            callback.currentPlaylist(song);
        }
    }

    private void notifyPlayNext(SongListItemEntity song) {
//        for (PlayerListener callback : mCallbacks) {
//            callback.(song);
//        }
    }

    private void notifyComplete(SongListItemEntity song) {
        for (PlayerListener callback : mCallbacks) {
//            callback.onCompletion(song);
        }
    }
}
