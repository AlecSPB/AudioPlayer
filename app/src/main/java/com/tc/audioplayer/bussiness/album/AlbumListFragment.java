package com.tc.audioplayer.bussiness.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.model.entity.Albumn;
import com.tc.model.entity.AlbumnList;

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
            Albumn albumn = adapter.getItem(position);
            Navigator.toAlbumnDetailActivity(getContext(), albumn.album_id);
        });
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
        AlbumnList listWrapp = (AlbumnList) data;
        adapter.setData(listWrapp.list);
    }
}
