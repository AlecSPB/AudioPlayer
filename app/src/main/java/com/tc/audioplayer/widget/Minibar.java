package com.tc.audioplayer.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.audioplayer.bussiness.player.PlayerDetailDialog;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.player.SimplePlayerListener;
import com.tc.model.entity.PlayList;
import com.tc.model.entity.SongEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by tianchao on 2017/8/6.
 */

public class Minibar extends LinearLayout {
    //    @BindView(R.id.miniProgressBar)
//    CircularProgressBar progressBar;
    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.loading)
//    MetroLoadingView loadingView;
            ProgressView loadingView;

    private static final String TAG = Minibar.class.getSimpleName();

    private boolean autoVisibility = false;
    private MibarPlayerListener playerListener;
    private int progress;
    private FragmentManager fragmentManager;

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
        setBackgroundColor(getResources().getColor(R.color.bg_minibar));
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
        ButterKnife.bind(this);
        setOnClickListener((v) -> {
//            Navigator.toPlayerDetailActivity(getContext());
            if (fragmentManager == null && getContext() instanceof AppCompatActivity)
                fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            PlayerDetailDialog dialog = new PlayerDetailDialog();
            dialog.show(fragmentManager, "player_detail_dialog");
        });
        playerListener = new MibarPlayerListener();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setAutoVisibility(boolean autoVisibility) {
        this.autoVisibility = autoVisibility;
        if (autoVisibility) {
            PlayerManager.getInstance().addPlayListener(playerListener);
        }
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
        Glide.with(getContext())
                .load(song.pic_small)
                .asBitmap()
                .transform(new RoundedCornersTransformation(getContext(), 10, 0))
                .into(ivAvatar);
        float progress = playList.getCurrentDuration() * 100f / song.getFile_duration();
//        progressBar.setProgress(progress);
    }


    @OnClick(R.id.iv_play_pause)
    public void onPlayPause(View view) {
        PlayerManager.getInstance().playPause();
    }

    @OnClick(R.id.iv_next)
    public void onPlayNext(View view) {
        PlayerManager.getInstance().playNext();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
//        if (visibility == VISIBLE) {
//            PlayerManager.getInstance().addPlayListener(playerListener);
//        } else {
//            PlayerManager.getInstance().removePlayListener(playerListener);
//        }
    }

    @Override
    protected void onDetachedFromWindow() {
        PlayerManager.getInstance().removePlayListener(playerListener);
        super.onDetachedFromWindow();
    }

    private class MibarPlayerListener extends SimplePlayerListener {
        @Override
        public void onInit(PlayList currentPlaylist) {
            setVisibility(View.VISIBLE);
            loadingView.start();
        }

        @Override
        public void onPreparingStart() {
            ivPlayPause.setSelected(false);
            bindData();
            PlayerManager.getInstance().updatePlaylistToDB();
        }

        @Override
        public void onBufferingError() {
            loadingView.stop();
            Toast.makeText(getContext(), getContext().getString(R.string.error_buffering), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPlay() {
            ivPlayPause.setSelected(true);
            loadingView.stop();
//            progressBar.setPlayState(true);
        }

        @Override
        public void onPause() {
//            progressBar.setPlayState(false);
            ivPlayPause.setSelected(false);
        }

        @Override
        public void onResume() {
//            progressBar.setPlayState(true);
            ivPlayPause.setSelected(true);
        }

        @Override
        public void onProgress(boolean isPlaying, int progress, int duration, int secondProgress) {
//            progressBar.setProgress(progress);
            ivPlayPause.setSelected(true);
        }

        @Override
        public void onCompletion() {
//            progressBar.setPlayState(false);
            ivPlayPause.setSelected(false);
        }

        @Override
        public void onError(int errorCode) {
            super.onError(errorCode);
            loadingView.stop();
            Toast.makeText(getContext(), getContext().getString(R.string.error_load_music_info, errorCode),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
