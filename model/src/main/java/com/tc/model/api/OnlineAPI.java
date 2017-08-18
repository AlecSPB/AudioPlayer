package com.tc.model.api;

import com.tc.model.entity.SongListEntity;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by tianchao on 2017/8/3.
 */

public interface OnlineAPI {
    @GET
    Observable<SongListEntity> requestOnlineMusicList(@Url String url);
}
