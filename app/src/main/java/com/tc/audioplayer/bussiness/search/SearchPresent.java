package com.tc.audioplayer.bussiness.search;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.model.entity.SearchWrapper;
import com.tc.model.usecase.OnlineCase;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/9/7.
 */

public class SearchPresent extends LifePresenter {
    private OnlineCase onlineCase;

    public SearchPresent() {
        onlineCase = new OnlineCase();
    }

    @Override
    public void loadData(boolean refresh) {

    }

    public void search(String key, Action1<SearchWrapper> showResult){
        doRequest(onlineCase.getSearch(key), showResult, getOnErrorAction());
    }
}
