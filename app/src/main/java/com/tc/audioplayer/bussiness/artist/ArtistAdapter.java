package com.tc.audioplayer.bussiness.artist;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.audioplayer.utils.DimenUtils;
import com.tc.base.utils.DeviceUtils;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.ArtistEntity;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by itcayman on 2017/9/24.
 */

public class ArtistAdapter extends HeaderFooterAdapter<ArtistEntity> {
    private int margin;
    private int imgWidth;

    public ArtistAdapter(Context context) {
        super(context);
        margin = DimenUtils.dp2px(context, 5);
        imgWidth = (DeviceUtils.getScreenWidthPx(context) - margin * 2) / 3;
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_artist, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        ArtistEntity item = getItem(dataIndex);
        ImageView ivArtist = holder.getView(R.id.iv_artist);
        String pic = item.avatar_big;
        int index = pic.indexOf(",w_");
        if (index > 0) {
            pic = pic.substring(0, index) + ",w_" + imgWidth + "," + "h_" + imgWidth;
        }
        Glide.with(mContext)
                .load(pic)
                .asBitmap()
                .transform(new RoundedCornersTransformation(mContext, 0, 0))
                .into(ivArtist);
        holder.setText(R.id.tv_title, item.name);
        holder.setText(R.id.tv_country, item.country);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.getView().getLayoutParams();
        int column = dataIndex % 3;
        if (column == 0) {
            params.leftMargin = margin;
            params.rightMargin = 0;
        } else if (column == 1) {
            params.leftMargin = margin;
            params.rightMargin = margin;
        } else {
            params.leftMargin = 0;
            params.rightMargin = margin;
        }
        holder.getView().setLayoutParams(params);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) holder.getView(R.id.iv_artist).getLayoutParams();
        params1.width = imgWidth;
        params1.height = imgWidth;
        holder.getView(R.id.iv_artist).setLayoutParams(params1);
    }
}
