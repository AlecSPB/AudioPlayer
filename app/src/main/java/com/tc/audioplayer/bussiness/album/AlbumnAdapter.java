package com.tc.audioplayer.bussiness.album;

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
import com.tc.model.entity.Album;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by itcayman on 2017/9/24.
 */

public class AlbumnAdapter extends HeaderFooterAdapter<Album> {
    private int margin;
    private int imgWidth;

    public AlbumnAdapter(Context context) {
        super(context);
        margin = DimenUtils.dp2px(context, 10);
        imgWidth = (DeviceUtils.getScreenWidthPx(context) - margin * 3) / 2;
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        return mInflater.inflate(R.layout.item_album, parent, false);
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        Album item = getItem(dataIndex);
        ImageView ivAlbumn = holder.getView(R.id.iv_albumn);
        String pic = item.pic_big;
        int index = pic.indexOf(",w_");
        if (index > 0) {
            pic = pic.substring(0, index) + ",w_+" + imgWidth + ",h_" + imgWidth;
        }
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.getView().getLayoutParams();
        int column = dataIndex % 2;
        if (column == 0) {
            params.leftMargin = margin;
            params.rightMargin = margin / 2;
        } else {
            params.leftMargin = margin / 2;
            params.rightMargin = margin;
        }
        holder.getView().setLayoutParams(params);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) ivAlbumn.getLayoutParams();
        params1.width = imgWidth;
        params1.height = imgWidth;
        ivAlbumn.setLayoutParams(params1);
        Glide.with(mContext)
                .load(pic)
                .asBitmap()
                .transform(new RoundedCornersTransformation(mContext, 10, 0))
                .placeholder(R.drawable.default_cover)
                .into(ivAlbumn);
        holder.setText(R.id.tv_title, item.title);
        holder.setText(R.id.tv_author, item.author);
    }
}
