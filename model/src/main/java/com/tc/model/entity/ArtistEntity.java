package com.tc.model.entity;

import com.tc.model.api.BMA;

/**
 * Created by tianchao on 2017/8/3.
 * 艺术家
 */

public class ArtistEntity {
    /**
     * 全部地区
     */
    public static final int AREA_ALL = 0;
    /**
     * 华语
     */
    public static final int AREA_CHINIESE = 6;
    /**
     * 欧美
     */
    public static final int AREA_EU = 3;
    /**
     * 韩国
     */
    public static final int AREA_KOREA = 7;
    /**
     * 日本
     */
    public static final int AREA_JAPAN = 60;
    /**
     * 其他
     */
    public static final int AREA_OTHER = 5;

    /**
     * 无选择
     */
    public static final int SEX_NONE = 0;
    /**
     * 男性
     */
    public static final int SEX_MALE = 1;
    /**
     * 女性
     */
    public static final int SEX_FEMALE = 2;
    /**
     * 组合
     */
    public static final int SEX_GROUP = 3;

    /**
     * 获取艺术家列表
     *
     * @param offset 偏移
     * @param limit  数量
     * @param area   地区：0不分,6华语,3欧美,7韩国,60日本,5其他
     * @param sex    性别：0不分,1男,2女,3组合
     * @param order  排序：1按热门，2按艺术家id
     * @param abc    艺术家名首字母：a-z,other其他
     * @return
     */
    public static String artistList(int offset, int limit, int area, int sex, int order, String abc) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.artist.getList");
        sb.append("&offset=").append(offset);
        sb.append("&limit=").append(limit);
        sb.append("&area=").append(area);
        sb.append("&sex=").append(sex);
        sb.append("&order=").append(order);//暂时不清楚order排序
        if (abc != null && !abc.trim().equals("")) {
            sb.append("&abc=").append(abc);
        }
        return sb.toString();
    }

    /**
     * 热门艺术家
     *
     * @param offset 偏移量
     * @param limit  获取数量
     * @return
     */
    public static String hotArtist(int offset, int limit) {
        return artistList(offset, limit, 0, 0, 1, null);
    }

    /**
     * 艺术家歌曲
     *
     * @param tinguid  tinguid
     * @param artistid 艺术家id
     * @param offset   偏移量
     * @param limit    获取数量
     * @return
     */
    public static String artistSongList(String tinguid, String artistid, int offset, int limit) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.artist.getSongList")
                .append("&order=2")
                .append("&tinguid=").append(tinguid)
                .append("&artistid=").append(artistid)
                .append("&offset=").append(offset)
                .append("&limits=").append(limit);
        return sb.toString();
    }

    /**
     * 艺术家信息
     *
     * @param tinguid  tinguid
     * @param artistid 艺术家id
     * @return
     */
    public static String artistInfo(String tinguid, String artistid) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.artist.getinfo")
                .append("&tinguid=").append(tinguid)
                .append("&artistid=").append(artistid);
        return sb.toString();
    }
}
