package com.tc.audioplayer.player;

import com.tc.model.entity.PlayList;

/**
 * 播放引擎状态监听器
 * Created by itcayman on 2017/8/16.
 */

public interface PlayerListener {
    void onInit(PlayList currentPlaylist);

    void currentPlaylist(PlayList currentPlaylist);

    void onPreparingStart();//音频处于准备状态

    void onPreparingEnd();//音频准备状态结束

    void onBufferingUpdate(int percent);//音频缓冲进度

    void onBufferingError();

    void onPlay();//当前音频开始播放

    void onPause();//当前音频暂停

    void onResume();

    void onStop();//当前音频停止

    void onCompletion(); //当前音频播放结束

    void isPlay(boolean isPlaying);//当前音频播放状态

    void onListEnd();//音频列表播放结束

    void onProgress(boolean isPlaying, int progress, int duration, int secondProgress);//音频播放进度

    void onError(int errorCode);//音频播放错误

    void onLast(); //最后一首

    void onFirst(); //第一首
}
