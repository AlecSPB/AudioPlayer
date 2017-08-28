package com.tc.model.entity;

import com.tc.model.db.greendao.ArtistEntityDao;
import com.tc.model.db.greendao.DaoSession;
import com.tc.model.db.greendao.SongInfoEntityDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by itcayman on 2017/8/25.
 */

@Entity
public class SongInfoEntity {
    /**
     * resource_type_ext : 0
     * resource_type : 0
     * del_status : 0
     * collect_num : 129972
     * hot : 577812
     * sound_effect :
     * title : 刚好遇见你
     * language : 国语
     * play_type : 0
     * country : 内地
     * biaoshi : lossless
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * song_source : web
     * is_first_publish : 0
     * artist_640_1136 :
     * is_secret : 0
     * charge : 0
     * album_500_500 : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_500,h_500
     * korean_bb_song : 0
     * has_mv_mobile : 0
     * album_no : 1
     * is_charge :
     * pic_radio : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_300,h_300
     * has_filmtv : 0
     * pic_huge : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_1000,h_1000
     * ting_uid : 1078
     * expire : 36000
     * pic_singer :
     * si_proxycompany : 北京新奥视讯国际文化传媒有限公司
     * compose : 高进
     * toneid : 0
     * area : 0
     * original_rate :
     * artist_500_500 : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500
     * multiterminal_copytype :
     * has_mv : 0
     * album_title : 刚好遇见你
     * piao_id : 0
     * high_rate : 320
     * compress_status : 0
     * lrclink : http://musicdata.baidu.com/data2/lrc/b794e0a41a7806a92746d5ac3652dd8c/543756270/543756270.lrc
     * artist_480_800 :
     * relate_status : 0
     * learn : 0
     * pic_big : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_150,h_150
     * artist : 李玉刚
     * artist_list : [{"avatar_mini":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_20","avatar_s300":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_300","ting_uid":"1078","del_status":"0","avatar_s500":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500","artist_name":"李玉刚","avatar_small":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_48","avatar_s180":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_180","artist_id":"89"}]
     * aliasname :
     * comment_num : 3562
     * album_1000_1000 : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_1000,h_1000
     * album_id : 276867491
     * share_num : 6676
     * song_id : 276867440
     * pic_premium : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_500,h_500
     * all_rate : 64,128,256,320,flac
     * author : 李玉刚
     * share_url : http://music.baidu.com/song/276867440
     * all_artist_id : 89
     * songwriting : 高进
     * publishtime : 2016-11-14
     * copy_type : 1
     * artist_1000_1000 : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg
     * versions :
     * file_duration : 200
     * artist_id : 89
     * original : 0
     * distribution : 0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000
     * pic_small : http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_90,h_90
     * info :
     * havehigh : 2
     * bitrate : 64,128,256,320,976
     */

