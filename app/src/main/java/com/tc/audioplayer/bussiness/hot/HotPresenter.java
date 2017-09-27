package com.tc.audioplayer.bussiness.hot;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.usecase.OnlineCase;

import rx.functions.Action1;

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

    public void loadAlbum(Action1 onNext, Action1 onError){
        addSubscription(onlineCase.getAlbumnList(1), onNext, onError);
    }
}
