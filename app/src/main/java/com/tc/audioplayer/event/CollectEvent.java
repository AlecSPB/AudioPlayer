package com.tc.audioplayer.event;

import com.tc.model.entity.CollectSong;

/**
 * Created by itcayman on 2017/9/11.
 */

public class CollectEvent {
    public CollectSong collectSong;
    public boolean isFav;

    public CollectEvent(CollectSong collectSong, boolean isFav) {
        this.collectSong = collectSong;
        this.isFav = isFav;
    }
}