    @Id
    public String song_id;
    public String resource_type_ext;
    public String resource_type;
    public String del_status;
    public int collect_num;
    public String hot;
    public String sound_effect;
    public String title;
    public String language;
    public int play_type;
    public String country;
    public String biaoshi;
    public String bitrate_fee;
    public String song_source;
    public int is_first_publish;
    public String artist_640_1136;
    public String is_secret;
    public int charge;
    public String album_500_500;
    public String korean_bb_song;
    public int has_mv_mobile;
    public String album_no;
    public String is_charge;
    public String pic_radio;
    public String has_filmtv;
    public String pic_huge;
    public String ting_uid;
    public int expire;
    public String pic_singer;
    public String si_proxycompany;
    public String compose;
    public String toneid;
    public String area;
    public String original_rate;
    public String artist_500_500;
    public String multiterminal_copytype;
    public int has_mv;
    public String album_title;
    public String piao_id;
    public String high_rate;
    public String compress_status;
    public String lrclink;
    public String artist_480_800;
    public String relate_status;
    public int learn;
    public String pic_big;
    public String artist;
    public String aliasname;
    public int comment_num;
    public String album_1000_1000;
    public String album_id;
    public int share_num;
    public String pic_premium;
    public String all_rate;
    public String author;
    public String share_url;
    public String all_artist_id;
    public String songwriting;
    public String publishtime;
    public String copy_type;
    public String artist_1000_1000;
    public String versions;
    public String file_duration;
    public String artist_id;
    public int original;
    public String distribution;
    public String pic_small;
    public String info;
    public int havehigh;
    public String bitrate;
    @ToMany(referencedJoinProperty = "artist_id")
    public List<ArtistEntity> artist_list;
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1569597842)
    public synchronized void resetArtist_list() {
        artist_list = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 208218821)
    public List<ArtistEntity> getArtist_list() {
        if (artist_list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ArtistEntityDao targetDao = daoSession.getArtistEntityDao();
            List<ArtistEntity> artist_listNew = targetDao._querySongInfoEntity_Artist_list(song_id);
            synchronized (this) {
                if(artist_list == null) {
                    artist_list = artist_listNew;
                }
            }
        }
        return artist_list;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 120648698)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSongInfoEntityDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1285030765)
    private transient SongInfoEntityDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getBitrate() {
        return this.bitrate;
    }
    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }
    public int getHavehigh() {
        return this.havehigh;
    }
    public void setHavehigh(int havehigh) {
        this.havehigh = havehigh;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getPic_small() {
        return this.pic_small;
    }
    public void setPic_small(String pic_small) {
        this.pic_small = pic_small;
    }
    public String getDistribution() {
        return this.distribution;
    }
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }
    public int getOriginal() {
        return this.original;
    }
    public void setOriginal(int original) {
        this.original = original;
    }
    public String getArtist_id() {
        return this.artist_id;
    }
    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }
    public String getFile_duration() {
        return this.file_duration;
    }
    public void setFile_duration(String file_duration) {
        this.file_duration = file_duration;
    }
    public String getVersions() {
        return this.versions;
    }
    public void setVersions(String versions) {
        this.versions = versions;
    }
    public String getArtist_1000_1000() {
        return this.artist_1000_1000;
    }
    public void setArtist_1000_1000(String artist_1000_1000) {
        this.artist_1000_1000 = artist_1000_1000;
    }
    public String getCopy_type() {
        return this.copy_type;
    }
    public void setCopy_type(String copy_type) {
        this.copy_type = copy_type;
    }
    public String getPublishtime() {
        return this.publishtime;
    }
    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }
    public String getSongwriting() {
        return this.songwriting;
    }
    public void setSongwriting(String songwriting) {
        this.songwriting = songwriting;
    }
    public String getAll_artist_id() {
        return this.all_artist_id;
    }
    public void setAll_artist_id(String all_artist_id) {
        this.all_artist_id = all_artist_id;
    }
    public String getShare_url() {
        return this.share_url;
    }
    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAll_rate() {
        return this.all_rate;
    }
    public void setAll_rate(String all_rate) {
        this.all_rate = all_rate;
    }
    public String getPic_premium() {
        return this.pic_premium;
    }
    public void setPic_premium(String pic_premium) {
        this.pic_premium = pic_premium;
    }
    public int getShare_num() {
        return this.share_num;
    }
    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }
    public String getAlbum_id() {
        return this.album_id;
    }
    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }
    public String getAlbum_1000_1000() {
        return this.album_1000_1000;
    }
    public void setAlbum_1000_1000(String album_1000_1000) {
        this.album_1000_1000 = album_1000_1000;
    }
    public int getComment_num() {
        return this.comment_num;
    }
    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }
    public String getAliasname() {
        return this.aliasname;
    }
    public void setAliasname(String aliasname) {
        this.aliasname = aliasname;
    }
    public String getArtist() {
        return this.artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getPic_big() {
        return this.pic_big;
    }
    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }
    public int getLearn() {
        return this.learn;
    }
    public void setLearn(int learn) {
        this.learn = learn;
    }
    public String getRelate_status() {
        return this.relate_status;
    }
    public void setRelate_status(String relate_status) {
        this.relate_status = relate_status;
    }
    public String getArtist_480_800() {
        return this.artist_480_800;
    }
    public void setArtist_480_800(String artist_480_800) {
        this.artist_480_800 = artist_480_800;
    }
    public String getLrclink() {
        return this.lrclink;
    }
    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }
    public String getCompress_status() {
        return this.compress_status;
    }
    public void setCompress_status(String compress_status) {
        this.compress_status = compress_status;
    }
    public String getHigh_rate() {
        return this.high_rate;
    }
    public void setHigh_rate(String high_rate) {
        this.high_rate = high_rate;
    }
    public String getPiao_id() {
        return this.piao_id;
    }
    public void setPiao_id(String piao_id) {
        this.piao_id = piao_id;
    }
    public String getAlbum_title() {
        return this.album_title;
    }
    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }
    public int getHas_mv() {
        return this.has_mv;
    }
    public void setHas_mv(int has_mv) {
        this.has_mv = has_mv;
    }
    public String getMultiterminal_copytype() {
        return this.multiterminal_copytype;
    }
    public void setMultiterminal_copytype(String multiterminal_copytype) {
        this.multiterminal_copytype = multiterminal_copytype;
    }
    public String getArtist_500_500() {
        return this.artist_500_500;
    }
    public void setArtist_500_500(String artist_500_500) {
        this.artist_500_500 = artist_500_500;
    }
    public String getOriginal_rate() {
        return this.original_rate;
    }
    public void setOriginal_rate(String original_rate) {
        this.original_rate = original_rate;
    }
    public String getArea() {
        return this.area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getToneid() {
        return this.toneid;
    }
    public void setToneid(String toneid) {
        this.toneid = toneid;
    }
    public String getCompose() {
        return this.compose;
    }
    public void setCompose(String compose) {
        this.compose = compose;
    }
    public String getSi_proxycompany() {
        return this.si_proxycompany;
    }
    public void setSi_proxycompany(String si_proxycompany) {
        this.si_proxycompany = si_proxycompany;
    }
    public String getPic_singer() {
        return this.pic_singer;
    }
    public void setPic_singer(String pic_singer) {
        this.pic_singer = pic_singer;
    }
    public int getExpire() {
        return this.expire;
    }
    public void setExpire(int expire) {
        this.expire = expire;
    }
    public String getTing_uid() {
        return this.ting_uid;
    }
    public void setTing_uid(String ting_uid) {
        this.ting_uid = ting_uid;
    }
    public String getPic_huge() {
        return this.pic_huge;
    }
    public void setPic_huge(String pic_huge) {
        this.pic_huge = pic_huge;
    }
    public String getHas_filmtv() {
        return this.has_filmtv;
    }
    public void setHas_filmtv(String has_filmtv) {
        this.has_filmtv = has_filmtv;
    }
    public String getPic_radio() {
        return this.pic_radio;
    }
    public void setPic_radio(String pic_radio) {
        this.pic_radio = pic_radio;
    }
    public String getIs_charge() {
        return this.is_charge;
    }
    public void setIs_charge(String is_charge) {
        this.is_charge = is_charge;
    }
    public String getAlbum_no() {
        return this.album_no;
    }
    public void setAlbum_no(String album_no) {
        this.album_no = album_no;
    }
    public int getHas_mv_mobile() {
        return this.has_mv_mobile;
    }
    public void setHas_mv_mobile(int has_mv_mobile) {
        this.has_mv_mobile = has_mv_mobile;
    }
    public String getKorean_bb_song() {
        return this.korean_bb_song;
    }
    public void setKorean_bb_song(String korean_bb_song) {
        this.korean_bb_song = korean_bb_song;
    }
    public String getAlbum_500_500() {
        return this.album_500_500;
    }
    public void setAlbum_500_500(String album_500_500) {
        this.album_500_500 = album_500_500;
    }
    public int getCharge() {
        return this.charge;
    }
    public void setCharge(int charge) {
        this.charge = charge;
    }
    public String getIs_secret() {
        return this.is_secret;
    }
    public void setIs_secret(String is_secret) {
        this.is_secret = is_secret;
    }
    public String getArtist_640_1136() {
        return this.artist_640_1136;
    }
    public void setArtist_640_1136(String artist_640_1136) {
        this.artist_640_1136 = artist_640_1136;
    }
    public int getIs_first_publish() {
        return this.is_first_publish;
    }
    public void setIs_first_publish(int is_first_publish) {
        this.is_first_publish = is_first_publish;
    }
    public String getSong_source() {
        return this.song_source;
    }
    public void setSong_source(String song_source) {
        this.song_source = song_source;
    }
    public String getBitrate_fee() {
        return this.bitrate_fee;
    }
    public void setBitrate_fee(String bitrate_fee) {
        this.bitrate_fee = bitrate_fee;
    }
    public String getBiaoshi() {
        return this.biaoshi;
    }
    public void setBiaoshi(String biaoshi) {
        this.biaoshi = biaoshi;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getPlay_type() {
        return this.play_type;
    }
    public void setPlay_type(int play_type) {
        this.play_type = play_type;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSound_effect() {
        return this.sound_effect;
    }
    public void setSound_effect(String sound_effect) {
        this.sound_effect = sound_effect;
    }
    public String getHot() {
        return this.hot;
    }
    public void setHot(String hot) {
        this.hot = hot;
    }
    public int getCollect_num() {
        return this.collect_num;
    }
    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
    }
    public String getDel_status() {
        return this.del_status;
    }
    public void setDel_status(String del_status) {
        this.del_status = del_status;
    }
    public String getResource_type() {
        return this.resource_type;
    }
    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }
    public String getResource_type_ext() {
        return this.resource_type_ext;
    }
    public void setResource_type_ext(String resource_type_ext) {
        this.resource_type_ext = resource_type_ext;
    }
    public String getSong_id() {
        return this.song_id;
    }
    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }
    @Generated(hash = 34619715)
    public SongInfoEntity(String song_id, String resource_type_ext, String resource_type, String del_status, int collect_num, String hot, String sound_effect, String title, String language, int play_type, String country, String biaoshi, String bitrate_fee, String song_source, int is_first_publish, String artist_640_1136, String is_secret, int charge, String album_500_500, String korean_bb_song, int has_mv_mobile, String album_no, String is_charge, String pic_radio, String has_filmtv, String pic_huge, String ting_uid,
            int expire, String pic_singer, String si_proxycompany, String compose, String toneid, String area, String original_rate, String artist_500_500, String multiterminal_copytype, int has_mv, String album_title, String piao_id, String high_rate, String compress_status, String lrclink, String artist_480_800, String relate_status, int learn, String pic_big, String artist, String aliasname, int comment_num, String album_1000_1000, String album_id, int share_num, String pic_premium, String all_rate, String author,
            String share_url, String all_artist_id, String songwriting, String publishtime, String copy_type, String artist_1000_1000, String versions, String file_duration, String artist_id, int original, String distribution, String pic_small, String info, int havehigh, String bitrate) {
        this.song_id = song_id;
        this.resource_type_ext = resource_type_ext;
        this.resource_type = resource_type;
        this.del_status = del_status;
        this.collect_num = collect_num;
        this.hot = hot;
        this.sound_effect = sound_effect;
        this.title = title;
        this.language = language;
        this.play_type = play_type;
        this.country = country;
        this.biaoshi = biaoshi;
        this.bitrate_fee = bitrate_fee;
        this.song_source = song_source;
        this.is_first_publish = is_first_publish;
        this.artist_640_1136 = artist_640_1136;
        this.is_secret = is_secret;
        this.charge = charge;
        this.album_500_500 = album_500_500;
        this.korean_bb_song = korean_bb_song;
        this.has_mv_mobile = has_mv_mobile;
        this.album_no = album_no;
        this.is_charge = is_charge;
        this.pic_radio = pic_radio;
        this.has_filmtv = has_filmtv;
        this.pic_huge = pic_huge;
        this.ting_uid = ting_uid;
        this.expire = expire;
        this.pic_singer = pic_singer;
        this.si_proxycompany = si_proxycompany;
        this.compose = compose;
        this.toneid = toneid;
        this.area = area;
        this.original_rate = original_rate;
        this.artist_500_500 = artist_500_500;
        this.multiterminal_copytype = multiterminal_copytype;
        this.has_mv = has_mv;
        this.album_title = album_title;
        this.piao_id = piao_id;
        this.high_rate = high_rate;
        this.compress_status = compress_status;
        this.lrclink = lrclink;
        this.artist_480_800 = artist_480_800;
        this.relate_status = relate_status;
        this.learn = learn;
        this.pic_big = pic_big;
        this.artist = artist;
        this.aliasname = aliasname;
        this.comment_num = comment_num;
        this.album_1000_1000 = album_1000_1000;
        this.album_id = album_id;
        this.share_num = share_num;
        this.pic_premium = pic_premium;
        this.all_rate = all_rate;
        this.author = author;
        this.share_url = share_url;
        this.all_artist_id = all_artist_id;
        this.songwriting = songwriting;
        this.publishtime = publishtime;
        this.copy_type = copy_type;
        this.artist_1000_1000 = artist_1000_1000;
        this.versions = versions;
        this.file_duration = file_duration;
        this.artist_id = artist_id;
        this.original = original;
        this.distribution = distribution;
        this.pic_small = pic_small;
        this.info = info;
        this.havehigh = havehigh;
        this.bitrate = bitrate;
    }
    @Generated(hash = 460387546)
    public SongInfoEntity() {
    }

}
