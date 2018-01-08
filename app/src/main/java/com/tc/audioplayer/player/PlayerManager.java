package com.tc.audioplayer.player;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tc.audioplayer.AudioApplication;
import com.tc.audioplayer.R;
import com.tc.base.utils.CollectionUtil;
import com.tc.base.utils.TLogger;
import com.tc.model.db.DBManager;
import com.tc.model.db.greendao.CommonEntityDao;
import com.tc.model.entity.CommonEntity;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by itcayman on 2017/8/18.
 */

public class PlayerManager {
    private static final String TAG = PlayerManager.class.getSimpleName();
    private static PlayerManager instance;
    private Context context;
    private IPlayer player;
    private Gson gson;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * 注册播放器
     */
    public void registerPlayer(IPlayer iPlayer) {
        TLogger.d(TAG, "registerPlayer " + iPlayer);
        this.player = iPlayer;
        addPlayListener(new SimplePlayerListener(){
            @Override
            public void onBufferingError() {
                Toast.makeText(context, context.getString(R.string.error_buffering), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int errorCode) {
                Toast.makeText(context, context.getString(R.string.error_load_music_info, errorCode),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PlayerManager() {
        context = AudioApplication.getInstance();
        gson = new Gson();
    }

    public int getSeektoDuration() {
        return player.getSeekToDuration();
    }

    public PlayList getPlayList() {
        if (player == null) {
            return new PlayList();
        }
        PlayList playList = player.getPlayList();
        if (playList == null || CollectionUtil.isEmpty(playList.getSongList())) {
            PlayList dbPlaylist = getPlayListFromDB();
            if (dbPlaylist != null) {
                TLogger.d(TAG, "getPlayList: playindex=" + dbPlaylist.getPlayingIndex()
                        + " listsize=" + dbPlaylist.getSongList().size()
                        + " duration=" + dbPlaylist.getCurrentDuration()
                        + " playmode=" + dbPlaylist.getPlayMode());
                player.appendPlayList(dbPlaylist, dbPlaylist.getPlayingIndex(), dbPlaylist.getCurrentDuration());
                player.setPlayMode(dbPlaylist.getPlayMode());
                return dbPlaylist;
            }
        }
        return playList;
    }

    /**
     * 追加PlayList数据
     */
    public void appendPlayList(PlayList playList) {
        player.appendPlayList(playList);
        updatePlaylistToDB();
    }

    public void addPlayListener(PlayerListener listener) {
        if (player != null) {
            player.addPlayerListener(listener);
        }
    }

    public void removePlayListener(PlayerListener listener) {
        if (player != null) {
            player.removePlayerListener(listener);
        }
    }

    public void play(PlayList playList, int index) {
        player.play(playList, index);
        updatePlaylistToDB();
        startPlayerAction(PlayActions.MEDIA_PLAY_PAUSE);
    }

    public void play(SongEntity songEntity) {
        player.play(songEntity);
        updatePlaylistToDB();
        startPlayerAction(PlayActions.MEDIA_PLAY_PAUSE);
    }

    public void playPause() {
        TLogger.d(TAG, "playPause");
        if (player.getPlayList() == null || player.getPlayList().getPlayingIndex() < 0)
            return;
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.play();
        }
        updatePlaylistToDB();
    }

    public void playPrev() {
        player.playPrev();
    }

    public void playNext() {
        player.playNext();
    }

    public void seekTo(int progress) {
        player.seekTo(progress);
    }

    @PlayList.PlayMode
    public int switchNextMode() {
        return player.switchNextMode(player.getPlayMode());
    }

    @PlayList.PlayMode
    public int getPlayMode() {
        return player.getPlayMode();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void close(Context context) {
        player.pause();
    }

    public int getProgress() {
        return player.getProgress();
    }

    public void setVolume(float volume) {
        player.setVolume(volume);
    }

    public void startPlayService() {
        TLogger.d(TAG, "startPlayService");
        startPlayerAction("");
    }

    /**
     * 启动service
     */
    private void startPlayerAction(String action) {
        TLogger.d(TAG, "startPlayerAction: action=" + action);
        Intent intent = new Intent(context, PlayService.class);
        intent.setAction(action);
        context.startService(intent);
    }

    /**
     * 启动service ，当前index
     */
    private void startPlayerAction(String action, int index) {
        TLogger.d(TAG, "startPlayerAction: action=" + action + " index=" + index);
        Intent intent = new Intent(context, PlayService.class);
        intent.setAction(action);
        intent.putExtra("index", index);
        context.startService(intent);
    }

    public void updatePlaylistToDB() {
        try {
            CommonEntity commonEntity = getCommonEntityFromDB();
            PlayList playList = player.getPlayList();
            String content = gson.toJson(playList);
            TLogger.d(TAG, "updatePlaylistToDB: index=" + playList.getPlayingIndex()
                    + " duration=" + playList.getCurrentDuration());
            if (commonEntity != null) {
                commonEntity.content = content;
                DBManager.getInstance(AudioApplication.getInstance()).getDaoSession()
                        .getCommonEntityDao().update(commonEntity);
            } else {
                CommonEntity temp = new CommonEntity();
                temp.content = content;
                temp.setType(PlayList.class.getSimpleName());
                DBManager.getInstance(AudioApplication.getInstance()).getDaoSession()
                        .getCommonEntityDao().insert(temp);
            }
        } catch (Exception e) {
            TLogger.e(TAG, "updatePlaylistToDB exception: " + e.toString());
        }
    }

    private PlayList getPlayListFromDB() {
        CommonEntity commonEntity = getCommonEntityFromDB();
        if (commonEntity == null) {
            return null;
        }
        return gson.fromJson(commonEntity.getContent(), PlayList.class);
    }

    private CommonEntity getCommonEntityFromDB() {
        QueryBuilder queryBuilder = DBManager.getInstance(AudioApplication.getInstance())
                .getDaoSession().getCommonEntityDao().queryBuilder();
        WhereCondition whereCondition = new WhereCondition.PropertyCondition(
                CommonEntityDao.Properties.Type, "=" + "\"" + PlayList.class.getSimpleName() + "\"");
        queryBuilder.where(whereCondition);
        List<CommonEntity> commonEntities = queryBuilder.build().list();
        if (CollectionUtil.isEmpty(commonEntities))
            return null;
        return commonEntities.get(0);
    }
}
