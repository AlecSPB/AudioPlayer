package com.tc.model.entity;

import com.tc.model.net.ParseNodePath;

/**
 * Created by itcayman on 2017/10/23.
 */

@ParseNodePath(path = ":result")
public class HotSearch {
    public int strong;
    public String word;
    public int linktype;
    public String linkurl;
}
