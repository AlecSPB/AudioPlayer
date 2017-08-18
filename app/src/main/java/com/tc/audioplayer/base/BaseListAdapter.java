package com.tc.audioplayer.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;

/**
 * Created by tianchao on 2017/8/5.
 */

public class BaseListAdapter extends HeaderFooterAdapter<Object> {
    private IListItemModel model;

    public BaseListAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void bindListItemData(RecyclerViewHolder viewHolder, int dataIndex) {

    }
}
