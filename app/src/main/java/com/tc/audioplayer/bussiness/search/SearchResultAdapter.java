package com.tc.audioplayer.bussiness.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.SongEntity;

/**
 * Created by tianchao on 2017/8/5.
 */

public class SearchResultAdapter extends HeaderFooterAdapter<SongEntity> {

    public SearchResultAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_music, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        SongEntity item = getItem(dataIndex);
        holder.setText(R.id.tv_title, item.title);
        String author = TextUtils.isEmpty(item.album_title) ? item.author : item.author + "-" + item.album_title;
        holder.setText(R.id.tv_author, author);
        Glide.with(mContext)
                .load(item.pic_small)
                .placeholder(R.drawable.default_cover)
                .into((ImageView) holder.getView(R.id.iv_avatar));
    }
}
