package com.tc.model.api;

import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongList;

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
}
