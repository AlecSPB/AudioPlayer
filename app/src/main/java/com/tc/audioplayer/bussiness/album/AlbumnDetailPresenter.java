package com.tc.audioplayer.bussiness.album;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.usecase.OnlineCase;

/**
 * Created by itcayman on 2017/9/22.
 */

public class AlbumnDetailPresenter extends LifePresenter {
    private OnlineCase onlineCase;
    private String albumnid;

    public AlbumnDetailPresenter(String albumnid) {
        this.albumnid = albumnid;
        onlineCase = new OnlineCase();
    }

    @Override
    public void loadData(boolean refresh) {
        addSubscription(onlineCase.getAlbumnDetail(albumnid), getOnNextAction(), getOnErrorAction());
    }
}
