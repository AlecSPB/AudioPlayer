package com.tc.audioplayer.event;

import com.tc.model.entity.CollectSong;

/**
 * Created by itcayman on 2017/9/11.
 */

public class CollectEvent {
    public CollectSong collectSong;

    public CollectEvent(CollectSong collectSong) {
        this.collectSong = collectSong;
    }
}
