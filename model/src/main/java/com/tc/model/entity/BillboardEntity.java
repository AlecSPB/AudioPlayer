package com.tc.model.entity;

import com.tc.model.net.ParseNodePath;

import java.util.List;

/**
 * Created by tianchao on 2017/8/3.
 * 音乐榜
 */

@ParseNodePath(path = ":content")
public class BillboardEntity {

    public String name;
    public int type;
    public int count;
    public String comment;
    public String web_url;
    public String pic_s192;
    public String pic_s444;
    public String pic_s260;
    public String pic_s210;
    public String pic_s328;
    public String pic_s640;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * title : 红颜旧
         * author : 崔子格
         * song_id : 554926752
         * album_id : 554926749
         * album_title : 红颜旧
         * rank_change : 0
         * all_rate : 64,128,256,320,flac
         * biaoshi : first,lossless
         */

        public String title;
        public String author;
        public String song_id;
        public String album_id;
        public String album_title;
        public String rank_change;
        public String all_rate;
        public String biaoshi;
        public int number;
    }
}
