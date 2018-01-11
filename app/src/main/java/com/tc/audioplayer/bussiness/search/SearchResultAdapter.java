package com.tc.audioplayer.bussiness.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.tc.audioplayer.R;
import com.tc.audioplayer.utils.AdMobUtils;
import com.tc.audioplayer.utils.StringUtils;
import com.tc.librecyclerview.adapter.HeaderFooterAdapter;
import com.tc.librecyclerview.adapter.RecyclerViewHolder;
import com.tc.model.entity.ArtistEntity;
import com.tc.model.entity.SearchWrapper;
import com.tc.model.entity.SongEntity;

/**
 * Created by tianchao on 2017/8/5.
 */

public class SearchResultAdapter extends HeaderFooterAdapter<Object> {
    public static final int TYPE_SONG = 0;
    public static final int TYPE_ARTIST = 1;
    public static final int TYPE_ALBUM = 2;
    public static final int TYPE_AD = 3;
    public static final int TYPE_AD_INSTALL = 4;

    public SearchResultAdapter(Context context) {
        super(context);
    }

    @Override
    public View inflaterListItemView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SONG:
                return mInflater.inflate(R.layout.item_music, parent, false);
            case TYPE_ARTIST:
                return mInflater.inflate(R.layout.item_search_artist, parent, false);
            case TYPE_ALBUM:
                return mInflater.inflate(R.layout.item_search_album, parent, false);
            case TYPE_AD:
                return mInflater.inflate(R.layout.ad_native_content, parent, false);
            case TYPE_AD_INSTALL:
                return mInflater.inflate(R.layout.ad_native_app_install2, parent, false);
        }
        return mInflater.inflate(R.layout.item_music, parent, false);
    }

    @Override
    public int getListItemViewHolderType(int dataIndex) {
        Object object = getItemFromViewIndex(dataIndex);
        if (object instanceof SongEntity) {
            return TYPE_SONG;
        } else if (object instanceof ArtistEntity) {
            return TYPE_ARTIST;
        } else if (object instanceof SearchWrapper.ResultBean.AlbumInfoBean.AlbumListBean) {
            return TYPE_ALBUM;
        } else if (object instanceof NativeContentAd) {
            return TYPE_AD;
        } else if(object instanceof NativeAppInstallAd){
            return TYPE_AD_INSTALL;
        }
        return -1;
    }

    @Override
    public void bindListItemData(RecyclerViewHolder holder, int dataIndex) {
        int type = getListItemViewHolderType(dataIndex);
        switch (type) {
            case TYPE_SONG:
                bindSong(dataIndex, holder);
                break;
            case TYPE_ARTIST:
                bindArtist(dataIndex, holder);
                break;
            case TYPE_ALBUM:
                bindAlbum(dataIndex, holder);
                break;
            case TYPE_AD:
                NativeContentAdView view = (NativeContentAdView) holder.getView();
                NativeContentAd ad = (NativeContentAd) getItem(dataIndex);
                AdMobUtils.populateContentAdView(ad, view, false);
                break;
            case TYPE_AD_INSTALL:
                NativeAppInstallAdView installAdView = (NativeAppInstallAdView) holder.getView();
                NativeAppInstallAd adInstall = (NativeAppInstallAd) getItem(dataIndex);
                AdMobUtils.populateInstallAdView2(adInstall, installAdView);
                break;
        }
    }

    private void bindSong(int dataIndex, RecyclerViewHolder holder) {
        SongEntity item = (SongEntity) getItemFromViewIndex(dataIndex);
        holder.setText(R.id.tv_title, item.title);
        String author = TextUtils.isEmpty(item.album_title) ? item.author : item.author + "-" + item.album_title;
        holder.setText(R.id.tv_author, author);
        Glide.with(mContext)
                .load(item.pic_small)
                .placeholder(R.drawable.default_cover)
                .into((ImageView) holder.getView(R.id.iv_avatar));
        holder.getView(R.id.iv_more).setVisibility(View.GONE);
    }

    private void bindArtist(int dataIndex, RecyclerViewHolder holder) {
        ArtistEntity item = (ArtistEntity) getItemFromViewIndex(dataIndex);
        item.author = StringUtils.replaceEm(item.author);
        holder.setText(R.id.tv_title, item.author);
        holder.setText(R.id.tv_author, item.country);
        Glide.with(mContext)
                .load(item.avatar_middle)
                .placeholder(R.drawable.default_cover)
                .into((ImageView) holder.getView(R.id.iv_avatar));
    }

    private void bindAlbum(int dataIndex, RecyclerViewHolder holder) {
        SearchWrapper.ResultBean.AlbumInfoBean.AlbumListBean item = (SearchWrapper.ResultBean.AlbumInfoBean.AlbumListBean) getItemFromViewIndex(dataIndex);
        item.title = StringUtils.replaceEm(item.title);
        item.author = StringUtils.replaceEm(item.author);
        holder.setText(R.id.tv_title, item.title);
        holder.setText(R.id.tv_author, item.author);
        Glide.with(mContext)
                .load(item.pic_small)
                .placeholder(R.drawable.default_cover)
                .into((ImageView) holder.getView(R.id.iv_avatar));
    }
}
