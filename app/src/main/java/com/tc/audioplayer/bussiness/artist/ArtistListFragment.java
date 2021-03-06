package com.tc.audioplayer.bussiness.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.model.entity.ArtistEntity;
import com.tc.model.entity.ArtistList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itcayman on 2017/9/21.
 */

public class ArtistListFragment extends BaseListFragment {
    public static ArtistListFragment newInstance() {
        ArtistListFragment fragment = new ArtistListFragment();
        return fragment;
    }

    private ArtistPresenter presenter;
    private ArtistAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ArtistPresenter();
        adapter = new ArtistAdapter(getContext());
        presenter.attachView(this);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadData(true);
        });
        presenter.loadData(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> {
            if (adapter.getItem(position) instanceof ArtistEntity) {
                ArtistEntity artist = (ArtistEntity) adapter.getItem(position);
                Navigator.toArtistDetailActivity(getContext(), artist.ting_uid, artist.artist_id,
                        artist.name, artist.avatar_big, artist.country);
            }
        });
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.loadData(true);
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
        ArtistList listWrapp = (ArtistList) data;
        List<Object> dataWithAd = new ArrayList<>();
        dataWithAd.addAll(listWrapp.artist);
        adapter.setData(dataWithAd);
    }
}
