package com.tc.audioplayer.bussiness.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.model.entity.PlayList;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.player.SimplePlayerListener;
import com.tc.audioplayer.utils.AudioDurationUtil;
import com.tc.audioplayer.utils.StatusBarUtil;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.SongEntity;

import java.util.ArrayList;


/**
 * Created by itcayman on 2017/8/20.
 */

public class PlayerDetailActivity extends ToolbarActivity {
    private static final String TAG = PlayerDetailActivity.class.getSimpleName();
    private ViewPager viewPager;
    private ImageView ivPlayMode;
    private ImageView ivPlayPause;
    private ImageView ivPrev;
    private ImageView ivNext;
    private ImageView ivList;
    private TextView tvCurrentDuration;
    private TextView tvTotalDuration;
    private SeekBar seekBar;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private LrcFragment lrcFragment;
    private CDFragment cdFragment;

    private DetailPlayerListener playerListener;
    private SeekbarListener seekbarListener;
    private boolean seekbarStarting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        viewPager = (ViewPager) findViewById(R.id.vp_content);
        ivPlayMode = (ImageView) findViewById(R.id.iv_play_mode);
        ivPlayPause = (ImageView) findViewById(R.id.iv_play_pause);
        ivPrev = (ImageView) findViewById(R.id.iv_prev);
        ivNext = (ImageView) findViewById(R.id.iv_next);
        ivList = (ImageView) findViewById(R.id.iv_list);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tvCurrentDuration = (TextView) findViewById(R.id.tv_current_duration);
        tvTotalDuration = (TextView) findViewById(R.id.tv_total_duration);
        lrcFragment = new LrcFragment();
        cdFragment = new CDFragment();
        fragments.add(lrcFragment);
        fragments.add(cdFragment);
        viewPager.setAdapter(new CDPagerAdapter(getSupportFragmentManager()));

        minibar.setVisibility(View.GONE);
        minibar.setAutoVisibility(false);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
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

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private class CDPagerAdapter extends FragmentPagerAdapter {
        public CDPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
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
        setToolbarTitle(song.title);
        setToolbarSubtitle(song.author);
        lrcFragment.setLrclink(song.lrclink);
        int playMode = PlayerManager.getInstance().getPlayMode();
        updatePlayModeUI(playMode);
    }

    @Override
    protected void onDestroy() {
        PlayerManager.getInstance().removePlayListener(playerListener);
        super.onDestroy();
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
            lrcFragment.updateLrcTime(duration * 1000);
        }

        @Override
        public void onCompletion() {
            TLogger.d(TAG, "onCompletion");
            ivPlayPause.setImageResource(R.drawable.selector_pause);
        }
    }
}
