package com.tc.model.entity;

import java.util.List;

/**
 * Created by itcayman on 2017/8/19.
 */

public class SongDetail {

    /**
     * songurl : {"url":[{"show_link":"http://zhangmenshiting.baidu.com/data2/music/acde8c2f34538234c41893a07945f6b5/540719546/540719546.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":0,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719546,"file_size":1601650,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":40,"file_bitrate":64,"file_link":"http://yinyueshiting.baidu.com/data2/music/acde8c2f34538234c41893a07945f6b5/540719546/540719546.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":1,"hash":"9227e6efeb1dd27c54ce1dd2cd5d95ee192855ab"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/efc490a7ce506659395762fd79575036/540719533/540719533.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":1,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719533,"file_size":3202016,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":80,"file_bitrate":128,"file_link":"http://yinyueshiting.baidu.com/data2/music/efc490a7ce506659395762fd79575036/540719533/540719533.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":0,"hash":"511c02d39d6ea2cb2bf6433f48900c614ea6370b"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/b054f634ef84dff76623d0d7e631fea0/540719516/540719516.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":0,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719516,"file_size":6402747,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":160,"file_bitrate":256,"file_link":"http://yinyueshiting.baidu.com/data2/music/b054f634ef84dff76623d0d7e631fea0/540719516/540719516.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":0,"hash":"5045e8ce79e6e32e7f52abfa8cbb5c5d25f7d6d7"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/e2ecbb5ad54ef14f0d9fb33682abf87c/540719462/540719462.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":2,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719462,"file_size":8003113,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":200,"file_bitrate":320,"file_link":"http://yinyueshiting.baidu.com/data2/music/e2ecbb5ad54ef14f0d9fb33682abf87c/540719462/540719462.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":0,"hash":"85430deba5472014eada91d52f45da40c6ac5584"},{"show_link":"","down_type":0,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719379,"file_size":24403146,"file_extension":"flac","file_duration":200,"can_see":1,"can_load":true,"preload":610,"file_bitrate":976,"file_link":"","is_udition_url":0,"hash":"5a4b7ec2637576275eb23bac932d6503943c536c"}]}
     * error_code : 22000
     * songinfo : {"resource_type_ext":"0","resource_type":"0","del_status":"0","collect_num":129972,"hot":"577812","sound_effect":"","title":"刚好遇见你","language":"国语","play_type":0,"country":"内地","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"artist_640_1136":"","is_secret":"0","charge":0,"album_500_500":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_500,h_500","korean_bb_song":"0","has_mv_mobile":0,"album_no":"1","is_charge":"","pic_radio":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_300,h_300","has_filmtv":"0","pic_huge":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_1000,h_1000","ting_uid":"1078","expire":36000,"pic_singer":"","si_proxycompany":"北京新奥视讯国际文化传媒有限公司","compose":"高进","toneid":"0","area":"0","original_rate":"","artist_500_500":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500","multiterminal_copytype":"","has_mv":0,"album_title":"刚好遇见你","piao_id":"0","high_rate":"320","compress_status":"0","lrclink":"http://musicdata.baidu.com/data2/lrc/b794e0a41a7806a92746d5ac3652dd8c/543756270/543756270.lrc","artist_480_800":"","relate_status":"0","learn":0,"pic_big":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_150,h_150","artist":"李玉刚","artist_list":[{"avatar_mini":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_20","avatar_s300":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_300","ting_uid":"1078","del_status":"0","avatar_s500":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500","artist_name":"李玉刚","avatar_small":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_48","avatar_s180":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_180","artist_id":"89"}],"aliasname":"","comment_num":3562,"album_1000_1000":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_1000,h_1000","album_id":"276867491","share_num":6676,"song_id":"276867440","pic_premium":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_500,h_500","all_rate":"64,128,256,320,flac","author":"李玉刚","share_url":"http://music.baidu.com/song/276867440","all_artist_id":"89","songwriting":"高进","publishtime":"2016-11-14","copy_type":"1","artist_1000_1000":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg","versions":"","file_duration":"200","artist_id":"89","original":0,"distribution":"0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000","pic_small":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_90,h_90","info":"","havehigh":2,"bitrate":"64,128,256,320,976"}
     */

    private SongurlBean songurl;
    private int error_code;
    private SonginfoBean songinfo;

