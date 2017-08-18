package com.tc.model.entity;

import com.tc.model.api.BMA;

/**
 * Created by tianchao on 2017/8/3.
 * 乐播节目
 * 节目相当于一个专辑
 * 每一期相当于专辑里的每首歌
 */

public class LeboEntity {
    /**
     * 频道
     *
     * @param pageNo   页码(暂时无用)
     * @param pageSize 每页数量，也是返回数量(暂时无用)
     * @return
     */
    public static String channelTag(int pageNo, int pageSize) {

        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.lebo.getChannelTag")
                .append("&page_no=").append(pageNo)
                .append("&page_size=0").append(pageSize);
        return sb.toString();
    }

    /**
     * 返回频道下的不同节目的几期
     * 包含几个节目，每个节目有一期或多期
     * 比如返回 	节目1第1期，节目1第2期，节目2第1期，节目3第6期
     *
     * @param tagId 频道id
     * @param num   数量
     * @return
     */
    public static String channelSongList(String tagId, int num) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.lebo.channelSongList")
                .append("&tag_id=").append(tagId)
                .append("&num=").append(num);
        return sb.toString();
    }

    /**
     * 节目信息
     *
     * @param albumid        节目id
     * @param lastestSongNum 返回最近几期
     * @return
     */
    public static String albumInfo(String albumid, int lastestSongNum) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.lebo.albumInfo")
                .append("&album_id=").append(albumid)
                .append("&num=").append(lastestSongNum);
        return sb.toString();
    }
}
