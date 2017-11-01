package com.tc.audioplayer.bussiness.billboard;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.base.utils.CollectionUtil;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.usecase.OnlineCase;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/9/22.
 */

public class BillboardListPresenter extends LifePresenter {
    private OnlineCase onlineCase;

    public BillboardListPresenter() {
        onlineCase = new OnlineCase();
    }

    @Override
    public void loadData(boolean refresh) {
        addSubscription(onlineCase.getBillboardCategory(refresh), getOnNextAction(), getOnErrorAction());
    }

    /**
     * 加载歌曲详情
     */
    public void loadSongInfo(String songid, Action1 onNext, Action1 onError) {
        addSubscription(onlineCase.getSongInfo(songid), onNext, onError);
    }

    /**
     * 加载榜单列表
     * @param type 榜单类型
     */
    public void loadBillboardList(boolean refresh, int type, Action1 onNext){
        addSubscription(onlineCase.getMusicList(refresh, type), onNext, getOnErrorAction());
    }

    @Override
    protected Object formatData(Object data) {
        List<BillboardEntity> billboardEntities = (ArrayList<BillboardEntity>) data;
        List<Object> result = new ArrayList<>();
        for (BillboardEntity entity : billboardEntities) {
            BillboardEntity temp = new BillboardEntity();
            if (CollectionUtil.isEmpty(entity.content)) {
                continue;
            }
            temp.name = entity.name;
            temp.comment = entity.comment;
            temp.count = entity.count;
            temp.pic_s192 = entity.pic_s192;
            temp.pic_s210 = entity.pic_s210;
            temp.pic_s260 = entity.pic_s260;
            temp.pic_s328 = entity.pic_s328;
            temp.pic_s444 = entity.pic_s444;
            temp.pic_s640 = entity.pic_s640;
            temp.type = entity.type;
            temp.web_url = entity.web_url;
            result.add(temp);
            if (entity.content.size() > 3) {
                for (int i = 1; i <= entity.content.size(); i++) {
                    entity.content.get(i - 1).number = i;
                }
                result.addAll(entity.content.subList(0, 3));
            } else {
                result.addAll(entity.content);
            }
            result.add("");
        }
        return result;
    }
}
