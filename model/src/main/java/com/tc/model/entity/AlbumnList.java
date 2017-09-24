package com.tc.model.entity;

import com.tc.model.net.ParseNodePath;

import java.util.List;

/**
 * Created by itcayman on 2017/9/24.
 */

@ParseNodePath(path = ":plaze_album_list:RM:album_list")
public class AlbumnList {
    public List<Albumn> list;
    public int havemore;
}
