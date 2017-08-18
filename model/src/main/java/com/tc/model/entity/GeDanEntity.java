package com.tc.model.entity;

import com.tc.model.api.BMA;

/**
 * Created by tianchao on 2017/8/3.
 * 歌单
 */

public class GeDanEntity {
    /**
     * 歌单分类
     *
     * @return
     */
    public static String geDanCategory() {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.diy.gedanCategory");
        return sb.toString();
    }

    /**
     * 热门歌单
     *
     * @param num
     * @return
     */
    public static String hotGeDan(int num) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.diy.getHotGeDanAndOfficial")
                .append("&num=").append(num);
        return sb.toString();
    }

    /**
     * 歌单
     *
     * @param pageNo   页码
     * @param pageSize 每页数量
     * @return
     */
    public static String geDan(int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.diy.gedan")
                .append("&page_size=").append(pageSize)
                .append("&page_no=").append(pageNo);
        return sb.toString();
    }


    /**
     * 包含标签的歌单
     *
     * @param tag      标签名
     * @param pageNo   页码
     * @param pageSize 每页数量
     * @return
     */
    public static String geDanByTag(String tag, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.diy.search")
                .append("&page_size=").append(pageSize)
                .append("&page_no=").append(pageNo)
                .append("&query=").append(BMA.encode(tag));
        return sb.toString();
    }

    /**
     * 歌单信息和歌曲
     *
     * @param listid 歌单id
     * @return
     */
    public static String geDanInfo(String listid) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.diy.gedanInfo")
                .append("&listid=").append(listid);
        return sb.toString();
    }
}
