package com.tc.audioplayer.bussiness.album;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.usecase.OnlineCase;

/**
 * Created by itcayman on 2017/9/22.
 */

public class AlbumnPresenter extends LifePresenter {
    private int page = 2;
    private OnlineCase onlineCase;

    public AlbumnPresenter() {
        onlineCase = new OnlineCase();
    }

    @Override
    public void loadData(boolean refresh) {
        if (refresh)
            page = 2;
        addSubscription(onlineCase.getAlbumnList(refresh, page), getOnNextAction(), getOnErrorAction());
    }
}
