package com.tc.librecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Date:15/11/17
 * Time:10:32
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 * <p/>
 * 悬停Adapter
 * 名词说明：
 * section:一种类型的Item，比如电影列表中某一天的电影就称为一个section
 * position:所有都是在页面中的view位置
 */
public interface PinnedSectionedHeaderAdapter {

    /**
     * 判断一个item是否是一个section的开始，比如通讯录中以某一个字母作为首字母的姓名中的第一个
     *
     * @param position
     * @return
     */
    boolean isSectionHeader(int position);

    /**
     * 找到position的item所在的section，并返回这个section中第一个item的postion
     *
     * @param position
     * @return
     */
    int getSectionForPosition(int position);

    /**
     * 返回section所对应的HeaderView
     *
     * @param position    section的第一个item位置
     * @param convertView HeaderView
     * @param parent      容器，一般是ListView
     * @return
     */
    View getSectionHeaderView(int position, View convertView, ViewGroup parent);

    /**
     * 根据section的索引，获取该section对应的悬停Header的类型
     *
     * @param section
     * @return
     */
    int getSectionHeaderViewType(int section);

    /**
     * 返回整个列表中的数据
     *
     * @return
     */
    int getCount();
}
