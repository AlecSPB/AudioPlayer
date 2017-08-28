package com.tc.audioplayer.bussiness.oline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tc.audioplayer.R;

/**
 * Created by tianchao on 2017/8/2.
 * 歌单
 */

public class ListMusicFragment extends Fragment {

    public static ListMusicFragment newInstance() {
        ListMusicFragment instance = new ListMusicFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_music, container, false);
        return view;
    }
}
