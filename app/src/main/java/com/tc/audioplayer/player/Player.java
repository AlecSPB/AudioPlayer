package com.tc.audioplayer.player;

import android.media.MediaPlayer;
import android.support.annotation.IntDef;

import com.tc.base.utils.TLogger;
import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongEntity;
import com.tc.model.usecase.OnlineCase;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tianchao on 2017/8/10.
 */

public class Player implements IPlayer {
    private static final String TAG = "Player";
    private static volatile Player sInstance;

    public static final int SINGLE = 0;//单曲
    public static final int LOOP = 1;//循环
    public static final int LIST = 2;//列表
    public static final int SHUFFLE = 3;//随机

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SINGLE, LOOP, LIST, SHUFFLE})
    public @interface PlayMode {

    }

    public static final int DEFAULT = -1;
    public static final int INIT = 0;          //初始化
    public static final int PLAY_IDLE = 1;          // setDataSource            Idle等待中
    public static final int PLAY_BUFFERING = 2;     // buffering            缓冲等待中
    public static final int PLAY_PREPARING = 3;     // preparing            准备等待中
    public static final int PLAY_START = 4;         // start                   开始播放
    public static final int PLAY_PAUSE = 5;         //pause                    暂停
    public static final int PLAY_SEEKTO = 6;        //seekto                   拖动进度
    public static final int PLAY_STOP = 7;          // stop                     停止
    public static final int PLAY_ERROR = 8;         // error                  错误
    public static final int PLAY_REQUEST = 9;       // 请求URL
    public static final int PLAY_COMPLETION = 10;   // 播放完成
    public static final int PLAY_BUFFERING_PAUSE = 11; //buffer暂停
    public static final int PLAY_REQUEST_SUCCESS = 12;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DEFAULT, INIT, PLAY_IDLE, PLAY_BUFFERING, PLAY_PREPARING, PLAY_START, PLAY_PAUSE,
            PLAY_SEEKTO, PLAY_STOP, PLAY_ERROR, PLAY_REQUEST, PLAY_COMPLETION, PLAY_BUFFERING_PAUSE,
            PLAY_REQUEST_SUCCESS})
    public @interface PlayState {

    }

    private MediaPlayer mediaPlayer;
    private MediaListener mediaListener;
    List<PlayerListener> playerListeners;
    private PlayList playList;
    private OnlineCase onlineCase;
    // Player status
    private boolean isPaused;
    @PlayMode
    private int playMode;
    @PlayState
    private int playState = DEFAULT;

    private CompositeSubscription compositeSubscription;
    private int currentDuration;
    private int progress;

    void resetProgressa() {
        progress = 0;
        currentDuration = 0;
        stopUpdateProgress();
    }

    void stopUpdateProgress() {
        compositeSubscription.clear();
    }

    void startUpdateProgress() {
        Subscription subscribe = Observable.timer(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((aLong) -> {
                    currentDuration = mediaPlayer.getCurrentPosition() / 1000;
                    int duration = playList.getCurrentSong().file_duration;
                    progress = (int) (currentDuration * 100f / duration);
                    for (int i = 0; i < playerListeners.size(); i++) {
                        PlayerListener listener = playerListeners.get(i);
                        listener.onProgress(true, progress, currentDuration, 0);
                    }
//                    TLogger.e(TAG, "currentDuration=" + currentDuration + " duration=" + duration + " progress=" + progress);
                });
        compositeSubscription.add(subscribe);
    }

    public void onCompletion() {
        resetProgressa();
        playNext();
    }

    @Override
    public void addPlayerListener(PlayerListener listener) {
        playerListeners.add(listener);
    }

    @Override
    public void removePlayerListener(PlayerListener listener) {
        playerListeners.remove(listener);
    }

    @PlayMode
    int getDefault() {
        return LOOP;
    }

    @PlayMode
    public int switchNextMode(@PlayMode int current) {
        switch (current) {
            case LOOP:
                return LIST;
            case LIST:
                return SHUFFLE;
            case SHUFFLE:
                return SINGLE;
            case SINGLE:
                return LOOP;
        }
        return getDefault();
    }


    private Player() {
        mediaPlayer = new MediaPlayer();
        playList = new PlayList();
        mediaListener = new MediaListener(this);
        playerListeners = new ArrayList<>();
        mediaPlayer.setOnInfoListener(mediaListener);
        mediaPlayer.setOnPreparedListener(mediaListener);
        mediaPlayer.setOnBufferingUpdateListener(mediaListener);
        mediaPlayer.setOnSeekCompleteListener(mediaListener);
        mediaPlayer.setOnCompletionListener(mediaListener);
        mediaPlayer.setOnErrorListener(mediaListener);
        onlineCase = new OnlineCase();
        compositeSubscription = new CompositeSubscription();
    }

    public static Player getInstance() {
        if (sInstance == null) {
            synchronized (Player.class) {
                if (sInstance == null) {
                    sInstance = new Player();
                }
            }
        }
        return sInstance;
    }

    @Override
    public PlayList getPlayList() {
        return this.playList;
    }

    @Override
    public void appendPlayList(PlayList playList) {
        if (playList == null) {
            return;
        }
        this.playList.addSongList(playList.getSongList());
    }

    @Override
    public boolean append(SongEntity song) {
        this.playList.addSong(song);
        return true;
    }

    @Override
    public boolean play() {
        startUpdateProgress();
        if (isPaused) {
            mediaPlayer.start();
            isPaused = false;
            for (int i = 0; i < playerListeners.size(); i++) {
                PlayerListener listener = playerListeners.get(i);
                listener.onResume();
            }
            return true;
        }
        if (playList.prepare()) {
            SongEntity song = playList.getCurrentSong();
            loadMusicDetail(song);
            return true;
        }
        return false;
    }

    @Override
    public boolean play(PlayList list) {
        if (list == null) {
            return false;
        }
        play(list, 0);
        return true;
    }

    @Override
    public boolean play(PlayList list, int startIndex) {
        if (list == null) {
            return false;
        }
        list.setPlayingIndex(startIndex);
        this.playList = list;
        play();
        return true;
    }

    @Override
    public boolean play(SongEntity song) {
        return false;
    }

    @Override
    public boolean playPrev() {
        if (playList.prev() != null) {
            play();
        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean playNext() {
        if (playList.next() != null) {
            play();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean pause() {
        isPaused = true;
        mediaPlayer.pause();
        stopUpdateProgress();
        List<PlayerListener> listenerList = playerListeners;
        for (int i = 0; i < listenerList.size(); i++) {
            PlayerListener listener = listenerList.get(i);
            listener.onPause();
        }
        return false;
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public SongEntity getPlayingSong() {
        return null;
    }

    @Override
    public boolean seekTo(int progress) {
        int duration = playList.getCurrentSong().file_duration;
        mediaPlayer.seekTo(duration * progress * 10);
        return true;
    }

    @Override
    public void setPlayMode(@PlayMode int playMode) {
        this.playMode = playMode;
    }

    @Override
    @PlayMode
    public int getPlayMode() {
        return playMode;
    }

    @Override
    public void setPlayState(@PlayState int playState) {
        this.playState = playState;
    }

    @Override
    @PlayState
    public int getPlayState() {
        return playState;
    }

    /**
     * 加载
     */
    private void loadMusicDetail(SongEntity entity) {
        TLogger.d(TAG, "loadMusicDetail: songid=" + entity.song_id + " source=" + entity.song_source);
        Action1 onNext = (result) -> {
            SongDetail songDetail = (SongDetail) result;
            String path = songDetail.songurl.get(0).show_link;
            TLogger.d(TAG, "get play path: " + path);
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
                List<PlayerListener> listenerList = playerListeners;
                for (int i = 0; i < listenerList.size(); i++) {
                    PlayerListener listener = listenerList.get(i);
                    listener.onPlay();
                }
            } catch (IOException e) {
                TLogger.e(TAG, "play IOException: ", e.getMessage());
            }
        };
        onlineCase.getMusicInfo(entity.song_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }


}
