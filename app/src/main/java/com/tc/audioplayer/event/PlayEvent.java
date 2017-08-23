package com.tc.audioplayer.event;

import com.tc.audioplayer.player.Player;
import com.tc.model.entity.SongDetail;

/**
 * Created by itcayman on 2017/8/19.
 */

public class PlayEvent {
    public SongDetail songDetail;
    @Player.PlayState
    public int playState;

    public PlayEvent(SongDetail songDetail, @Player.PlayState int playState) {
        this.songDetail = songDetail;
        this.playState = playState;
    }
}
