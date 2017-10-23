package com.tc.audioplayer.bussiness.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tc.audioplayer.R;
import com.tc.audioplayer.event.SearchEvent;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.HotSearch;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tianchao on 2017/10/22.
 */

public class SearchHistoryAdapter extends HeaderFooterAdapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;


    public SearchHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return mInflater.inflate(R.layout.item_search_history_header, parent, false);
            case TYPE_ITEM:
                return mInflater.inflate(R.layout.item_search_history, parent, false);
        }
        return mInflater.inflate(R.layout.item_search_history, parent, false);
    }

    @Override
    public int getListItemViewHolderType(int dataIndex) {
        Object object = getItem(dataIndex);
        return object instanceof SearchHeader ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        int type = getListItemViewHolderType(dataIndex);
        Object item = getItem(dataIndex);
        switch (type) {
            case TYPE_HEADER:
                SearchHeader header = (SearchHeader) item;
                holder.setText(R.id.tv_title, header.name);
                holder.getView(R.id.tv_clear).setVisibility(header.type == 0 ? View.VISIBLE : View.GONE);
                holder.getView(R.id.tv_clear).setOnClickListener((v) -> {
                    SearchPresent.clearSearchHistory();
                    EventBus.getDefault().post(new SearchEvent());
                });
                break;
            case TYPE_ITEM:
                if (item instanceof String) {
                    holder.setText(R.id.tv_name, (String) item);
                } else {
                    HotSearch hot = (HotSearch) getItem(dataIndex);
                    holder.setText(R.id.tv_name, hot.word);
                }
                break;
        }
    }

    public static class SearchHeader {
        public String name;
        public int type;

        public SearchHeader(String name, int type) {
            this.name = name;
            this.type = type;
        }
    }
}
