package com.tc.audioplayer.bussiness;

import android.content.Context;
import android.widget.Toast;

import com.tc.audioplayer.AudioApplication;
import com.tc.audioplayer.event.CollectEvent;
import com.tc.base.utils.CollectionUtil;
import com.tc.model.db.DBManager;
import com.tc.model.db.greendao.CollectSongDao;
import com.tc.model.db.greendao.SongEntityDao;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.CollectSong;
import com.tc.model.entity.SongEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by itcayman on 2017/9/27.
 */

public class FavHelper {

    public static boolean favSong(Context context, SongEntity songEntity) {
        if (songEntity == null) {
            Toast.makeText(context, "收藏失败!", Toast.LENGTH_SHORT).show();
            return false;
        }
        CollectSong collectSong = new CollectSong();
        collectSong.setSongid(songEntity.getSong_id());
        collectSong.setSong(songEntity);
        CollectSongDao dao = DBManager.getInstance(AudioApplication.getInstance())
                .getDaoSession().getCollectSongDao();
        SongEntityDao songEntityDao = DBManager.getInstance(AudioApplication.getInstance())
                .getDaoSession().getSongEntityDao();
        List<CollectSong> list = dao.queryBuilder()
                .where(CollectSongDao.Properties.Song_id.eq(songEntity.song_id))
                .build()
                .list();
        if (CollectionUtil.isEmpty(list)) {
            if (songEntityDao.getKey(songEntity) == null) {
                songEntityDao.insert(songEntity);
            }
            dao.insert(collectSong);
            EventBus.getDefault().post(new CollectEvent(collectSong));
            Toast.makeText(context, "收藏成功!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean unfavSong(SongEntity songEntity) {
        if (songEntity == null) {
            return false;
        }
        CollectSong collectSong = new CollectSong();
        collectSong.setSongid(songEntity.getSong_id());
        collectSong.setSong(songEntity);
        CollectSongDao dao = DBManager.getInstance(AudioApplication.getInstance())
                .getDaoSession().getCollectSongDao();
        try {
            dao.queryBuilder()
                    .where(CollectSongDao.Properties.Song_id.eq(songEntity.song_id))
                    .buildDelete()
                    .executeDeleteWithoutDetachingEntities();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public static boolean isFav(SongEntity songEntity) {
        CollectSong collectSong = new CollectSong();
        collectSong.setSongid(songEntity.getSong_id());
        collectSong.setSong(songEntity);
        CollectSongDao dao = DBManager.getInstance(AudioApplication.getInstance())
                .getDaoSession().getCollectSongDao();
        List<CollectSong> list = dao.queryBuilder()
                .where(CollectSongDao.Properties.Song_id.eq(songEntity.song_id))
                .build()
                .list();
        if (!CollectionUtil.isEmpty(list)) {
            return true;
        }
        return false;
    }

    public static boolean isFav(BillboardEntity.ContentBean contentBean) {
        SongEntity songEntity = revert(contentBean);
        return isFav(songEntity);
    }

    public static SongEntity revert(BillboardEntity.ContentBean contentBean) {
        SongEntity songEntity = new SongEntity();
        songEntity.setAuthor(contentBean.author);
        songEntity.setSong_id(contentBean.song_id);
        songEntity.setTitle(contentBean.title);
        songEntity.setAlbum_id(contentBean.album_id);
        songEntity.setAlbum_title(contentBean.album_title);
        return songEntity;
    }
}
