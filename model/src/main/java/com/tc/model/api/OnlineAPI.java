package com.tc.model.api;

import com.tc.model.entity.AlbumDetailEntity;
import com.tc.model.entity.AlbumList;
import com.tc.model.entity.ArtistEntity;
import com.tc.model.entity.ArtistList;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.SearchWrapper;
import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongEntity;
import com.tc.model.entity.SongList;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by tianchao on 2017/8/3.
 */

public interface OnlineAPI {
    @GET
    Observable<SongList> requestOnlineMusicList(@Url String url);

    @GET
    Observable<SongDetail> requestOnlineMusicInfo(@Url String url);

    @GET
    Observable<ResponseBody> requestLrc(@Url String url);

    @GET
    Observable<SearchWrapper> requestSearch(@Url String url);

    @GET
    Observable<String> requestGedanCatetory(@Url String url);

    @GET
    Observable<String> requestGeDanByTag(@Url String url);

    @GET
    Observable<String> requestGedan(@Url String url);

    @GET
    Observable<List<BillboardEntity>> requestBillboardCategory(@Url String url);

    @GET
    Observable<SongEntity> requestSongBaseInfo(@Url String url);

    @GET
    Observable<SongEntity> requestSongInfo(@Url String url);

    @GET
    Observable<AlbumList> requestAlbumnList(@Url String url);

    @GET
    Observable<AlbumDetailEntity> requetAlbumnDetail(@Url String url);

    @GET
    Observable<ArtistList> requestArtistList(@Url String url);

    @GET
    Observable<ArtistEntity> requestArtistDetail(@Url String url);
}
