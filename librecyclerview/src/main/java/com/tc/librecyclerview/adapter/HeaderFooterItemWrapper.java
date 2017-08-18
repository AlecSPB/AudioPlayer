package com.tc.librecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Date:15/11/10
 * Time:15:57
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 * <p/>
 * 加入Header和Footer之后，需要在加载布局和绑定数据时，去掉Footer和Header所在的Item
 *
 * dataIndex : list数据中的真正位置
 */
public interface HeaderFooterItemWrapper {
    /**
     * 根据viewType  inflate一个list中数据item(非Header Footer)
     *
     * @param parent
     * @param viewType
     * @return
     */
    View inflaterListItemView(ViewGroup parent, int viewType);


    /**
     * 通过 {@link RecyclerViewHolder}绑定数据
     *
     * @param holder
     * @param dataIndex
     */
    void bindListItemData(RecyclerViewHolder holder, int dataIndex);

    /**
     * @param dataIndex 真正List数据的index
     * @return List数据的View的类型，用于复用
     */
    int getListItemViewHolderType(int dataIndex);
}
