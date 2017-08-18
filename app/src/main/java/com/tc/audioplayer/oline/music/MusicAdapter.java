package com.tc.audioplayer.oline.music;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.SongListItemEntity;

/**
 * Created by tianchao on 2017/8/5.
 */

public class MusicAdapter extends HeaderFooterAdapter<SongListItemEntity> {

    public MusicAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_music, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        SongListItemEntity item = getItem(dataIndex);
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_author, item.getAuthor() + "-" + item.getAlbum_title());
        Glide.with(mContext).load(item.getPic_small()).into((ImageView) holder.getView(R.id.iv_avatar));
    }
}
