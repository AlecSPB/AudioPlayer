package com.tc.audioplayer.bussiness.collect;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.CollectSong;
import com.tc.model.entity.SongEntity;

/**
 * Created by tianchao on 2017/8/5.
 */

public class CollectAdapter extends HeaderFooterAdapter<CollectSong> {

    public CollectAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_music, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        holder.getView(R.id.iv_more).setVisibility(View.GONE);
        CollectSong collectSong = getItem(dataIndex);
        SongEntity item = collectSong.getSong();
        if (item == null)
            return;
        holder.setText(R.id.tv_title, item.title);
        holder.setText(R.id.tv_author, item.author + "-" + item.album_title);
        Glide.with(mContext).load(item.pic_small).into((ImageView) holder.getView(R.id.iv_avatar));
    }
}
