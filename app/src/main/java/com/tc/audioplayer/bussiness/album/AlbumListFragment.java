package com.tc.audioplayer.bussiness.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.model.entity.Album;
import com.tc.model.entity.AlbumList;

/**
 * Created by itcayman on 2017/9/21.
 */

public class AlbumListFragment extends BaseListFragment {
    public static AlbumListFragment newInstance() {
        AlbumListFragment fragment = new AlbumListFragment();
        return fragment;
    }

    private AlbumnPresenter presenter;
    private AlbumnAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AlbumnPresenter();
        adapter = new AlbumnAdapter(getContext());
        presenter.attachView(this);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadData(true);
        });
        presenter.loadData(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> {
            Album albumn = adapter.getItem(position);
            Navigator.toAlbumnDetailActivity(getContext(), albumn.album_id);
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
        AlbumList listWrapp = (AlbumList) data;
        adapter.setData(listWrapp.list);
    }
}
