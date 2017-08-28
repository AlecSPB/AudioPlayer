package com.tc.model.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by tianchao on 2017/8/5.
 */

public class SongList {
    public List<SongEntity> song_list;
    public BillboardEntity billboard;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
