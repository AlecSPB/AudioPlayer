package com.tc.model.usecase;

import com.tc.base.utils.TLogger;
import com.tc.model.api.BMA;
import com.tc.model.api.OnlineAPI;
import com.tc.model.entity.AlbumDetailEntity;
import com.tc.model.entity.AlbumList;
import com.tc.model.entity.ArtistEntity;
import com.tc.model.entity.ArtistList;
import com.tc.model.entity.ArtistSongList;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.HotSearch;
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

    public Observable<SongList> getMusicList(boolean refresh, int type) {
        String url = BMA.Billboard.billSongList(type, 0, PAGE_SIZE);
        TLogger.d(TAG, "getMusicList: " + url);
        return api.requestOnlineMusicList(url, refresh);
    }

    public Observable<SongDetail> getMusicInfo(String songid) {
        String url = BMA.Song.songInfo(songid);
        TLogger.d(TAG, "getMusicInfo: " + url);
        return api.requestOnlineMusicInfo(url, false);
    }

    public Observable<ResponseBody> getMusicLrc(String lrclink) {
        TLogger.d(TAG, "getMusicLrc: " + lrclink);
        return api.requestLrc(lrclink, false);
    }

    public Observable<SearchWrapper> getSearch(String query) {
        String url = BMA.Search.searchMerge(query, 1, PAGE_SIZE);
        TLogger.d(TAG, "getSearch: " + url);
        return api.requestSearch(url, false);
    }

    public Observable<List<HotSearch>> getHotSearch() {
        String url = BMA.Search.hotWord();
        TLogger.d(TAG, "getHotSearch " + url);
        return api.requestHotSearch(url, false);
    }

    public Observable<String> getGedanCategory(boolean refresh) {
        String url = BMA.GeDan.geDanCategory();
        TLogger.d(TAG, "getGedanCategory: " + url);
        return api.requestGedanCatetory(url, refresh);
    }

    public Observable<String> getGedanByTag(boolean refresh, String tag, int pageno) {
        String url = BMA.GeDan.geDanByTag(tag, pageno, 10);
        TLogger.d(TAG, "geGedanByTag: " + url);
        return api.requestGeDanByTag(url, refresh);
    }

    public Observable<String> getGeDan(boolean refresh, int pageNo, int pageSize) {
        String url = BMA.GeDan.geDan(pageNo, pageSize);
        TLogger.d(TAG, "geDan: " + url);
        return api.requestGedan(url, refresh);
    }

    public Observable<List<BillboardEntity>> getBillboardCategory(boolean refresh) {
        String url = BMA.Billboard.billCategory();
        TLogger.d(TAG, "getBillboardCategory: " + url);
        return api.requestBillboardCategory(url, refresh);
    }

    public Observable<SongEntity> getSongInfo(String songid) {
        String url = BMA.Song.songInfo(songid);
        TLogger.d(TAG, "getSongBaseInfo: " + url);
        return api.requestSongInfo(url, false);
    }

    public Observable<AlbumList> getAlbumnList(boolean refresh, int page) {
        page = page >= 1 ? page - 1 : 0;
        int limit = 50;
        String url = BMA.Album.recommendAlbum(limit * page, limit);
        TLogger.d(TAG, "getAlbumnList: " + url);
        return api.requestAlbumnList(url, refresh);
    }

    public Observable<AlbumDetailEntity> getAlbumnDetail(boolean refresh, String albumnid) {
        String url = BMA.Album.albumInfo(albumnid);
        TLogger.d(TAG, "getAlbumnDetail: " + url);
        return api.requetAlbumnDetail(url, refresh);
    }

    public Observable<ArtistList> getArtistList(boolean refresh, int page) {
        page = page >= 1 ? page - 1 : 0;
        int limit = 50;
        String url = BMA.Artist.artistList(limit * page, limit, 6, 0, 0, "a-z");
        TLogger.d(TAG, "getArtistList: " + url);
        return api.requestArtistList(url,refresh);
    }

    public Observable<ArtistEntity> getArtistDetail(boolean refresh, String tinguid, String artistid) {
        String url = BMA.Artist.artistInfo(tinguid, artistid);
        TLogger.d(TAG, "getArtistDetail: " + url);
        return api.requestArtistDetail(url, refresh);
    }

    public Observable<ArtistSongList> getArtistSongList(boolean refresh, String tinguid, String artistid, int page) {
        page = page >= 1 ? page - 1 : 0;
        int limit = 50;
        String url = BMA.Artist.artistSongList(tinguid, artistid, limit * page, limit);
        TLogger.d(TAG, "getArtistSongList");
        return api.requestArtistSongList(url, refresh);
    }
}
