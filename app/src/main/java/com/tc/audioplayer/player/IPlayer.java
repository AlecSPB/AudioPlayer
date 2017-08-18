package com.tc.audioplayer.player;


import com.tc.model.entity.SongListItemEntity;

/**
 * Created by tianchao on 2017/8/10.
 */

public interface IPlayer {
    void setPlayList(PlayList playList);

    boolean play();

    boolean play(PlayList list);

    boolean play(PlayList list, int startIndex);

    boolean play(SongListItemEntity song);

    boolean playLast();

    boolean playNext();

    boolean pause();

    boolean isPlaying();

    int getProgress();

    SongListItemEntity getPlayingSong();

    boolean seekTo(int progress);

    void setPlayMode(@Player.PlayMode int playMode);
}

