package com.tc.audioplayer.bussiness.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.DimenUtils;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongList;

/**
 * Created by tianchao on 2017/8/2.
 * 热歌
 */

public class HotFragment extends BaseListFragment {
    private static final String TAG = HotFragment.class.getSimpleName();

    private HotPresenter presenter;

    public static HotFragment newInstance() {
        HotFragment instance = new HotFragment();
        return instance;
    }

    private HotAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadData(true));
        swipeRefreshLayout.setRefreshing(true);

        presenter = new HotPresenter();
        presenter.attachView(this);
        adapter = new HotAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addDefaultDivider();
        presenter.loadData(false);
        adapter.setOnItemClickListener((v, position) -> {
            PlayList playList = new PlayList();
            playList.addSongList(adapter.getData());
            PlayerManager.getInstance().play(playList, position);
        });
        AdView adView = new AdView(getContext());
        AdSize adSize = new AdSize(AdSize.FULL_WIDTH, DimenUtils.dp2px(getContext(), 50));
        adView.setAdSize(adSize);
        adView.setAdUnitId("ca-app-pub-7199806726993025/1864479462");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                TLogger.d(TAG, "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                TLogger.d(TAG, "onAdFailedToLoad: " + i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                TLogger.d(TAG, "onAdLeftApplication");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                TLogger.d(TAG, "onAdOpened");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                TLogger.d(TAG, "onAdLoaded");
            }
        });
        adapter.addHeaderView(adView);
    }

    @Override
    public void setData(Object object) {
        super.setData(object);
        SongList data = (SongList) object;
        adapter.setData(data.song_list);
    }

    @Override
    protected void onRefresh() {
        presenter.loadData(false);
    }
}
