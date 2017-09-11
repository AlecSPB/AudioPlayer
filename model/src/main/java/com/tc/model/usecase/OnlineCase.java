package com.tc.model.usecase;

import com.tc.base.utils.TLogger;
import com.tc.model.api.BMA;
import com.tc.model.api.OnlineAPI;
import com.tc.model.entity.SearchWrapper;
import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongList;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by tianchao on 2017/8/3.
 */

public class OnlineCase extends BaseCase<OnlineAPI> {
    private static final int PAGE_SIZE = 30;
    private static final String TAG = OnlineCase.class.getSimpleName();

    public Observable<SongList> getMusicList(int type) {
        String url = BMA.Billboard.billSongList(type, 0, PAGE_SIZE);
        TLogger.d(TAG, "getMusicList: " + url);
        return api.requestOnlineMusicList(url);
    }

    public Observable<SongDetail> getMusicInfo(String songid) {
        String url = BMA.Song.songInfo(songid);
        TLogger.d(TAG, "getMusicInfo: " + url);
        return api.requestOnlineMusicInfo(url);
    }

    public Observable<ResponseBody> getMusicLrc(String lrclink) {
        TLogger.d(TAG, "getMusicLrc: " + lrclink);
        return api.requestLrc(lrclink);
    }

    public Observable<SearchWrapper> getSearch(String query) {
        String url = BMA.Search.searchMerge(query, 1, PAGE_SIZE);
        TLogger.d(TAG, "getSearch: " + url);
        return api.requestSearch(url);
    }
}
