package com.tc.audioplayer.bussiness.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.player.SimplePlayerListener;
import com.tc.audioplayer.utils.AudioDurationUtil;
import com.tc.audioplayer.utils.FileUtil;
import com.tc.audioplayer.widget.lrcview.LrcView;
import com.tc.base.utils.DeviceUtils;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;
import com.tc.model.usecase.OnlineCase;

import java.io.File;
import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.tc.audioplayer.utils.FileUtil.getLrcFile;


/**
 * Created by itcayman on 2017/8/20.
 */

public class PlayerDetailDialog extends DialogFragment {
    private static final String TAG = PlayerDetailDialog.class.getSimpleName();
    private OnlineCase onlineCase;
    private ImageView ivPlayMode;
    private ImageView ivPlayPause;
    private ImageView ivPrev;
    private ImageView ivNext;
    private ImageView ivList;
    private TextView tvCurrentDuration;
    private TextView tvTotalDuration;
    private SeekBar seekBar;
    private LrcView lrcView;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    private DetailPlayerListener playerListener;
    private SeekbarListener seekbarListener;
    private boolean seekbarStarting;
    private String lrclink;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onlineCase = new OnlineCase();
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ActionSheet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog_player, container);
        ivPlayMode = (ImageView) view.findViewById(R.id.iv_play_mode);
        ivPlayPause = (ImageView) view.findViewById(R.id.iv_play_pause);
        ivPrev = (ImageView) view.findViewById(R.id.iv_prev);
        ivNext = (ImageView) view.findViewById(R.id.iv_next);
        ivList = (ImageView) view.findViewById(R.id.iv_list);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        lrcView = (LrcView) view.findViewById(R.id.lrc);
        tvCurrentDuration = (TextView) view.findViewById(R.id.tv_current_duration);
        tvTotalDuration = (TextView) view.findViewById(R.id.tv_total_duration);

        seekbarListener = new SeekbarListener();

        ivPlayMode.setOnClickListener(onClickListener);
        ivPlayPause.setOnClickListener(onClickListener);
        ivPrev.setOnClickListener(onClickListener);
        ivNext.setOnClickListener(onClickListener);
        ivList.setOnClickListener(onClickListener);
        seekBar.setOnSeekBarChangeListener(seekbarListener);

        playerListener = new DetailPlayerListener();
        PlayerManager.getInstance().addPlayListener(playerListener);
        initPlayerStatus();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initWindow();
    }

    private void initWindow() {
        android.view.WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = (int) (DeviceUtils.getScreenWidthPx(getContext()) * 0.95);
        p.height = (int) (DeviceUtils.getScreenHeightPx(getContext()) * 0.95);
        p.gravity = Gravity.BOTTOM;
        p.dimAmount = 0.5f;
        getActivity().getWindow().setAttributes(p);
    }

    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
        if (onlineCase == null) {
            return;
        } else {
            showLrc(lrclink);
        }
    }

    public void updateLrcTime(long time) {
        if (lrcView == null)
            return;
        lrcView.updateTime(time);
    }

    private void showLrc(String lrclink) {
        File file = getLrcFile(lrclink);
        if (file.exists()) {
            lrcView.loadLrc(file);
        } else {
            loadServerLrc(lrclink);
        }
    }

    /**
     * 记载服务器lrc文件
     */
    private void loadServerLrc(String lrclink) {
        Action1<Boolean> onNext = (saveLrcSuccess) -> {
            if (saveLrcSuccess) {
                File file = getLrcFile(lrclink);
                lrcView.loadLrc(file);
            }
        };
        Action1<Throwable> onError = (throwable) -> {
            if (!isAdded())
                return;
        };
        onlineCase.getMusicLrc(lrclink)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) -> {
                    return FileUtil.writeResponseBodyToDisk(responseBody, lrclink);
                })
                .subscribe(onNext, onError);
    }


    /**
     * 初始化播放器当前状态
     */
    private void initPlayerStatus() {
        PlayList playList = PlayerManager.getInstance().getPlayList();
        SongEntity song = playList.getCurrentSong();
        if (song == null)
            return;
        int progress = PlayerManager.getInstance().getProgress();
        int duration = progress * song.file_duration / 100;
        String currentDuration = AudioDurationUtil.secondsToString(duration);
        String totalDuration = AudioDurationUtil.secondsToString(song.file_duration);
        tvCurrentDuration.setText(currentDuration);
        tvTotalDuration.setText(totalDuration);
        seekBar.setProgress(progress);
        if (PlayerManager.getInstance().isPlaying()) {
            ivPlayPause.setImageResource(R.drawable.selector_play);
        } else {
            ivPlayPause.setImageResource(R.drawable.selector_pause);
        }
//        setToolbarTitle(song.title);
//        setToolbarSubtitle(song.author);
//        tvCenterTitle.setText(song.title);
        setLrclink(song.lrclink);
        int playMode = PlayerManager.getInstance().getPlayMode();
        updatePlayModeUI(playMode);
    }

    @Override
    public void onDestroyView() {
        PlayerManager.getInstance().removePlayListener(playerListener);
        super.onDestroyView();
    }

    private void updatePlayModeUI(int playMode) {
        switch (playMode) {
            case PlayList.SINGLE:
                ivPlayMode.setImageResource(R.drawable.selector_mode_single);
                break;
            case PlayList.LOOP:
                ivPlayMode.setImageResource(R.drawable.selector_mode_loop);
                break;
            case PlayList.SHUFFLE:
                ivPlayMode.setImageResource(R.drawable.selector_mode_random);
                break;
        }
    }

    private View.OnClickListener onClickListener = (view) -> {
        switch (view.getId()) {
            case R.id.iv_play_mode:
                int playMode = PlayerManager.getInstance().switchNextMode();
                updatePlayModeUI(playMode);
                break;
            case R.id.iv_play_pause:
                PlayerManager.getInstance().playPause();
                break;
            case R.id.iv_prev:
                PlayerManager.getInstance().playPrev();
                break;
            case R.id.iv_next:
                PlayerManager.getInstance().playNext();
                break;
            case R.id.iv_list:
                break;
        }
    };

    private class SeekbarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            TLogger.d(TAG, "onProgressChanged: progress=" + progress);
            if (seekbarStarting) {
                PlayList playList = PlayerManager.getInstance().getPlayList();
                SongEntity song = playList.getCurrentSong();
                int totalDuration = song.file_duration;
                tvCurrentDuration.setText(AudioDurationUtil.secondsToString(progress * totalDuration / 100));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            TLogger.d(TAG, "onStartTrackingTouch: progress=" + seekBar.getProgress());
            seekbarStarting = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            TLogger.d(TAG, "onStopTrackingTouch: progress=" + seekBar.getProgress());
            PlayerManager.getInstance().seekTo(seekBar.getProgress());
            seekbarStarting = false;
        }
    }

    private class DetailPlayerListener extends SimplePlayerListener {
        @Override
        public void onPreparingStart() {
            TLogger.d(TAG, "onPreparingStart");
        }

        @Override
        public void onBufferingUpdate(int percent) {
            TLogger.d(TAG, "onBufferingUpdate: percent=" + percent);
            seekBar.setSecondaryProgress(percent);
            int seektoDuration = PlayerManager.getInstance().getSeektoDuration();
            if (percent >= 100 && seektoDuration > 0) {
                PlayList playList = PlayerManager.getInstance().getPlayList();
                SongEntity song = playList.getCurrentSong();
                int totalDuration = song.file_duration;
                int progress = seektoDuration * 100 / totalDuration;
                PlayerManager.getInstance().seekTo(progress);
            }
        }

        @Override
        public void onPlay() {
            TLogger.d(TAG, "onPlay");
            ivPlayPause.setImageResource(R.drawable.selector_play);
            initPlayerStatus();
        }

        @Override
        public void onPause() {
            TLogger.d(TAG, "onPause");
            ivPlayPause.setImageResource(R.drawable.selector_pause);
        }

        @Override
        public void onResume() {
            TLogger.d(TAG, "onResume");
            ivPlayPause.setImageResource(R.drawable.selector_play);
        }

        @Override
        public void onProgress(boolean isPlaying, int progress, int duration, int secondProgress) {
            if (seekbarStarting || (progress == 0 && duration == 0 && secondProgress == 0))
                return;
            TLogger.d(TAG, "onProgress: progress=" + progress + " duration=" + duration
                    + " secondProgress=" + secondProgress);
            seekBar.setProgress(progress);
            String totalDuration = AudioDurationUtil.secondsToString(duration);
            tvCurrentDuration.setText(totalDuration);
            updateLrcTime(duration * 1000);
        }

        @Override
        public void onCompletion() {
            TLogger.d(TAG, "onCompletion");
            ivPlayPause.setImageResource(R.drawable.selector_pause);
        }
    }
}
