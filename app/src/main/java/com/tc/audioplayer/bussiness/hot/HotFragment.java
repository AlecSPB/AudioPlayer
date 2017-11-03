package com.tc.audioplayer.bussiness.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.event.CollectEvent;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.DimenUtils;
import com.tc.base.utils.TLogger;
import com.tc.librecyclerview.LinearRecyclerView;
import com.tc.model.entity.Album;
import com.tc.model.entity.AlbumList;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongList;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.functions.Action1;

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
    private View layoutAlbum;
    private LinearRecyclerView rcyAlbum;
    private HeaderAlbumnAdapter albumnAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadData(true);
            presenter.loadAlbum(true, onLoadHeaderSuccess, onLoadHeaderFail);
        });
        swipeRefreshLayout.setRefreshing(true);

        presenter = new HotPresenter();
        presenter.attachView(this);
        adapter = new HotAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addDefaultDivider();
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
                adView.setVisibility(View.VISIBLE);
                TLogger.d(TAG, "onAdLoaded");
            }
        });
        adView.setVisibility(View.GONE);
        adapter.addHeaderView(adView);
        addHotAlbumHeader();
        presenter.loadData(false);
        presenter.loadAlbum(false, onLoadHeaderSuccess, onLoadHeaderFail);
    }

    private void addHotAlbumHeader() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.header_hot, recyclerView, false);
        layoutAlbum = view.findViewById(R.id.ll_albumn);
        rcyAlbum = (LinearRecyclerView) view.findViewById(R.id.rc_albumn);
        albumnAdapter = new HeaderAlbumnAdapter(getContext());
        rcyAlbum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcyAlbum.setAdapter(albumnAdapter);
        albumnAdapter.setOnItemClickListener((v, position) -> {
            Album albumn = albumnAdapter.getItem(position);
            Navigator.toAlbumnDetailActivity(getContext(), albumn.album_id);
        });
        adapter.addHeaderView(view);
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
        presenter.loadAlbum(true, onLoadHeaderSuccess, onLoadHeaderFail);
    }

    private Action1 onLoadHeaderSuccess = (data) -> {
        if (layoutAlbum != null) {
            layoutAlbum.setVisibility(View.VISIBLE);
            AlbumList listWrapp = (AlbumList) data;
            albumnAdapter.setData(listWrapp.list);
        }
    };

    private Action1 onLoadHeaderFail = (throwable) -> {
        if (layoutAlbum != null) {
            layoutAlbum.setVisibility(View.GONE);
        }
    };

    @Override
    protected int configDefaultRigsterFlags() {
        return EventBusRegisterFlags.NEED_DEFAULT_REGISTER;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleCollectEvent(CollectEvent event) {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
