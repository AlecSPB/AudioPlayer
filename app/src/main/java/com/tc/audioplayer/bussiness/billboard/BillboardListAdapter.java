package com.tc.audioplayer.bussiness.billboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tc.audioplayer.R;
import com.tc.audioplayer.bussiness.FavHelper;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.BillboardEntity;
import com.tc.model.entity.SongEntity;

/**
 * Created by itcayman on 2017/9/22.
 */

public class BillboardListAdapter extends HeaderFooterAdapter<Object> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_LINE = 2;

    public BillboardListAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return mInflater.inflate(R.layout.item_billboard_header, parent, false);
        } else if (viewType == TYPE_CONTENT) {
            return mInflater.inflate(R.layout.item_billboard_content, parent, false);
        } else if (viewType == TYPE_LINE) {
            return mInflater.inflate(R.layout.item_billboard_line, parent, false);
        }
        return null;
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        Object item = getItem(dataIndex);
        if (item instanceof BillboardEntity) {
            BillboardEntity header = (BillboardEntity) item;
            holder.setText(R.id.tv_name, header.name);
        } else if (item instanceof BillboardEntity.ContentBean) {
            BillboardEntity.ContentBean content = (BillboardEntity.ContentBean) item;
            holder.setText(R.id.tv_name, content.title);
            holder.setText(R.id.tv_author, content.author);
            holder.setText(R.id.tv_number, String.valueOf(content.number));
            holder.getView(R.id.iv_fav).setTag(content);
            holder.getView(R.id.iv_fav).setSelected(FavHelper.isFav(content));
            holder.getView(R.id.iv_fav).setOnClickListener(favClickListener);
        }
    }

    @Override
    public int getListItemViewHolderType(int dataIndex) {
        Object item = getItem(dataIndex);
        if (item instanceof BillboardEntity) {
            return TYPE_HEADER;
        } else if (item instanceof BillboardEntity.ContentBean) {
            return TYPE_CONTENT;
        } else if (item instanceof String) {
            return TYPE_LINE;
        }
        return -1;
    }

    private View.OnClickListener favClickListener = (v) -> {
        BillboardEntity.ContentBean entity = (BillboardEntity.ContentBean) v.getTag();
        SongEntity songEntity = FavHelper.revert(entity);
        boolean isFav = v.isSelected();
        if (isFav) {
            FavHelper.unfavSong(songEntity);
        } else {
            FavHelper.favSong(mContext, songEntity);
        }
        notifyDataSetChanged();
    };

}
