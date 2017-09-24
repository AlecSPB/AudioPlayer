package com.tc.model.usecase;

import com.tc.base.utils.TLogger;
import com.tc.model.api.BMA;
import com.tc.model.api.OnlineAPI;
import com.tc.model.entity.AlbumnList;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.SearchWrapper;
import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongEntity;
import com.tc.model.entity.SongList;

import java.util.List;

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

    public Observable<String> getGedanCategory() {
        String url = BMA.GeDan.geDanCategory();
        TLogger.d(TAG, "getGedanCategory: " + url);
        return api.requestGedanCatetory(url);
    }

    public Observable<String> getGedanByTag(String tag, int pageno) {
        String url = BMA.GeDan.geDanByTag(tag, pageno, 10);
        TLogger.d(TAG, "geGedanByTag: " + url);
        return api.requestGeDanByTag(url);
    }

    public Observable<String> getGeDan(int pageNo, int pageSize) {
        String url = BMA.GeDan.geDan(pageNo, pageSize);
        TLogger.d(TAG, "geDan: " + url);
        return api.requestGedan(url);
    }

    public Observable<List<BillboardEntity>> getBillboardCategory() {
        String url = BMA.Billboard.billCategory();
        TLogger.d(TAG, "getBillboardCategory: " + url);
        return api.requestBillboardCategory(url);
    }

    public Observable<SongEntity> getSongInfo(String songid) {
        String url = BMA.Song.songInfo(songid);
        TLogger.d(TAG, "getSongBaseInfo: " + url);
        return api.requestSongInfo(url);
    }

    public Observable<AlbumnList> getAlbumnList(int page) {
        page = page >= 1 ? page - 1 : 0;
        int limit = 20;
        String url = BMA.Album.recommendAlbum(limit * page, limit);
        TLogger.d(TAG, "getAlbumnList: " + url);
        return api.requestAlbumnList(url);
    }

}
