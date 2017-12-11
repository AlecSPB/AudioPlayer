package com.tc.audioplayer.bussiness.billboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.base.Constant;
import com.tc.audioplayer.event.CollectEvent;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.AdMobUtils;
import com.tc.base.utils.CollectionUtil;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/9/21.
 */

public class BillboardListFragment extends BaseListFragment {
    private static final String TAG = BillboardListFragment.class.getSimpleName();
    private BillboardListPresenter presenter;
    private BillboardListAdapter adapter;
    private boolean hasAdShown;
    private NativeContentAdView nativeAdView;

    public static BillboardListFragment newInstance() {
        BillboardListFragment instance = new BillboardListFragment();
        return instance;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new BillboardListAdapter(getContext());
        presenter = new BillboardListPresenter();
        presenter.attachView(this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadData(true));
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadData(false);
        adapter.setOnItemClickListener((v, position) -> {
            Object object = adapter.getItem(position);
            if (object instanceof BillboardEntity.ContentBean) {
                BillboardEntity.ContentBean item = (BillboardEntity.ContentBean) adapter.getItem(position);
                SongEntity songEntity = new SongEntity();
                songEntity.song_id = item.song_id;
                songEntity.song_source = "";
                List<SongEntity> songList = presenter.getPlayList();
                int index = presenter.getCurrentIndex(songEntity, songList);
                PlayList playList = new PlayList();
                playList.addSongList(songList);
                PlayerManager.getInstance().play(playList, index);
            } else if (object instanceof BillboardEntity) {
                BillboardEntity billboard = (BillboardEntity) object;
                Navigator.toBillboardDetailActivity(getContext(), billboard.type, billboard.name);
            }
        });

        AdMobUtils.loadNativeContentAd(getContext(), Constant.AdmobNativeID_Billboard,
                new NativeContentAd.OnContentAdLoadedListener() {
                    @Override
                    public void onContentAdLoaded(NativeContentAd contentAd) {
                        TLogger.e(TAG, "onContentAdLoaded: " + contentAd.getBody());
                        addAdAfterNewBillboard(contentAd);
                    }
                });
    }

    /**
     * 在新歌榜后面加广告
     */
    private void addAdAfterNewBillboard(NativeContentAd contentAd) {
        List<Object> data = adapter.getData();
        int index = 0;
        int count = 0;
        if (!CollectionUtil.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                Object item = data.get(i);
                if (item instanceof String && count < 1) {
                    count++;
                    index = i;
                }
            }
        }
        data.add(index + 1, contentAd);
        adapter.notifyDataSetChanged();
    }

    /**
     * 加载底部广告
     */
    private void loadBottomAd() {
        AdMobUtils.loadNativeContentAd(getContext(), Constant.AdmobNativeID, new NativeContentAd.OnContentAdLoadedListener() {
            @Override
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                TLogger.e(TAG, "onContentAdLoaded");
                NativeContentAdView view = (NativeContentAdView) LayoutInflater.from(getContext()).inflate(R.layout.ad_hot, recyclerView, false);
                adapter.addFooterView(view);
                AdMobUtils.showNativeContentAd(getContext(), view);
            }
        });
    }


    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.loadData(true);
    }

    private Action1 getOnPlayAction() {
        Action1 onNext = (data) -> {
            if (isAdded()) {
                TLogger.e(TAG, "getOnPlayAction: ", data.toString());
                SongEntity entity = (SongEntity) data;
                PlayerManager.getInstance().play(entity);
            }
        };
        return onNext;
    }

    private Action1 getOnPlayErrorAction() {
        Action1<Throwable> onError = (throwable) -> {
            if (isAdded()) {
                TLogger.e(TAG, "getOnPlayErrorAction: ", throwable.toString());
                Toast.makeText(getContext(), getString(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        };
        return onError;
    }


    @Override
    public void setData(Object data) {
        super.setData(data);
        List<Object> list = (ArrayList<Object>) data;
        adapter.setData(list);
        if (!hasAdShown) {
            AdMobUtils.showHomeBigAd(getContext());
            hasAdShown = true;
        }
        loadBottomAd();
    }

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
