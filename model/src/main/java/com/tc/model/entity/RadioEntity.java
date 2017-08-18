package com.tc.model.entity;

import com.tc.model.api.BMA;

/**
 * Created by tianchao on 2017/8/3.
 * 电台
 */

public class RadioEntity {
    /**
     * 录制电台
     *
     * @param pageNo   页数
     * @param pageSize 每页数量，也是返回数量
     * @return
     */
    public static String recChannel(int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.radio.getRecChannel")
                .append("&page_no=").append(pageNo)
                .append("&page_size=").append(pageSize);
        return sb.toString();
    }

    /**
     * 推荐电台（注意返回的都是乐播节目)
     *
     * @param num
     * @return
     */
    public static String recommendRadioList(int num) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.radio.getRecommendRadioList")
                .append("&num=").append(num);
        return sb.toString();
    }

    /**
     * 频道歌曲
     *
     * @param channelname 频道名,注意返回的json数据频道有num+1个，但是最后一个是空的
     * @return
     */
    public static String channelSong(String channelname, int num) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.radio.getChannelSong")
                .append("&channelname=").append(BMA.encode(channelname))
                .append("&pn=0")
                .append("&rn=").append(num);
        return sb.toString();
    }
}
