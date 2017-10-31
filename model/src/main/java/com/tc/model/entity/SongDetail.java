package com.tc.model.entity;

/**
 * Created by itcayman on 2017/8/19.
 */

public class SongDetail {
    /**
     * songinfo : {"special_type":0,"pic_huge":"http://musicdata.baidu.com/data2/pic/7c0e4039955b69c7e6117743f79d5585/559949681/559949681.jpg@s_1,w_1000,h_1000","ting_uid":"1116","pic_premium":"http://musicdata.baidu.com/data2/pic/7c0e4039955b69c7e6117743f79d5585/559949681/559949681.jpg@s_1,w_500,h_500","havehigh":0,"si_proxycompany":"深圳腾讯计算机系统有限公司索尼音乐","author":"莫文蔚","toneid":"0","has_mv":0,"song_id":"559949687","title":"一生所爱","artist_id":"162","lrclink":"http://musicdata.baidu.com/data2/lrc/5fa48564d9aad008bfb4eb7a0da365d7/559950901/559950901.lrc","relate_status":"0","learn":0,"pic_big":"http://musicdata.baidu.com/data2/pic/7c0e4039955b69c7e6117743f79d5585/559949681/559949681.jpg@s_1,w_150,h_150","play_type":0,"album_id":"559949680","pic_radio":"http://musicdata.baidu.com/data2/pic/7c0e4039955b69c7e6117743f79d5585/559949681/559949681.jpg@s_1,w_300,h_300","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","all_artist_id":"162","all_artist_ting_uid":"1116","piao_id":"0","charge":0,"copy_type":"0","all_rate":"64,128,256","korean_bb_song":"0","is_first_publish":0,"has_mv_mobile":0,"album_title":"天籁 Studio Version ","pic_small":"http://musicdata.baidu.com/data2/pic/7c0e4039955b69c7e6117743f79d5585/559949681/559949681.jpg@s_1,w_90,h_90","album_no":"2","resource_type_ext":"0","resource_type":"0"}
     * error_code : 22000
     * bitrate : {"show_link":"http://zhangmenshiting.baidu.com/data2/music/2305a56de029940d9673c5fecb46538f/559950083/559950083.mp3?xcode=5355500018012c13c6826868de970894","free":0,"song_file_id":559950083,"file_size":2153151,"file_extension":"mp3","file_duration":268,"file_bitrate":64,"file_link":"http://yinyueshiting.baidu.com/data2/music/2305a56de029940d9673c5fecb46538f/559950083/559950083.mp3?xcode=5355500018012c13c6826868de970894","hash":"e2b366b02437f38bf4c7651159d664a74bd100bc"}
     */

    public SongInfoEntity songinfo;
    public int error_code;
    public BitrateBean bitrate;

    public static class BitrateBean {
        /**
         * show_link : http://zhangmenshiting.baidu.com/data2/music/2305a56de029940d9673c5fecb46538f/559950083/559950083.mp3?xcode=5355500018012c13c6826868de970894
         * free : 0
         * song_file_id : 559950083
         * file_size : 2153151
         * file_extension : mp3
         * file_duration : 268
         * file_bitrate : 64
         * file_link : http://yinyueshiting.baidu.com/data2/music/2305a56de029940d9673c5fecb46538f/559950083/559950083.mp3?xcode=5355500018012c13c6826868de970894
         * hash : e2b366b02437f38bf4c7651159d664a74bd100bc
         */

        public String show_link;
        public int free;
        public int song_file_id;
        public int file_size;
        public String file_extension;
        public int file_duration;
        public int file_bitrate;
        public String file_link;
        public String hash;
    }

