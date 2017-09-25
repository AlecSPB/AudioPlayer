package com.tc.audioplayer.bussiness.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.model.entity.AlbumnDetailEntity;

/**
 * Created by itcayman on 2017/9/25.
 */

public class AlbumnDetailActivity extends ToolbarActivity {
    private String albumnid;
    private AlbumnDetailPresenter presenter;
    private AlbumnDetailAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base);

        albumnid = getIntent().getStringExtra("albumnid");

        flToolbarContent.setVisibility(View.VISIBLE);

        presenter = new AlbumnDetailPresenter(albumnid);
        presenter.attachView(this);
        adapter = new AlbumnDetailAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadData(true);
        });

        presenter.loadData(false);
    }

    @Override
    public void setData(Object data) {
        AlbumnDetailEntity detailEntity = (AlbumnDetailEntity) data;
        adapter.setData(detailEntity.songlist);
    }

}
