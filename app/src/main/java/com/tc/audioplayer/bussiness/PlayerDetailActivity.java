package com.tc.audioplayer.bussiness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.player.PlayList;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.player.SimplePlayerListener;
import com.tc.audioplayer.utils.AudioDurationUtil;
import com.tc.audioplayer.utils.StatusBarUtil;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.SongListItemEntity;


/**
 * Created by itcayman on 2017/8/20.
 */

public class PlayerDetailActivity extends ToolbarActivity {
    private static final String TAG = PlayerDetailActivity.class.getSimpleName();
    private ImageView ivPlayMode;
    private ImageView ivPlayPause;
    private ImageView ivPrev;
    private ImageView ivNext;
    private ImageView ivList;
    private TextView tvCurrentDuration;
    private TextView tvTotalDuration;
    private SeekBar seekBar;

    private DetailPlayerListener playerListener;
    private SeekbarListener seekbarListener;
    private boolean seekbarStarting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_player);

        ivPlayMode = (ImageView) findViewById(R.id.iv_play_mode);
        ivPlayPause = (ImageView) findViewById(R.id.iv_play_pause);
        ivPrev = (ImageView) findViewById(R.id.iv_prev);
        ivNext = (ImageView) findViewById(R.id.iv_next);
        ivList = (ImageView) findViewById(R.id.iv_list);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tvCurrentDuration = (TextView) findViewById(R.id.tv_current_duration);
        tvTotalDuration = (TextView) findViewById(R.id.tv_total_duration);

        minibar.setVisibility(View.GONE);
        StatusBarUtil.setTransparent(this);
        changeToolBarColor(0);
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
    }

    /**
     * 初始化播放器当前状态
     */
    private void initPlayerStatus() {
        PlayList playList = PlayerManager.getInstance().getPlayList();
        SongListItemEntity song = playList.getCurrentSong();
        int progress = PlayerManager.getInstance().getProgress();
        int duration = progress * song.getFile_duration() / 100;
        String currentDuration = AudioDurationUtil.secondsToString(duration);
        String totalDuration = AudioDurationUtil.secondsToString(song.getFile_duration());
        tvCurrentDuration.setText(currentDuration);
        tvTotalDuration.setText(totalDuration);
        seekBar.setProgress(progress);
        if (PlayerManager.getInstance().isPlaying()) {
            ivPlayPause.setImageResource(R.drawable.selector_play);
        } else {
            ivPlayPause.setImageResource(R.drawable.selector_pause);
        }
        setToolbarTitle(song.getTitle());
        setToolbarSubtitle(song.getAuthor());
    }

    @Override
    protected void onDestroy() {
        PlayerManager.getInstance().removePlayListener(playerListener);
        super.onDestroy();
    }

    private View.OnClickListener onClickListener = (view) -> {
        switch (view.getId()) {
            case R.id.iv_play_mode:
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
                SongListItemEntity song = playList.getCurrentSong();
                int totalDuration = song.getFile_duration();
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
            if (seekbarStarting)
                return;
            seekBar.setProgress(progress);
            String totalDuration = AudioDurationUtil.secondsToString(duration);
            tvCurrentDuration.setText(totalDuration);
        }

        @Override
        public void onCompletion() {
            TLogger.d(TAG, "onCompletion");
            ivPlayPause.setImageResource(R.drawable.selector_pause);
        }
    }
}
