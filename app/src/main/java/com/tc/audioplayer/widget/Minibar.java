package com.tc.audioplayer.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.common.eventbus.EventBus;
import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.player.SimplePlayerListener;
import com.tc.base.utils.TLogger;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tianchao on 2017/8/6.
 */

public class Minibar extends LinearLayout {
    @BindView(R.id.miniProgressBar)
    CircularProgressBar progressBar;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.iv_next)
    ImageView ivNext;

    private static final String TAG = Minibar.class.getSimpleName();

    private EventBus eventBus;
    private boolean autoVisibility = true;
    private MibarPlayerListener playerListener;
    private int progress;

    public Minibar(Context context) {
        super(context);
        init();
    }

    public Minibar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Minibar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_minibar, this);
        setBackgroundColor(Color.WHITE);
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
        ButterKnife.bind(this);
        setOnClickListener((v) -> {
            Navigator.toPlayerDetailActivity(getContext());
        });
        eventBus = new EventBus();
        eventBus.register(this);
        playerListener = new MibarPlayerListener();
        postDelayed(() -> {
            TLogger.d(TAG, "onAttachedToWindow");
            PlayerManager.getInstance().addPlayListener(playerListener);
        }, 500);
    }

    public void setAutoVisibility(boolean autoVisibility) {
        this.autoVisibility = autoVisibility;
    }

    public void bindData() {
        PlayList playList = PlayerManager.getInstance().getPlayList();
        SongEntity song = playList.getCurrentSong();
        if (song == null) {
            setVisibility(View.GONE);
            return;
        }
        setVisibility(autoVisibility ? View.VISIBLE : View.GONE);
        tvTitle.setText(song.title);
        tvAuthor.setText(song.author);
        Glide.with(getContext()).load(song.pic_small).into(ivAvatar);
        float progress = playList.getCurrentDuration() * 100f / song.getFile_duration();
        progressBar.setProgress(progress);
    }


    @OnClick(R.id.miniProgressBar)
    public void onPlayPause(View view) {
        PlayerManager.getInstance().playPause();
    }

    @OnClick(R.id.iv_next)
    public void onPlayNext(View view) {
        PlayerManager.getInstance().playNext();
    }

    @Override
    protected void onDetachedFromWindow() {
        eventBus.unregister(this);
        PlayerManager.getInstance().removePlayListener(playerListener);
        super.onDetachedFromWindow();
    }

//    @Subscribe
//    public void onEventMainThread(PlayEvent event) {
//        if (event.songDetail == null)
//            return;
//        switch (event.playState) {
//            case Player.PLAY_START:
//                bindData(event.songDetail.songinfo);
//                break;
//            case Player.PLAY_PAUSE:
//                break;
//            case Player.PLAY_STOP:
//                break;
//        }
//    }

    private class MibarPlayerListener extends SimplePlayerListener {
        @Override
        public void onPreparingStart() {

        }

        @Override
        public void onPlay() {
            bindData();
            progressBar.setPlayState(true);
        }

        @Override
        public void onPause() {
            progressBar.setPlayState(false);
        }

        @Override
        public void onResume() {
            progressBar.setPlayState(true);
        }

        @Override
        public void onProgress(boolean isPlaying, int progress, int duration, int secondProgress) {
            progressBar.setProgress(progress);

        }

        @Override
        public void onCompletion() {
            progressBar.setPlayState(false);
        }
    }
}
