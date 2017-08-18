package com.tc.librecyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Date:15/11/10
 * Time:15:51
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public interface QuickRcOperation {

    /**
     * 根据viewType  inflate一个Item布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    View inflaterItemView(ViewGroup parent, int viewType);


    /**
     * 通过 {@link RecyclerViewHolder}绑定数据
     *
     * @param maoYanViewHolder
     * @param position
     */
    void bindItemData(RecyclerViewHolder maoYanViewHolder, int position);

}