    public SongurlBean getSongurl() {
        return songurl;
    }

    public void setSongurl(SongurlBean songurl) {
        this.songurl = songurl;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public SonginfoBean getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(SonginfoBean songinfo) {
        this.songinfo = songinfo;
    }

    public static class SongurlBean {
        private List<UrlBean> url;

        public List<UrlBean> getUrl() {
            return url;
        }

        public void setUrl(List<UrlBean> url) {
            this.url = url;
        }

        public static class UrlBean {
            /**
             * show_link : http://zhangmenshiting.baidu.com/data2/music/acde8c2f34538234c41893a07945f6b5/540719546/540719546.mp3?xcode=fc505a85329505978e84979b09d702df
             * down_type : 0
             * original : 0
             * free : 1
             * replay_gain : 0.000000
             * song_file_id : 540719546
             * file_size : 1601650
             * file_extension : mp3
             * file_duration : 200
             * can_see : 1
             * can_load : true
             * preload : 40
             * file_bitrate : 64
             * file_link : http://yinyueshiting.baidu.com/data2/music/acde8c2f34538234c41893a07945f6b5/540719546/540719546.mp3?xcode=fc505a85329505978e84979b09d702df
             * is_udition_url : 1
             * hash : 9227e6efeb1dd27c54ce1dd2cd5d95ee192855ab
             */

            private String show_link;
            private int down_type;
            private int original;
            private int free;
            private String replay_gain;
            private int song_file_id;
            private int file_size;
            private String file_extension;
            private int file_duration;
            private int can_see;
            private boolean can_load;
            private int preload;
            private int file_bitrate;
            private String file_link;
            private int is_udition_url;
            private String hash;

            public String getShow_link() {
                return show_link;
            }

            public void setShow_link(String show_link) {
                this.show_link = show_link;
            }

            public int getDown_type() {
                return down_type;
            }

            public void setDown_type(int down_type) {
                this.down_type = down_type;
            }

            public int getOriginal() {
                return original;
            }

            public void setOriginal(int original) {
                this.original = original;
            }

            public int getFree() {
                return free;
            }

            public void setFree(int free) {
                this.free = free;
            }

            public String getReplay_gain() {
                return replay_gain;
            }

            public void setReplay_gain(String replay_gain) {
                this.replay_gain = replay_gain;
            }

            public int getSong_file_id() {
                return song_file_id;
            }

            public void setSong_file_id(int song_file_id) {
                this.song_file_id = song_file_id;
            }

            public int getFile_size() {
                return file_size;
            }

            public void setFile_size(int file_size) {
                this.file_size = file_size;
            }

            public String getFile_extension() {
                return file_extension;
            }

            public void setFile_extension(String file_extension) {
                this.file_extension = file_extension;
            }

            public int getFile_duration() {
                return file_duration;
            }

            public void setFile_duration(int file_duration) {
                this.file_duration = file_duration;
            }

            public int getCan_see() {
                return can_see;
            }

            public void setCan_see(int can_see) {
                this.can_see = can_see;
            }

            public boolean isCan_load() {
                return can_load;
            }

            public void setCan_load(boolean can_load) {
                this.can_load = can_load;
            }

            public int getPreload() {
                return preload;
            }

            public void setPreload(int preload) {
                this.preload = preload;
            }

            public int getFile_bitrate() {
                return file_bitrate;
            }

            public void setFile_bitrate(int file_bitrate) {
                this.file_bitrate = file_bitrate;
            }

            public String getFile_link() {
                return file_link;
            }

            public void setFile_link(String file_link) {
                this.file_link = file_link;
            }

            public int getIs_udition_url() {
                return is_udition_url;
            }

            public void setIs_udition_url(int is_udition_url) {
                this.is_udition_url = is_udition_url;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }
        }
    }

    public static class SonginfoBean {
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

        private String resource_type_ext;
        private String resource_type;
        private String del_status;
        private int collect_num;
        private String hot;
        private String sound_effect;
        private String title;
        private String language;
        private int play_type;
        private String country;
        private String biaoshi;
        private String bitrate_fee;
        private String song_source;
        private int is_first_publish;
        private String artist_640_1136;
        private String is_secret;
        private int charge;
        private String album_500_500;
        private String korean_bb_song;
        private int has_mv_mobile;
        private String album_no;
        private String is_charge;
        private String pic_radio;
        private String has_filmtv;
        private String pic_huge;
        private String ting_uid;
        private int expire;
        private String pic_singer;
        private String si_proxycompany;
        private String compose;
        private String toneid;
        private String area;
        private String original_rate;
        private String artist_500_500;
        private String multiterminal_copytype;
        private int has_mv;
        private String album_title;
        private String piao_id;
        private String high_rate;
        private String compress_status;
        private String lrclink;
        private String artist_480_800;
        private String relate_status;
        private int learn;
        private String pic_big;
        private String artist;
        private String aliasname;
        private int comment_num;
        private String album_1000_1000;
        private String album_id;
        private int share_num;
        private String song_id;
        private String pic_premium;
        private String all_rate;
        private String author;
        private String share_url;
        private String all_artist_id;
        private String songwriting;
        private String publishtime;
        private String copy_type;
        private String artist_1000_1000;
        private String versions;
        private String file_duration;
        private String artist_id;
        private int original;
        private String distribution;
        private String pic_small;
        private String info;
        private int havehigh;
        private String bitrate;
        private List<ArtistListBean> artist_list;

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getResource_type() {
            return resource_type;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public String getDel_status() {
            return del_status;
        }

        public void setDel_status(String del_status) {
            this.del_status = del_status;
        }

        public int getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(int collect_num) {
            this.collect_num = collect_num;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getSound_effect() {
            return sound_effect;
        }

        public void setSound_effect(String sound_effect) {
            this.sound_effect = sound_effect;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getPlay_type() {
            return play_type;
        }

        public void setPlay_type(int play_type) {
            this.play_type = play_type;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getBiaoshi() {
            return biaoshi;
        }

        public void setBiaoshi(String biaoshi) {
            this.biaoshi = biaoshi;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getSong_source() {
            return song_source;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public int getIs_first_publish() {
            return is_first_publish;
        }

        public void setIs_first_publish(int is_first_publish) {
            this.is_first_publish = is_first_publish;
        }

        public String getArtist_640_1136() {
            return artist_640_1136;
        }

        public void setArtist_640_1136(String artist_640_1136) {
            this.artist_640_1136 = artist_640_1136;
        }

        public String getIs_secret() {
            return is_secret;
        }

        public void setIs_secret(String is_secret) {
            this.is_secret = is_secret;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public String getAlbum_500_500() {
            return album_500_500;
        }

        public void setAlbum_500_500(String album_500_500) {
            this.album_500_500 = album_500_500;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public int getHas_mv_mobile() {
            return has_mv_mobile;
        }

        public void setHas_mv_mobile(int has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public String getAlbum_no() {
            return album_no;
        }

        public void setAlbum_no(String album_no) {
            this.album_no = album_no;
        }

        public String getIs_charge() {
            return is_charge;
        }

        public void setIs_charge(String is_charge) {
            this.is_charge = is_charge;
        }

        public String getPic_radio() {
            return pic_radio;
        }

        public void setPic_radio(String pic_radio) {
            this.pic_radio = pic_radio;
        }

        public String getHas_filmtv() {
            return has_filmtv;
        }

        public void setHas_filmtv(String has_filmtv) {
            this.has_filmtv = has_filmtv;
        }

        public String getPic_huge() {
            return pic_huge;
        }

        public void setPic_huge(String pic_huge) {
            this.pic_huge = pic_huge;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public String getPic_singer() {
            return pic_singer;
        }

        public void setPic_singer(String pic_singer) {
            this.pic_singer = pic_singer;
        }

        public String getSi_proxycompany() {
            return si_proxycompany;
        }

        public void setSi_proxycompany(String si_proxycompany) {
            this.si_proxycompany = si_proxycompany;
        }

        public String getCompose() {
            return compose;
        }

        public void setCompose(String compose) {
            this.compose = compose;
        }

        public String getToneid() {
            return toneid;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getOriginal_rate() {
            return original_rate;
        }

        public void setOriginal_rate(String original_rate) {
            this.original_rate = original_rate;
        }

        public String getArtist_500_500() {
            return artist_500_500;
        }

        public void setArtist_500_500(String artist_500_500) {
            this.artist_500_500 = artist_500_500;
        }

        public String getMultiterminal_copytype() {
            return multiterminal_copytype;
        }

        public void setMultiterminal_copytype(String multiterminal_copytype) {
            this.multiterminal_copytype = multiterminal_copytype;
        }

        public int getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(int has_mv) {
            this.has_mv = has_mv;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public String getPiao_id() {
            return piao_id;
        }

        public void setPiao_id(String piao_id) {
            this.piao_id = piao_id;
        }

        public String getHigh_rate() {
            return high_rate;
        }

        public void setHigh_rate(String high_rate) {
            this.high_rate = high_rate;
        }

        public String getCompress_status() {
            return compress_status;
        }

        public void setCompress_status(String compress_status) {
            this.compress_status = compress_status;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public String getArtist_480_800() {
            return artist_480_800;
        }

        public void setArtist_480_800(String artist_480_800) {
            this.artist_480_800 = artist_480_800;
        }

        public String getRelate_status() {
            return relate_status;
        }

        public void setRelate_status(String relate_status) {
            this.relate_status = relate_status;
        }

        public int getLearn() {
            return learn;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getAliasname() {
            return aliasname;
        }

        public void setAliasname(String aliasname) {
            this.aliasname = aliasname;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public String getAlbum_1000_1000() {
            return album_1000_1000;
        }

        public void setAlbum_1000_1000(String album_1000_1000) {
            this.album_1000_1000 = album_1000_1000;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public int getShare_num() {
            return share_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getPic_premium() {
            return pic_premium;
        }

        public void setPic_premium(String pic_premium) {
            this.pic_premium = pic_premium;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public String getSongwriting() {
            return songwriting;
        }

        public void setSongwriting(String songwriting) {
            this.songwriting = songwriting;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getCopy_type() {
            return copy_type;
        }

        public void setCopy_type(String copy_type) {
            this.copy_type = copy_type;
        }

        public String getArtist_1000_1000() {
            return artist_1000_1000;
        }

        public void setArtist_1000_1000(String artist_1000_1000) {
            this.artist_1000_1000 = artist_1000_1000;
        }

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(String file_duration) {
            this.file_duration = file_duration;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public int getOriginal() {
            return original;
        }

        public void setOriginal(int original) {
            this.original = original;
        }

        public String getDistribution() {
            return distribution;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public String getPic_small() {
            return pic_small;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public String getBitrate() {
            return bitrate;
        }

        public void setBitrate(String bitrate) {
            this.bitrate = bitrate;
        }

        public List<ArtistListBean> getArtist_list() {
            return artist_list;
        }

        public void setArtist_list(List<ArtistListBean> artist_list) {
            this.artist_list = artist_list;
        }

        public static class ArtistListBean {
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

            private String avatar_mini;
            private String avatar_s300;
            private String ting_uid;
            private String del_status;
            private String avatar_s500;
            private String artist_name;
            private String avatar_small;
            private String avatar_s180;
            private String artist_id;

            public String getAvatar_mini() {
                return avatar_mini;
            }

            public void setAvatar_mini(String avatar_mini) {
                this.avatar_mini = avatar_mini;
            }

            public String getAvatar_s300() {
                return avatar_s300;
            }

            public void setAvatar_s300(String avatar_s300) {
                this.avatar_s300 = avatar_s300;
            }

            public String getTing_uid() {
                return ting_uid;
            }

            public void setTing_uid(String ting_uid) {
                this.ting_uid = ting_uid;
            }

            public String getDel_status() {
                return del_status;
            }

            public void setDel_status(String del_status) {
                this.del_status = del_status;
            }

            public String getAvatar_s500() {
                return avatar_s500;
            }

            public void setAvatar_s500(String avatar_s500) {
                this.avatar_s500 = avatar_s500;
            }

            public String getArtist_name() {
                return artist_name;
            }

            public void setArtist_name(String artist_name) {
                this.artist_name = artist_name;
            }

            public String getAvatar_small() {
                return avatar_small;
            }

            public void setAvatar_small(String avatar_small) {
                this.avatar_small = avatar_small;
            }

            public String getAvatar_s180() {
                return avatar_s180;
            }

            public void setAvatar_s180(String avatar_s180) {
                this.avatar_s180 = avatar_s180;
            }

            public String getArtist_id() {
                return artist_id;
            }

            public void setArtist_id(String artist_id) {
                this.artist_id = artist_id;
            }
        }
    }
}
