package com.tc.model.usecase;

import com.tc.base.utils.TLogger;
import com.tc.model.api.BMA;
import com.tc.model.api.OnlineAPI;
import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongList;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by tianchao on 2017/8/3.
 */

public class OnlineCase extends BaseCase<OnlineAPI> {
    public Observable<SongList> getMusicList(int type) {
        String url = BMA.Billboard.billSongList(type, 0, 20);
        TLogger.e("getMusicList: " + url);
        return api.requestOnlineMusicList(url);
    }

    public Observable<SongDetail> getMusicInfo(String songid) {
        String url = BMA.Song.songInfo(songid);
        TLogger.e("getMusicInfo: " + url);
        return api.requestOnlineMusicInfo(url);
    }

    public Observable<ResponseBody> getMusicLrc(String lrclink){
        TLogger.e("getMusicLrc: " + lrclink);
        return api.requestLrc(lrclink);
    }
}
