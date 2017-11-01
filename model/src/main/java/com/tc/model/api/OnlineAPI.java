package com.tc.model.api;

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
import com.tc.model.net.APIServiceProvider;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by tianchao on 2017/8/3.
 */

public interface OnlineAPI {
    @GET
    Observable<SongList> requestOnlineMusicList(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<SongDetail> requestOnlineMusicInfo(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<ResponseBody> requestLrc(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<SearchWrapper> requestSearch(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<List<HotSearch>> requestHotSearch(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<String> requestGedanCatetory(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<String> requestGeDanByTag(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<String> requestGedan(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<List<BillboardEntity>> requestBillboardCategory(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<SongEntity> requestSongBaseInfo(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<SongEntity> requestSongInfo(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<AlbumList> requestAlbumnList(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<AlbumDetailEntity> requetAlbumnDetail(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<ArtistList> requestArtistList(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<ArtistEntity> requestArtistDetail(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);

    @GET
    Observable<ArtistSongList> requestArtistSongList(@Url String url, @Header(APIServiceProvider.IS_FRESH) boolean refresh);
}
