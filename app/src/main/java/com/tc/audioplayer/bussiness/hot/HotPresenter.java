package com.tc.audioplayer.bussiness.hot;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.usecase.OnlineCase;

/**
 * Created by tianchao on 2017/8/4.
 */

public class HotPresenter extends LifePresenter {
    private OnlineCase onlineCase;

    public HotPresenter() {
        this.onlineCase = new OnlineCase();
    }

    @Override
    public void loadData(boolean refresh) {
        addSubscription(onlineCase.getMusicList(2), getOnNextAction(), getOnErrorAction());
    }
}
