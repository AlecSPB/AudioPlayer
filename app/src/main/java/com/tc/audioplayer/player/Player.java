package com.tc.audioplayer.player;

import android.media.MediaPlayer;
import android.support.annotation.IntDef;

import com.tc.base.utils.TLogger;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongDetail;
import com.tc.model.entity.SongEntity;
import com.tc.model.entity.SongInfoEntity;
import com.tc.model.usecase.OnlineCase;

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

import static com.tc.model.entity.PlayList.LOOP;
import static com.tc.model.entity.PlayList.SHUFFLE;
import static com.tc.model.entity.PlayList.SINGLE;

/**
 * Created by tianchao on 2017/8/10.
 */

public class Player implements IPlayer {
    private static final String TAG = "Player";
    private static volatile Player sInstance;

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
    @PlayList.PlayMode
    private int playMode;
    @PlayState
    private int playState = DEFAULT;

    private CompositeSubscription compositeSubscription;
    private int currentDuration;
    private int seekToDuration;
    private int progress;

    public int getCurrentDuration() {
        return currentDuration;
    }

    @Override
    public int getSeekToDuration() {
        return seekToDuration;
    }

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
                    this.playList.setCurrentDuration(currentDuration);
                    int duration = mediaPlayer.getDuration() / 1000;
                    getPlayingSong().setFile_duration(duration);
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
        if (playMode != PlayList.SINGLE) {
            playNext();
        } else {
            play();
        }
    }

    @Override
    public void addPlayerListener(PlayerListener listener) {
        playerListeners.add(listener);
    }

    @Override
    public void removePlayerListener(PlayerListener listener) {
        playerListeners.remove(listener);
    }

    @PlayList.PlayMode
    public int switchNextMode(@PlayList.PlayMode int current) {
        switch (current) {
            case LOOP:
                playMode = SHUFFLE;
                break;
            case SHUFFLE:
                playMode = SINGLE;
                break;
            case SINGLE:
                playMode = LOOP;
                break;
        }
        playList.setPlayMode(playMode);
        return playMode;
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
    public void appendPlayList(PlayList playList, int startIndex, int currentDuration) {
        appendPlayList(playList);
        this.playList.setPlayingIndex(startIndex);
        this.playList.setCurrentDuration(currentDuration);
        this.currentDuration = currentDuration;
        this.seekToDuration = currentDuration;
        SongEntity song = playList.getSongList().get(startIndex);
        float progress = playList.getCurrentDuration() * 100f / song.getFile_duration();
        this.progress = (int) progress;
    }

    @Override
    public boolean append(SongEntity song) {
        this.playList.addSong(song);
        return true;
    }

    @Override
    public boolean play() {
        if (isPaused) {
            TLogger.d(TAG, "from pause to start");
            startUpdateProgress();
            mediaPlayer.start();
            isPaused = false;
            for (int i = 0; i < playerListeners.size(); i++) {
                PlayerListener listener = playerListeners.get(i);
                listener.onResume();
            }
            return true;
        }
        if (playList.prepare()) {
            stopUpdateProgress();
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
        if (playList == null) {
            playList = new PlayList();
        }
        playList.clear();
        playList.addSong(song);
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
        return playList.getCurrentSong();
    }

    @Override
    public boolean seekTo(int progress) {
        int duration = playList.getCurrentSong().file_duration;
        mediaPlayer.seekTo(duration * progress * 10);
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            for (int i = 0; i < playerListeners.size(); i++) {
                PlayerListener listener = playerListeners.get(i);
                listener.onPlay();
            }
        }
        seekToDuration = 0;
        return true;
    }

    @Override
    public void setPlayMode(@PlayList.PlayMode int playMode) {
        this.playMode = playMode;
        this.playList.setPlayMode(playMode);
    }

    @Override
    @PlayList.PlayMode
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

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    /**
     * 加载
     */
    private void loadMusicDetail(SongEntity entity) {
        TLogger.d(TAG, "loadMusicDetail: songid=" + entity.song_id + " source=" + entity.song_source);
        Action1 onNext = (result) -> {
            SongDetail songDetail = (SongDetail) result;
            SongEntity songEntity = playList.getCurrentSong();
            SongInfoEntity songInfo = songDetail.songinfo;
            songEntity.setTitle(songInfo.getTitle());
            songEntity.setAuthor(songInfo.author);
            songEntity.setLrclink(songInfo.lrclink);
            songEntity.setPic_small(songInfo.pic_small);
            String path = songDetail.songurl.url.get(0).show_link;
            try {
                stopUpdateProgress();
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
//                if (currentDuration > 0) {
//                    mediaPlayer.seekTo(currentDuration * 1000);
//                } else {
//                    mediaPlayer.start();
//                }
                if (seekToDuration == 0) {
                    mediaPlayer.start();
                    startUpdateProgress();
                    TLogger.d(TAG, "get play path: duration=" + currentDuration * 1000 + " path=" + path);
                    List<PlayerListener> listenerList = playerListeners;
                    for (int i = 0; i < listenerList.size(); i++) {
                        PlayerListener listener = listenerList.get(i);
                        listener.onPlay();
                    }
                }
            } catch (Exception e) {
                TLogger.e(TAG, "play Exception: ", e);
            }
        };
        Action1<Throwable> onError = (throwable) -> {
            TLogger.e(TAG, "getMusic error: " + throwable);
        };
        onlineCase.getMusicInfo(entity.song_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
    }


}
