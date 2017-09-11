package com.tc.model.entity;

import java.util.List;

/**
 * Created by tianchao on 2017/8/3.
 * 搜索
 */

public class SearchWrapper {

    public int error_code;
    public ResultBean result;

    public static class ResultBean {


        public TagInfoBean tag_info;
        public VideoInfoBean video_info;
        public String syn_words;
        public TopicInfoBean topic_info;
        public String query;
        public UserInfoBean user_info;
        public AlbumInfoBean album_info;
        public int rqt_type;
        public SongInfoEntity song_info;
        public PlaylistInfoEntity playlist_info;
        public ArtistInfoEntity artist_info;

        public static class TagInfoBean {
            /**
             * total : 0
             */

            public int total;
        }

        public static class VideoInfoBean {
            public int total;
            public List<VideoListBean> video_list;

            public static class VideoListBean {

                public String artist;
                public String mv_id;
                public String title;
                public String thumbnail;
                public String thumbnail2;
                public String duration;
                public String all_artist_id;
            }
        }

        public static class TopicInfoBean {

            public int total;
            public List<TopicListBean> topic_list;

            public static class TopicListBean {
                /**
                 * pic :
                 * pv_nums : 17
                 * topic_id : 185
                 * min_pic :
                 * topic_title : <em>白</em>日梦想家
                 * nums : 17
                 */

                public String pic;
                public String pv_nums;
                public String topic_id;
                public String min_pic;
                public String topic_title;
                public String nums;
            }
        }

        public static class UserInfoBean {

            public int total;
            public List<UserListBean> user_list;

            public static class UserListBean {
                /**
                 * level :
                 * userpic : http://himg.bdimg.com/sys/portrait/item/ac12e799bdb738.jpg
                 * flag : 000
                 * renzheng_info :
                 * follow_num : 0
                 * friend_num : 2
                 * dynamic_num : 0
                 * desc :
                 * isFriend : 0
                 * province :
                 * sex : 0
                 * tag :
                 * username : <em>白</em>
                 * userid : 951521964
                 * bth_info : 631166400
                 */

                public String level;
                public String userpic;
                public String flag;
                public String renzheng_info;
                public int follow_num;
                public int friend_num;
                public int dynamic_num;
                public String desc;
                public int isFriend;
                public String province;
                public int sex;
                public String tag;
                public String username;
                public String userid;
                public int bth_info;
            }
        }

        public static class AlbumInfoBean {
            public int total;
            public List<AlbumListBean> album_list;

            public static class AlbumListBean {
                public String resource_type_ext;
                public String all_artist_id;
                public String publishtime;
                public String company;
                public String album_desc;
                public String title;
                public String album_id;
                public String pic_small;
                public int hot;
                public String author;
                public String artist_id;
            }
        }

        public static class SongInfoEntity {
            public int total;
            public List<SongEntity> song_list;
        }

        public static class PlaylistInfoEntity {
            public int total;
            public List<PlayListEntity> play_list;

            public static class PlayListEntity {
                public String firstSongid;
                public String diy_id;
                public String diy_pic;
                public int song_num;
                public String diy_tag;
                public String user_id;
                public UserinfoEntity userinfo;
                public String diy_title;
                public int listen_num;

                public static class UserinfoEntity {
                    public String userpic;
                    public String flag;
                    public String userpic_small;
                    public String userid;
                    public String username;
                }
            }
        }

        public static class ArtistInfoEntity {
            public int total;
            public List<ArtistEntity> artist_list;
        }
    }
}
