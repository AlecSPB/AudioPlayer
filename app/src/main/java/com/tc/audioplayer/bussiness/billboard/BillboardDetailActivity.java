package com.tc.audioplayer.bussiness.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.Constant;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.bussiness.artist.ArtistDetailAdapter;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.AdMobUtils;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;
import com.tc.model.entity.SongList;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/10/30.
 */

public class BillboardDetailActivity extends ToolbarActivity {
    private static final String TAG = BillboardDetailActivity.class.getSimpleName();
    private BillboardListPresenter presenter;
    private ArtistDetailAdapter adapter;
    private RecyclerView recyclerView;
    private List<SongEntity> sourceData;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base);
        setContentUnderToolbar();
        minibar.setAutoVisibility(true);
        minibar.bindData();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        type = intent.getIntExtra("type", 0);

        presenter = new BillboardListPresenter();
        presenter.attachView(this);
        adapter = new ArtistDetailAdapter(this);

        setToolbarCenterTitle(title);
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadBillboardList(false, type, onNext);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position)->{
            PlayList playList = new PlayList();
            playList.addSongList(sourceData);
            int index = sourceData.indexOf(adapter.getItem(position));
            PlayerManager.getInstance().play(playList, index);
        });
    }

    private Action1 onNext = new Action1() {
        @Override
        public void call(Object o) {
            swipeRefreshLayout.setRefreshing(false);
            tvRetry.setVisibility(View.GONE);
            SongList songList = (SongList) o;
            sourceData = songList.song_list;
            List<Object> data = new ArrayList<>();
            data.addAll(sourceData);
            adapter.setData(data);
            loadAd();
            if (songList.song_list != null && songList.song_list.size() > 20) {
                AdMobUtils.loadNativeContentAd(BillboardDetailActivity.this, Constant.AdmobNativeID, new NativeContentAd.OnContentAdLoadedListener() {
                    @Override
                    public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                        TLogger.e(TAG, "onContentAdLoaded");
                        List<Object> result = adapter.getData();
                        result.add(14, nativeContentAd);
                        adapter.setData(result);
                    }
                });
            }
        }
    };


    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.loadBillboardList(true, type, onNext);
    }

    private void loadAd() {
        AdMobUtils.loadNativeContentAd(this, Constant.AdmobNativeID, new NativeContentAd.OnContentAdLoadedListener() {
            @Override
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                TLogger.e(TAG, "onContentAdLoaded");
                NativeContentAdView view = (NativeContentAdView) LayoutInflater
                        .from(BillboardDetailActivity.this)
                        .inflate(R.layout.ad_hot, recyclerView, false);
                adapter.addHeaderView(0, view);
                AdMobUtils.showNativeContentAd(BillboardDetailActivity.this, view);
            }
        });
    }

}
