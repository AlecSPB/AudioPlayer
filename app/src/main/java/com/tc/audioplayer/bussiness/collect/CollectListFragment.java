package com.tc.audioplayer.bussiness.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tc.audioplayer.AudioApplication;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.event.CollectEvent;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.base.utils.CollectionUtil;
import com.tc.model.db.DBManager;
import com.tc.model.db.greendao.CollectSongDao;
import com.tc.model.entity.CollectSong;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianchao on 2017/8/2.
 * 收藏列表
 */

public class CollectListFragment extends BaseListFragment {

    public static CollectListFragment newInstance() {
        CollectListFragment instance = new CollectListFragment();
        return instance;
    }

    private List<CollectSong> collectSongs;
    private CollectAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CollectAdapter(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadData();
        });
        adapter.setOnItemClickListener((v, position) -> {
            PlayList playList = new PlayList();
            List<SongEntity> songEntities = revert(adapter.getData());
            if (!CollectionUtil.isEmpty(songEntities)) {
                playList.addSongList(songEntities);
                PlayerManager.getInstance().play(playList, position);
            }
        });
        loadData();
    }

    private void loadData() {
        CollectSongDao dao = DBManager.getInstance(AudioApplication.getInstance())
                .getDaoSession().getCollectSongDao();
        collectSongs = dao.loadAll();
        adapter.setData(collectSongs);
        swipeRefreshLayout.setRefreshing(false);
    }

    private List<SongEntity> revert(List<CollectSong> collectSongs) {
        if (CollectionUtil.isEmpty(collectSongs))
            return null;
        List<SongEntity> songEntities = new ArrayList<>();
        for (int i = 0; i < collectSongs.size(); i++) {
            songEntities.add(collectSongs.get(i).getSong());
        }
        return songEntities;
    }

    @Override
    protected int configDefaultRigsterFlags() {
        return EventBusRegisterFlags.NEED_DEFAULT_REGISTER;
    }

    @Subscribe
    public void onEventMainThread(CollectEvent event) {
        if (collectSongs == null) {
            collectSongs = new ArrayList<>();
        }
        collectSongs.add(0, event.collectSong);
        adapter.setData(collectSongs);
    }
}
