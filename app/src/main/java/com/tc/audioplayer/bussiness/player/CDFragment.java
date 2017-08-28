package com.tc.audioplayer.bussiness.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tc.audioplayer.base.BaseFragment;

/**
 * Created by itcayman on 2017/8/23.
 * CD页面
 */

public class CDFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new TextView(getContext());
    }
}
