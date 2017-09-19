package com.tc.audioplayer.bussiness.oline.music;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.usecase.OnlineCase;

/**
 * Created by tianchao on 2017/8/4.
 */

public class MusicPresenter extends LifePresenter {
    private OnlineCase onlineCase;
    private @MusicFragment.LIST_TYPE int type;

    public MusicPresenter(@MusicFragment.LIST_TYPE int type) {
        this.onlineCase = new OnlineCase();
        this.type = type;
    }

    @Override
    public void loadData(boolean refresh) {
//        addSubscription(onlineCase.getMusicList(type), getOnNextAction(), getOnErrorAction());
        addSubscription(onlineCase.getGeDan(10, 1), getOnNextAction(), getOnErrorAction());
    }
}
