package com.tc.audioplayer.bussiness.billboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.base.Constant;
import com.tc.audioplayer.event.CollectEvent;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.BillboardEntity;
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
//            presenter.loadSongInfo(item.song_id, getOnPlayAction(), getOnPlayErrorAction());
                SongEntity songEntity = new SongEntity();
                songEntity.song_id = item.song_id;
                songEntity.song_source = "";
                PlayerManager.getInstance().play(songEntity);
            } else if (object instanceof BillboardEntity) {
                BillboardEntity billboard = (BillboardEntity) object;
                Navigator.toBillboardDetailActivity(getContext(), billboard.type, billboard.name);
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
            InterstitialAd mInterstitialAd = new InterstitialAd(getContext());
            mInterstitialAd.setAdUnitId(Constant.AdmobInterID);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("EFDE3632F6D6F87801F68CAB10796A46")
                    .addTestDevice("DA6DE3BE84FC5D12846B2AD377CED73E")
                    .addTestDevice("126101F178936B6BA282A3EB81EF29F0")
                    .addTestDevice("FE3E29B85E2D0BC813D0AF1A53390C44")
                    .addTestDevice("B2952405032D73534E695FE8897CC4B1")
                    .addTestDevice("C357783CA84A3BDEAE79C5801DD2A323")
                    .addTestDevice("40a940a6200eff90bd875a6b1df06c70")
                    .addTestDevice("40A940A6200EFF90BD875A6B1DF06C70")
                    .build();
            mInterstitialAd.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener() {
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
                    mInterstitialAd.show();
                    TLogger.d(TAG, "onAdLoaded");
                }
            });
            hasAdShown = true;
        }
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
