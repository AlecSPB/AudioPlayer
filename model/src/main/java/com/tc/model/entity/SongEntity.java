package com.tc.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tianchao on 2017/8/3.
 */

@Entity
public class SongEntity {

    /**
     * artist_id : 89
     * language : 国语
     * pic_big : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/540108358/540108358.jpg@s_0,w_150
     * pic_small : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/540108358/540108358.jpg@s_0,w_90
     * country : 内地
     * area : 0
     * publishtime : 2016-11-14
     * album_no : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/b794e0a41a7806a92746d5ac3652dd8c/543756270/543756270.lrc
     * copy_type : 1
     * hot : 590182
     * all_artist_ting_uid : 1078
     * resource_type : 0
     * is_new : 0
     * rank_change : 0
     * rank : 1
     * all_artist_id : 89
     * style : 流行
     * del_status : 0
     * relate_status : 0
     * toneid : 0
     * all_rate : 64,128,256,320,flac
     * file_duration : 200
     * has_mv_mobile : 0
     * versions :
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * biaoshi : lossless
     * info :
     * has_filmtv : 0
     * si_proxycompany : 北京新奥视讯国际文化传媒有限公司
     * song_id : 276867440
     * title : 刚好遇见你
     * author : 李玉刚
     * album_title : 刚好遇见你
     * havehigh : 2
     * charge : 0
     * learn : 0
     * song_source : web
     * korean_bb_song : 0
     * ting_uid : 1078
     * album_id : 276867491
     * is_first_publish : 0
     * has_mv : 0
     * piao_id : 0
     * resource_type_ext : 0
     * mv_provider : 0000000000
     * artist_name : 李玉刚
     */

