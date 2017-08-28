package com.tc.audioplayer.player;


import com.tc.model.entity.SongEntity;

/**
 * Created by tianchao on 2017/8/10.
 */

public interface IPlayer {
    PlayList getPlayList();

    void appendPlayList(PlayList playList);

    boolean play();

    boolean play(PlayList list);

    boolean play(PlayList list, int startIndex);

    boolean play(SongEntity song);

    boolean append(SongEntity song);

    boolean playPrev();

    boolean playNext();

    boolean pause();

    boolean isPlaying();

    int getProgress();

    SongEntity getPlayingSong();

    boolean seekTo(int progress);

    void setPlayMode(@Player.PlayMode int playMode);

    @Player.PlayMode
    int getPlayMode();

    void setPlayState(@Player.PlayState int playState);

    @Player.PlayState
    int getPlayState();

    void addPlayerListener(PlayerListener listener);

    void removePlayerListener(PlayerListener listener);
}

