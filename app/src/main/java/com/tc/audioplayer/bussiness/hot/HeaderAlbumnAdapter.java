package com.tc.audioplayer.bussiness.hot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.Album;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by itcayman on 2017/9/24.
 */

public class HeaderAlbumnAdapter extends HeaderFooterAdapter<Album> {
    public HeaderAlbumnAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_album_header, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        Album item = getItem(dataIndex);
        ImageView ivAlbumn = holder.getView(R.id.iv_albumn);
        String pic = item.pic_radio;
        int index = pic.indexOf(",w_");
        if (index > 0) {
            pic = pic.substring(0, index) + ",w_200,h_180";
        }
        Glide.with(mContext)
                .load(pic)
                .asBitmap()
                .transform(new RoundedCornersTransformation(mContext, 10, 0))
                .into(ivAlbumn);
        holder.setText(R.id.tv_title, item.title);
        holder.setText(R.id.tv_author, item.author);
    }
}
