package com.tc.audioplayer.oline.music;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.View;

import com.tc.audioplayer.base.BaseListFragment;
import com.tc.librecyclerview.adapter.QuickRcAdapter;
import com.tc.model.entity.SongListEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tianchao on 2017/8/2.
 * 热歌
 */

public class MusicFragment extends BaseListFragment {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HOT, NEW})
    public @interface LIST_TYPE {
    }

    public static final int HOT = 2;//热歌
    public static final int NEW = 1;//新曲

    private @LIST_TYPE int listType;


    public static MusicFragment newInstance(@LIST_TYPE int listType) {
        MusicFragment instance = new MusicFragment();
        instance.listType = listType;
        return instance;
    }

    private MusicPresenter presenter;
    private MusicAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadData(true));
        swipeRefreshLayout.setRefreshing(true);

        presenter = new MusicPresenter(listType);
        presenter.attachView(this);
        adapter = new MusicAdapter(getContext());
        recyclerView.setAdapter(adapter);
        presenter.loadData(false);
        adapter.setOnItemClickListener(new QuickRcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
    }

    @Override
    public void setData(Object object) {
        super.setData(object);
        SongListEntity data = (SongListEntity) object;
        adapter.setData(data.song_list);
    }

}
