package com.tc.audioplayer.bussiness.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;

/**
 * Created by tianchao on 2017/10/22.
 */

public class SearchHistoryAdapter extends HeaderFooterAdapter {

    public SearchHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {

    }
}
