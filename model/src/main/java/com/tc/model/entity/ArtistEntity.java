package com.tc.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tianchao on 2017/8/3.
 */

@Entity
public class ArtistEntity {
    /**
     * avatar_mini : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_20
     * avatar_s300 : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_300
     * ting_uid : 1078
     * del_status : 0
     * avatar_s500 : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500
     * artist_name : 李玉刚
     * avatar_small : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_48
     * avatar_s180 : http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_180
     * artist_id : 89
     */

    @Id
    public String artist_id;
    public String avatar_mini;
    public String avatar_s300;
    public String ting_uid;
    public String del_status;
    public String avatar_s500;
    public String artist_name;
    public String avatar_small;
    public String avatar_s180;
    public String getAvatar_s180() {
        return this.avatar_s180;
    }
    public void setAvatar_s180(String avatar_s180) {
        this.avatar_s180 = avatar_s180;
    }
    public String getAvatar_small() {
        return this.avatar_small;
    }
    public void setAvatar_small(String avatar_small) {
        this.avatar_small = avatar_small;
    }
    public String getArtist_name() {
        return this.artist_name;
    }
    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
    public String getAvatar_s500() {
        return this.avatar_s500;
    }
    public void setAvatar_s500(String avatar_s500) {
        this.avatar_s500 = avatar_s500;
    }
    public String getDel_status() {
        return this.del_status;
    }
    public void setDel_status(String del_status) {
        this.del_status = del_status;
    }
    public String getTing_uid() {
        return this.ting_uid;
    }
    public void setTing_uid(String ting_uid) {
        this.ting_uid = ting_uid;
    }
    public String getAvatar_s300() {
        return this.avatar_s300;
    }
    public void setAvatar_s300(String avatar_s300) {
        this.avatar_s300 = avatar_s300;
    }
    public String getAvatar_mini() {
        return this.avatar_mini;
    }
    public void setAvatar_mini(String avatar_mini) {
        this.avatar_mini = avatar_mini;
    }
    public String getArtist_id() {
        return this.artist_id;
    }
    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }
    @Generated(hash = 1683919058)
    public ArtistEntity(String artist_id, String avatar_mini, String avatar_s300,
            String ting_uid, String del_status, String avatar_s500, String artist_name,
            String avatar_small, String avatar_s180) {
        this.artist_id = artist_id;
        this.avatar_mini = avatar_mini;
        this.avatar_s300 = avatar_s300;
        this.ting_uid = ting_uid;
        this.del_status = del_status;
        this.avatar_s500 = avatar_s500;
        this.artist_name = artist_name;
        this.avatar_small = avatar_small;
        this.avatar_s180 = avatar_s180;
    }
    @Generated(hash = 1273112035)
    public ArtistEntity() {
    }
}
