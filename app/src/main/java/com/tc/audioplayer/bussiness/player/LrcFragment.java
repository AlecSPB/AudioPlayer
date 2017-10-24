package com.tc.audioplayer.bussiness.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseFragment;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.audioplayer.utils.FileUtil;
import com.tc.audioplayer.widget.lrcview.LrcView;
import com.tc.model.usecase.OnlineCase;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.tc.audioplayer.utils.FileUtil.getLrcFile;


/**
 * Created by itcayman on 2017/8/23.
 * 歌词页面
 */

public class LrcFragment extends BaseFragment {

    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.lrc)
    LrcView lrcView;

    private OnlineCase onlineCase;
    private String lrclink;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onlineCase = new OnlineCase();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_lrc, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar.setOnSeekBarChangeListener(new SoundSeekbarListener());
        if (!TextUtils.isEmpty(lrclink)) {
            showLrc(lrclink);
        }
    }

    private class SoundSeekbarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            PlayerManager.getInstance().setVolume(progress * 1f / 100);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
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
        if (TextUtils.isEmpty(lrclink))
            return;
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
            if (saveLrcSuccess && !TextUtils.isEmpty(lrclink)) {
                File file = FileUtil.getLrcFile(lrclink);
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

}