    /**
     * songurl : {"url":[{"show_link":"http://zhangmenshiting.baidu.com/data2/music/acde8c2f34538234c41893a07945f6b5/540719546/540719546.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":0,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719546,"file_size":1601650,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":40,"file_bitrate":64,"file_link":"http://yinyueshiting.baidu.com/data2/music/acde8c2f34538234c41893a07945f6b5/540719546/540719546.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":1,"hash":"9227e6efeb1dd27c54ce1dd2cd5d95ee192855ab"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/efc490a7ce506659395762fd79575036/540719533/540719533.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":1,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719533,"file_size":3202016,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":80,"file_bitrate":128,"file_link":"http://yinyueshiting.baidu.com/data2/music/efc490a7ce506659395762fd79575036/540719533/540719533.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":0,"hash":"511c02d39d6ea2cb2bf6433f48900c614ea6370b"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/b054f634ef84dff76623d0d7e631fea0/540719516/540719516.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":0,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719516,"file_size":6402747,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":160,"file_bitrate":256,"file_link":"http://yinyueshiting.baidu.com/data2/music/b054f634ef84dff76623d0d7e631fea0/540719516/540719516.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":0,"hash":"5045e8ce79e6e32e7f52abfa8cbb5c5d25f7d6d7"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/e2ecbb5ad54ef14f0d9fb33682abf87c/540719462/540719462.mp3?xcode=fc505a85329505978e84979b09d702df","down_type":2,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719462,"file_size":8003113,"file_extension":"mp3","file_duration":200,"can_see":1,"can_load":true,"preload":200,"file_bitrate":320,"file_link":"http://yinyueshiting.baidu.com/data2/music/e2ecbb5ad54ef14f0d9fb33682abf87c/540719462/540719462.mp3?xcode=fc505a85329505978e84979b09d702df","is_udition_url":0,"hash":"85430deba5472014eada91d52f45da40c6ac5584"},{"show_link":"","down_type":0,"original":0,"free":1,"replay_gain":"0.000000","song_file_id":540719379,"file_size":24403146,"file_extension":"flac","file_duration":200,"can_see":1,"can_load":true,"preload":610,"file_bitrate":976,"file_link":"","is_udition_url":0,"hash":"5a4b7ec2637576275eb23bac932d6503943c536c"}]}
     * error_code : 22000
     * songinfo : {"resource_type_ext":"0","resource_type":"0","del_status":"0","collect_num":129972,"hot":"577812","sound_effect":"","title":"刚好遇见你","language":"国语","play_type":0,"country":"内地","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"artist_640_1136":"","is_secret":"0","charge":0,"album_500_500":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_500,h_500","korean_bb_song":"0","has_mv_mobile":0,"album_no":"1","is_charge":"","pic_radio":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_300,h_300","has_filmtv":"0","pic_huge":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_1000,h_1000","ting_uid":"1078","expire":36000,"pic_singer":"","si_proxycompany":"北京新奥视讯国际文化传媒有限公司","compose":"高进","toneid":"0","area":"0","original_rate":"","artist_500_500":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500","multiterminal_copytype":"","has_mv":0,"album_title":"刚好遇见你","piao_id":"0","high_rate":"320","compress_status":"0","lrclink":"http://musicdata.baidu.com/data2/lrc/b794e0a41a7806a92746d5ac3652dd8c/543756270/543756270.lrc","artist_480_800":"","relate_status":"0","learn":0,"pic_big":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_150,h_150","artist":"李玉刚","artist_list":[{"avatar_mini":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_20","avatar_s300":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_300","ting_uid":"1078","del_status":"0","avatar_s500":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_500","artist_name":"李玉刚","avatar_small":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_48","avatar_s180":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg@s_0,w_180","artist_id":"89"}],"aliasname":"","comment_num":3562,"album_1000_1000":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_1000,h_1000","album_id":"276867491","share_num":6676,"song_id":"276867440","pic_premium":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_500,h_500","all_rate":"64,128,256,320,flac","author":"李玉刚","share_url":"http://music.baidu.com/song/276867440","all_artist_id":"89","songwriting":"高进","publishtime":"2016-11-14","copy_type":"1","artist_1000_1000":"http://musicdata.baidu.com/data2/pic/246707812/246707812.jpg","versions":"","file_duration":"200","artist_id":"89","original":0,"distribution":"0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000","pic_small":"http://musicdata.baidu.com/data2/pic/d59cab8d47b4ae5cd500cbb67de9cc5c/276867491/276867491.jpg@s_1,w_90,h_90","info":"","havehigh":2,"bitrate":"64,128,256,320,976"}
     */


}
