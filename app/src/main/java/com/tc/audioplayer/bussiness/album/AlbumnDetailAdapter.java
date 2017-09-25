package com.tc.audioplayer.bussiness.album;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tc.audioplayer.R;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.SongEntity;

/**
 * Created by itcayman on 2017/9/24.
 */

public class AlbumnDetailAdapter extends HeaderFooterAdapter<SongEntity> {
    public AlbumnDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_albumn_detail, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        SongEntity item = getItem(dataIndex);
        holder.setText(R.id.tv_title, item.title);
        holder.setText(R.id.tv_author, item.author);
        holder.setText(R.id.tv_number, String.valueOf(dataIndex + 1));
    }
}
