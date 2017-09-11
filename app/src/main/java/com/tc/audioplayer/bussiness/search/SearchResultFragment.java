package com.tc.audioplayer.bussiness.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.model.entity.PlayList;

/**
 * Created by itcayman on 2017/9/5.
 */

public class SearchResultFragment extends BaseListFragment {
    private SearchPresent present;
    private SearchResultAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        present = new SearchPresent();
        adapter = new SearchResultAdapter(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        present.attachView(this);
        recyclerView.addDefaultDivider();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> {
            PlayList playList = new PlayList();
            playList.addSongList(adapter.getData());
            PlayerManager.getInstance().play(playList, position);
        });
    }

    public void search(String key) {
        if (present == null)
            return;
        present.search(key, (result) -> {
            adapter.setData(result.result.song_info.song_list);
        });
    }
}
