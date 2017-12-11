package com.tc.audioplayer.bussiness.artist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.R;
import com.tc.audioplayer.bussiness.fav.FavHelper;
import com.tc.audioplayer.utils.AdMobUtils;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.SongEntity;

/**
 * Created by itcayman on 2017/9/24.
 */

public class ArtistDetailAdapter extends HeaderFooterAdapter<Object> {
    private boolean favVisiable = true;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_AD = 1;

    public ArtistDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                return mInflater.inflate(R.layout.item_artist_detail, parent, false);
            case TYPE_AD:
                return mInflater.inflate(R.layout.ad_hot, parent, false);
        }
        return mInflater.inflate(R.layout.item_artist_detail, parent, false);
    }


    @Override
    public int getListItemViewHolderType(int dataIndex) {
        Object object = getItem(dataIndex);
        if (object instanceof SongEntity) {
            return TYPE_ITEM;
        } else if (object instanceof NativeContentAd) {
            return TYPE_AD;
        }
        return TYPE_ITEM;
    }

    public void setFavVisibility(boolean visibility) {
        this.favVisiable = visibility;
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        Object ob = getItem(dataIndex);
        if (ob instanceof SongEntity) {
            SongEntity item = (SongEntity) ob;
            holder.setText(R.id.tv_title, item.title);
            int number = getItemCount() > 20 && dataIndex > 14 ? dataIndex : dataIndex + 1;
            holder.setText(R.id.tv_number, String.valueOf(number));
            holder.setText(R.id.tv_album, item.album_title);
            if (favVisiable) {
                holder.getView(R.id.iv_fav).setVisibility(View.VISIBLE);
                holder.getView(R.id.iv_fav).setTag(item);
                holder.getView(R.id.iv_fav).setSelected(FavHelper.isFav(item));
                holder.getView(R.id.iv_fav).setOnClickListener(favClickListener);
            } else {
                holder.getView(R.id.iv_fav).setVisibility(View.GONE);
            }
        } else if (ob instanceof NativeContentAd) {
            NativeContentAdView view = (NativeContentAdView) holder.getView();
            NativeContentAd ad = (NativeContentAd) ob;
            AdMobUtils.populateContentAdView(ad, view, false);
        }
    }

    private View.OnClickListener favClickListener = (v) -> {
        SongEntity songEntity = (SongEntity) v.getTag();
        boolean isFav = v.isSelected();
        if (isFav) {
            FavHelper.unfavSong(songEntity);
        } else {
            FavHelper.favSong(mContext, songEntity);
        }
        notifyDataSetChanged();
    };
}
