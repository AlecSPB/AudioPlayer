package com.tc.audioplayer.bussiness.fav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.bussiness.artist.ArtistDetailAdapter;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tianchao on 2017/10/31.
 */

public class FavListActivity extends ToolbarActivity {
    private ArtistDetailAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base);
        setContentUnderToolbar();
        minibar.setAutoVisibility(true);
        minibar.bindData();
        setToolbarCenterTitle(getString(R.string.title_fav));

        adapter = new ArtistDetailAdapter(this);
        adapter.setFavVisibility(false);
        swipeRefreshLayout.setOnRefreshListener(() -> loadData());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> {
            List<SongEntity> data = adapter.getData();
            PlayList playList = new PlayList();
            playList.addSongList(data);
            PlayerManager.getInstance().play(playList, position);
        });

        loadData();
    }

    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        Observable<List<SongEntity>> observable = Observable.create(subscriber -> {
            List<SongEntity> songEntities = FavHelper.getFavList();
            subscriber.onNext(songEntities);
            subscriber.onCompleted();
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((data) -> {
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.setData(data);
                });
    }
}