    @Id
    public String song_id;
    public String artist_id;
    public String language;
    public String pic_big;
    public String pic_small;
    public String country;
    public String area;
    public String publishtime;
    public String album_no;
    public String lrclink;
    public String copy_type;
    public String hot;
    public String all_artist_ting_uid;
    public String resource_type;
    public String is_new;
    public String rank_change;
    public String rank;
    public String all_artist_id;
    public String style;
    public String del_status;
    public String relate_status;
    public String toneid;
    public String all_rate;
    public int file_duration;
    public int has_mv_mobile;
    public String versions;
    public String bitrate_fee;
    public String biaoshi;
    public String info;
    public String has_filmtv;
    public String si_proxycompany;
    public String title;
    public String author;
    public String album_title;
    public int havehigh;
    public int charge;
    public int learn;
    public String song_source;
    public String korean_bb_song;
    public String ting_uid;
    public String album_id;
    public int is_first_publish;
    public int has_mv;
    public String piao_id;
    public String resource_type_ext;
    public String mv_provider;
    public String artist_name;
    public String path;
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getArtist_name() {
        return this.artist_name;
    }
    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
    public String getMv_provider() {
        return this.mv_provider;
    }
    public void setMv_provider(String mv_provider) {
        this.mv_provider = mv_provider;
    }
    public String getResource_type_ext() {
        return this.resource_type_ext;
    }
    public void setResource_type_ext(String resource_type_ext) {
        this.resource_type_ext = resource_type_ext;
    }
    public String getPiao_id() {
        return this.piao_id;
    }
    public void setPiao_id(String piao_id) {
        this.piao_id = piao_id;
    }
    public int getHas_mv() {
        return this.has_mv;
    }
    public void setHas_mv(int has_mv) {
        this.has_mv = has_mv;
    }
    public int getIs_first_publish() {
        return this.is_first_publish;
    }
    public void setIs_first_publish(int is_first_publish) {
        this.is_first_publish = is_first_publish;
    }
    public String getAlbum_id() {
        return this.album_id;
    }
    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }
    public String getTing_uid() {
        return this.ting_uid;
    }
    public void setTing_uid(String ting_uid) {
        this.ting_uid = ting_uid;
    }
    public String getKorean_bb_song() {
        return this.korean_bb_song;
    }
    public void setKorean_bb_song(String korean_bb_song) {
        this.korean_bb_song = korean_bb_song;
    }
    public String getSong_source() {
        return this.song_source;
    }
    public void setSong_source(String song_source) {
        this.song_source = song_source;
    }
    public int getLearn() {
        return this.learn;
    }
    public void setLearn(int learn) {
        this.learn = learn;
    }
    public int getCharge() {
        return this.charge;
    }
    public void setCharge(int charge) {
        this.charge = charge;
    }
    public int getHavehigh() {
        return this.havehigh;
    }
    public void setHavehigh(int havehigh) {
        this.havehigh = havehigh;
    }
    public String getAlbum_title() {
        return this.album_title;
    }
    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSi_proxycompany() {
        return this.si_proxycompany;
    }
    public void setSi_proxycompany(String si_proxycompany) {
        this.si_proxycompany = si_proxycompany;
    }
    public String getHas_filmtv() {
        return this.has_filmtv;
    }
    public void setHas_filmtv(String has_filmtv) {
        this.has_filmtv = has_filmtv;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getBiaoshi() {
        return this.biaoshi;
    }
    public void setBiaoshi(String biaoshi) {
        this.biaoshi = biaoshi;
    }
    public String getBitrate_fee() {
        return this.bitrate_fee;
    }
    public void setBitrate_fee(String bitrate_fee) {
        this.bitrate_fee = bitrate_fee;
    }
    public String getVersions() {
        return this.versions;
    }
    public void setVersions(String versions) {
        this.versions = versions;
    }
    public int getHas_mv_mobile() {
        return this.has_mv_mobile;
    }
    public void setHas_mv_mobile(int has_mv_mobile) {
        this.has_mv_mobile = has_mv_mobile;
    }
    public int getFile_duration() {
        return this.file_duration;
    }
    public void setFile_duration(int file_duration) {
        this.file_duration = file_duration;
    }
    public String getAll_rate() {
        return this.all_rate;
    }
    public void setAll_rate(String all_rate) {
        this.all_rate = all_rate;
    }
    public String getToneid() {
        return this.toneid;
    }
    public void setToneid(String toneid) {
        this.toneid = toneid;
    }
    public String getRelate_status() {
        return this.relate_status;
    }
    public void setRelate_status(String relate_status) {
        this.relate_status = relate_status;
    }
    public String getDel_status() {
        return this.del_status;
    }
    public void setDel_status(String del_status) {
        this.del_status = del_status;
    }
    public String getStyle() {
        return this.style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public String getAll_artist_id() {
        return this.all_artist_id;
    }
    public void setAll_artist_id(String all_artist_id) {
        this.all_artist_id = all_artist_id;
    }
    public String getRank() {
        return this.rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getRank_change() {
        return this.rank_change;
    }
    public void setRank_change(String rank_change) {
        this.rank_change = rank_change;
    }
    public String getIs_new() {
        return this.is_new;
    }
    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }
    public String getResource_type() {
        return this.resource_type;
    }
    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }
    public String getAll_artist_ting_uid() {
        return this.all_artist_ting_uid;
    }
    public void setAll_artist_ting_uid(String all_artist_ting_uid) {
        this.all_artist_ting_uid = all_artist_ting_uid;
    }
    public String getHot() {
        return this.hot;
    }
    public void setHot(String hot) {
        this.hot = hot;
    }
    public String getCopy_type() {
        return this.copy_type;
    }
    public void setCopy_type(String copy_type) {
        this.copy_type = copy_type;
    }
    public String getLrclink() {
        return this.lrclink;
    }
    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }
    public String getAlbum_no() {
        return this.album_no;
    }
    public void setAlbum_no(String album_no) {
        this.album_no = album_no;
    }
    public String getPublishtime() {
        return this.publishtime;
    }
    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }
    public String getArea() {
        return this.area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPic_small() {
        return this.pic_small;
    }
    public void setPic_small(String pic_small) {
        this.pic_small = pic_small;
    }
    public String getPic_big() {
        return this.pic_big;
    }
    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getArtist_id() {
        return this.artist_id;
    }
    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }
    public String getSong_id() {
        return this.song_id;
    }
    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }
    @Generated(hash = 821484934)
    public SongEntity(String song_id, String artist_id, String language, String pic_big, String pic_small, String country,
            String area, String publishtime, String album_no, String lrclink, String copy_type, String hot,
            String all_artist_ting_uid, String resource_type, String is_new, String rank_change, String rank,
            String all_artist_id, String style, String del_status, String relate_status, String toneid, String all_rate,
            int file_duration, int has_mv_mobile, String versions, String bitrate_fee, String biaoshi, String info,
            String has_filmtv, String si_proxycompany, String title, String author, String album_title, int havehigh,
            int charge, int learn, String song_source, String korean_bb_song, String ting_uid, String album_id,
            int is_first_publish, int has_mv, String piao_id, String resource_type_ext, String mv_provider,
            String artist_name, String path) {
        this.song_id = song_id;
        this.artist_id = artist_id;
        this.language = language;
        this.pic_big = pic_big;
        this.pic_small = pic_small;
        this.country = country;
        this.area = area;
        this.publishtime = publishtime;
        this.album_no = album_no;
        this.lrclink = lrclink;
        this.copy_type = copy_type;
        this.hot = hot;
        this.all_artist_ting_uid = all_artist_ting_uid;
        this.resource_type = resource_type;
        this.is_new = is_new;
        this.rank_change = rank_change;
        this.rank = rank;
        this.all_artist_id = all_artist_id;
        this.style = style;
        this.del_status = del_status;
        this.relate_status = relate_status;
        this.toneid = toneid;
        this.all_rate = all_rate;
        this.file_duration = file_duration;
        this.has_mv_mobile = has_mv_mobile;
        this.versions = versions;
        this.bitrate_fee = bitrate_fee;
        this.biaoshi = biaoshi;
        this.info = info;
        this.has_filmtv = has_filmtv;
        this.si_proxycompany = si_proxycompany;
        this.title = title;
        this.author = author;
        this.album_title = album_title;
        this.havehigh = havehigh;
        this.charge = charge;
        this.learn = learn;
        this.song_source = song_source;
        this.korean_bb_song = korean_bb_song;
        this.ting_uid = ting_uid;
        this.album_id = album_id;
        this.is_first_publish = is_first_publish;
        this.has_mv = has_mv;
        this.piao_id = piao_id;
        this.resource_type_ext = resource_type_ext;
        this.mv_provider = mv_provider;
        this.artist_name = artist_name;
        this.path = path;
    }
    @Generated(hash = 274420887)
    public SongEntity() {
    }

}
