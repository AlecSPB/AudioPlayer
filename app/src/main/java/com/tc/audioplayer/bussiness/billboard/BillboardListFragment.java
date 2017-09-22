package com.tc.audioplayer.bussiness.billboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.SongEntity;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/9/21.
 */

public class BillboardListFragment extends BaseListFragment {
    private static final String TAG = BillboardListFragment.class.getSimpleName();
    private BillboardPresenter presenter;
    private BillboardAdapter adapter;

    public static BillboardListFragment newInstance() {
        BillboardListFragment instance = new BillboardListFragment();
        return instance;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new BillboardAdapter(getContext());
        presenter = new BillboardPresenter();
        presenter.attachView(this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadData(true));
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadData(false);
        adapter.setOnItemClickListener((v, position) -> {
            BillboardEntity.ContentBean item = (BillboardEntity.ContentBean) adapter.getItem(position);
//            presenter.loadSongInfo(item.song_id, getOnPlayAction(), getOnPlayErrorAction());
            SongEntity songEntity = new SongEntity();
            songEntity.song_id = item.song_id;
            songEntity.song_source = "";
            PlayerManager.getInstance().play(songEntity);
        });
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
    }
}
