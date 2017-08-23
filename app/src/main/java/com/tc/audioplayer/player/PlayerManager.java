package com.tc.audioplayer.player;

import android.content.Context;
import android.content.Intent;

import com.tc.audioplayer.AudioApplication;
import com.tc.base.utils.TLogger;

/**
 * Created by itcayman on 2017/8/18.
 */

public class PlayerManager {
    private static final String TAG = PlayerManager.class.getSimpleName();
    private static PlayerManager instance;
    private Context context;
    private IPlayer player;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * 注册播放器
     */
    public void registerPlayer(IPlayer iPlayer) {
        TLogger.d(TAG, "registerPlayer " + iPlayer);
        this.player = iPlayer;
    }

    private PlayerManager() {
        context = AudioApplication.getInstance();
    }

    public PlayList getPlayList() {
        return player.getPlayList();
    }

    /**
     * 追加PlayList数据
     */
    public void appendPlayList(PlayList playList) {
        player.appendPlayList(playList);
    }

    public void addPlayListener(PlayerListener listener) {
        player.addPlayerListener(listener);
    }

    public void removePlayListener(PlayerListener listener) {
        player.removePlayerListener(listener);
    }

    public void play(PlayList playList, int index) {
        player.play(playList, index);
        startPlayerAction(PlayActions.MEDIA_PLAY_PAUSE);
    }

    public void playPause() {
        if (player.getPlayList() == null || player.getPlayList().getPlayingIndex() < 0)
            return;
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.play();
        }
    }

    public void playPrev() {
        player.playPrev();
    }

    public void playNext() {
        player.playNext();
    }

    public void seekTo(int progress) {
        player.seekTo(progress);
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public int getProgress(){
        return player.getProgress();
    }

    public void startPlayService() {
        TLogger.d(TAG, "startPlayService");
        startPlayerAction("");
    }

    /**
     * 启动service
     */
    private void startPlayerAction(String action) {
        TLogger.d(TAG, "startPlayerAction: action=" + action);
        Intent intent = new Intent(context, PlayService.class);
        intent.setAction(action);
        context.startService(intent);
    }

    /**
     * 启动service ，当前index
     */
    private void startPlayerAction(String action, int index) {
        TLogger.d(TAG, "startPlayerAction: action=" + action + " index=" + index);
        Intent intent = new Intent(context, PlayService.class);
        intent.setAction(action);
        intent.putExtra("index", index);
        context.startService(intent);
    }
}
