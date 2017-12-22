package com.tc.audioplayer.bussiness.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.base.Constant;
import com.tc.audioplayer.event.CollectEvent;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.AdMobUtils;
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
    private boolean hasAddTopAd;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadData(true);
            presenter.loadAlbum(true, onLoadHeaderSuccess, onLoadHeaderFail);
            if (adapter.getHeaderCount() == 1) {
                loadAd();
            }
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
        loadAd();
        addHotAlbumHeader();
        presenter.loadData(false);
        presenter.loadAlbum(false, onLoadHeaderSuccess, onLoadHeaderFail);
    }

    private void loadAd() {
        if (hasAddTopAd) {
            return;
        }
        AdMobUtils.loadNativeAd(getContext(), Constant.AdmobNativeID,
                (nativeAppInstallAd) -> {
                    if (hasAddTopAd || getContext() == null) {
                        return;
                    }
                    TLogger.e(TAG, "onAppInstallAdLoaded");
                    NativeAppInstallAdView view = (NativeAppInstallAdView) LayoutInflater
                            .from(getContext())
                            .inflate(R.layout.ad_native_app_install, recyclerView, false);
                    adapter.addHeaderView(0, view);
                    AdMobUtils.populateInstallAdView(nativeAppInstallAd, view);
                    hasAddTopAd = true;
                }
                ,
                (nativeContentAd) -> {
                    if (hasAddTopAd || getContext() == null) {
                        return;
                    }
                    TLogger.e(TAG, "onContentAdLoaded");
                    NativeContentAdView view = (NativeContentAdView) LayoutInflater
                            .from(getContext())
                            .inflate(R.layout.ad_native_content, recyclerView, false);
                    adapter.addHeaderView(0, view);
                    AdMobUtils.populateContentAdView(nativeContentAd, view, false);
                    hasAddTopAd = true;
                });
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
