package com.tc.audioplayer.bussiness.artist;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.usecase.OnlineCase;

/**
 * Created by itcayman on 2017/9/22.
 */

public class ArtistDetailPresenter extends LifePresenter {
    private OnlineCase onlineCase;
    private String albumnid;
    private String tinguid;

    public ArtistDetailPresenter(String albumnid, String tinguid) {
        this.albumnid = albumnid;
        this.tinguid = tinguid;
        onlineCase = new OnlineCase();
    }

    @Override
    public void loadData(boolean refresh) {
        addSubscription(onlineCase.getArtistSongList(refresh, tinguid, albumnid, 1), getOnNextAction(), getOnErrorAction());
    }
}
