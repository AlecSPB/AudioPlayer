package com.tc.audioplayer.bussiness.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.bussiness.artist.ArtistDetailAdapter;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;
import com.tc.model.entity.SongList;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/10/30.
 */

public class BillboardDetailActivity extends ToolbarActivity {
    private BillboardListPresenter presenter;
    private ArtistDetailAdapter adapter;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base);
        setContentUnderToolbar();
        minibar.setVisibility(View.VISIBLE);
        minibar.bindData();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        type = intent.getIntExtra("type", 0);

        presenter = new BillboardListPresenter();
        presenter.attachView(this);
        adapter = new ArtistDetailAdapter(this);
        swipeRefreshLayout.setOnRefreshListener(()->{
            presenter.loadBillboardList(type, onNext);
        });

        setToolbarCenterTitle(title);
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadBillboardList(type, onNext);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position)->{
            List<SongEntity> data = adapter.getData();
            PlayList playList = new PlayList();
            playList.addSongList(data);
            PlayerManager.getInstance().play(playList, position);
        });
    }

    private Action1 onNext = new Action1() {
        @Override
        public void call(Object o) {
            swipeRefreshLayout.setRefreshing(false);
            SongList songList = (SongList) o;
            adapter.setData(songList.song_list);
        }
    };


}
