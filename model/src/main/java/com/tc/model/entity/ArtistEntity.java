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
    public int song_num;
    public String country;
    public String avatar_middle;
    public int album_num;
    public String artist_desc;
    public String author;
    public String artist_source;

    public int area;
    public String firstchar;
    public String name;
    public int islocate;
    public int gender;
    public String avatar_big;
    public int albums_total;
    public int songs_total;

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

    public void setSong_num(int song_num) {
        this.song_num = song_num;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAvatar_middle(String avatar_middle) {
        this.avatar_middle = avatar_middle;
    }

    public void setAlbum_num(int album_num) {
        this.album_num = album_num;
    }

    public void setArtist_desc(String artist_desc) {
        this.artist_desc = artist_desc;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setArtist_source(String artist_source) {
        this.artist_source = artist_source;
    }

    public String getArtist_source() {
        return this.artist_source;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getArtist_desc() {
        return this.artist_desc;
    }

    public int getAlbum_num() {
        return this.album_num;
    }

    public String getAvatar_middle() {
        return this.avatar_middle;
    }

    public String getCountry() {
        return this.country;
    }

    public int getSong_num() {
        return this.song_num;
    }

    public int getSongs_total() {
        return this.songs_total;
    }

    public void setSongs_total(int songs_total) {
        this.songs_total = songs_total;
    }

    public int getAlbums_total() {
        return this.albums_total;
    }

    public void setAlbums_total(int albums_total) {
        this.albums_total = albums_total;
    }

    public String getAvatar_big() {
        return this.avatar_big;
    }

    public void setAvatar_big(String avatar_big) {
        this.avatar_big = avatar_big;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIslocate() {
        return this.islocate;
    }

    public void setIslocate(int islocate) {
        this.islocate = islocate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstchar() {
        return this.firstchar;
    }

    public void setFirstchar(String firstchar) {
        this.firstchar = firstchar;
    }

    public int getArea() {
        return this.area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Generated(hash = 1853380393)
    public ArtistEntity(String artist_id, String avatar_mini, String avatar_s300, String ting_uid,
            String del_status, String avatar_s500, String artist_name, String avatar_small,
            String avatar_s180, int song_num, String country, String avatar_middle, int album_num,
            String artist_desc, String author, String artist_source, int area, String firstchar,
            String name, int islocate, int gender, String avatar_big, int albums_total, int songs_total) {
        this.artist_id = artist_id;
        this.avatar_mini = avatar_mini;
        this.avatar_s300 = avatar_s300;
        this.ting_uid = ting_uid;
        this.del_status = del_status;
        this.avatar_s500 = avatar_s500;
        this.artist_name = artist_name;
        this.avatar_small = avatar_small;
        this.avatar_s180 = avatar_s180;
        this.song_num = song_num;
        this.country = country;
        this.avatar_middle = avatar_middle;
        this.album_num = album_num;
        this.artist_desc = artist_desc;
        this.author = author;
        this.artist_source = artist_source;
        this.area = area;
        this.firstchar = firstchar;
        this.name = name;
        this.islocate = islocate;
        this.gender = gender;
        this.avatar_big = avatar_big;
        this.albums_total = albums_total;
        this.songs_total = songs_total;
    }

    @Generated(hash = 1273112035)
    public ArtistEntity() {
    }
}
