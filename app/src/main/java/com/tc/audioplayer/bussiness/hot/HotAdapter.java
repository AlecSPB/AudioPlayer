package com.tc.audioplayer.bussiness.hot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tc.audioplayer.R;
import com.tc.audioplayer.bussiness.FavHelper;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.SongEntity;

/**
 * Created by tianchao on 2017/8/5.
 */

public class HotAdapter extends HeaderFooterAdapter<SongEntity> {

    public HotAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_hot_music, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        SongEntity item = getItem(dataIndex);
        holder.setText(R.id.tv_title, item.title);
        holder.setText(R.id.tv_author, item.author);
        holder.getView(R.id.iv_fav).setTag(item);
        holder.getView(R.id.iv_fav).setOnClickListener(favClickListener);
        if (dataIndex == 0) {
            holder.setImageResource(R.id.iv_number, R.drawable.ic_number1);
            holder.getView(R.id.iv_number).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_number).setVisibility(View.INVISIBLE);
        } else if (dataIndex == 1) {
            holder.setImageResource(R.id.iv_number, R.drawable.ic_number2);
            holder.getView(R.id.iv_number).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_number).setVisibility(View.INVISIBLE);
        } else if (dataIndex == 2) {
            holder.setImageResource(R.id.iv_number, R.drawable.ic_number3);
            holder.getView(R.id.iv_number).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_number).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.iv_number).setVisibility(View.INVISIBLE);
            holder.getView(R.id.tv_number).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_number, String.valueOf(dataIndex + 1));
        }
        holder.getView(R.id.iv_fav).setTag(item);
        holder.getView(R.id.iv_fav).setSelected(FavHelper.isFav(item));
        holder.getView(R.id.iv_fav).setOnClickListener(favClickListener);
    }

    private View.OnClickListener favClickListener = (view) -> {
        SongEntity item = (SongEntity) view.getTag();
        boolean isFav = view.isSelected();
        if (isFav) {
            view.setSelected(!FavHelper.unfavSong(item));
        } else {
            view.setSelected(FavHelper.favSong(mContext, item));
        }
    };
}
