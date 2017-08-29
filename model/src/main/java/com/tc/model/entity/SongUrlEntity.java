package com.tc.model.entity;

/**
 * Created by itcayman on 2017/8/25.
 */

public class SongUrlEntity {
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

    public int song_file_id;
    public String show_link;
    public int down_type;
    public int original;
    public int free;
    public String replay_gain;
    public int file_size;
    public String file_extension;
    public int file_duration;
    public int can_see;
    public boolean can_load;
    public int preload;
    public int file_bitrate;
    public String file_link;
    public int is_udition_url;
    public String hash;
}
