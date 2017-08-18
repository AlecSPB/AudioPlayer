package com.tc.model.entity;

import com.tc.model.api.AESTools;
import com.tc.model.api.BMA;

/**
 * Created by tianchao on 2017/8/3.
 * 搜索
 */

public class SearchEntity {
    /**
     * 热门关键字
     *
     * @return
     */
    public static String hotWord() {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.search.hot");
        return sb.toString();
    }

    /**
     * 搜索建议
     *
     * @param query 输入词
     * @return
     */
    public static String searchSugestion(String query) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.search.catalogSug")
                .append("&query=").append(BMA.encode(query));
        return sb.toString();
    }

    /**
     * 搜歌词
     *
     * @param songname 歌名
     * @param artist   艺术家
     * @return
     */
    public static String searchLrcPic(String songname, String artist) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        String ts = Long.toString(System.currentTimeMillis());
        String query = BMA.encode(songname) + "$$" + BMA.encode(artist);
        String e = AESTools.encrpty("query=" + songname + "$$" + artist + "&ts=" + ts);
        sb.append("&method=").append("baidu.ting.search.lrcpic")
                .append("&query=").append(query)
                .append("&ts=").append(ts)
                .append("&type=2")
                .append("&e=").append(e);
        return sb.toString();
    }

    /**
     * 合并搜索结果，用于搜索建议中的歌曲
     *
     * @param query
     * @return
     */
    public static String searchMerge(String query, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.search.merge")
                .append("&query=").append(BMA.encode(query))
                .append("&page_no=").append(pageNo)
                .append("&page_size=").append(pageSize)
                .append("&type=-1&data_source=0");
        return sb.toString();
    }

    /**
     * 搜索伴奏
     *
     * @param query    关键词
     * @param pageNo   页码
     * @param pageSize 每页数量，也是返回数量
     * @return
     */
    public static String searchAccompany(String query, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(BMA.BASE);
        sb.append("&method=").append("baidu.ting.learn.search")
                .append("&query=").append(BMA.encode(query))
                .append("&page_no=").append(pageNo)
                .append("&page_size=").append(pageSize);
        return sb.toString();
    }
}
