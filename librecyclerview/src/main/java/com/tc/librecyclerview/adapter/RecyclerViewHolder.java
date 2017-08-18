package com.tc.librecyclerview.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;

import com.tc.librecyclerview.common.QuickAdapterViewBinding;
import com.tc.librecyclerview.common.QuickDataBinding;


/**
 * Author:彭亮
 * Date:15-6-15
 * Time:22:15
 * ～冰冻三尺非一日之寒，水滴石穿非一日之功～
 * <p/>
 * 针对RecyclerView的通用Holder
 * 配合 {@link BaseRecyclerAdapter} 使用，调用者无需自己去继承{@link RecyclerView.ViewHolder}
 * 提供接口直接访问
 * itemView中的相关子View。
 * 在 {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)} 进行数据和
 * UI绑定时，可以直接
 * holder.setText(R.id.tv_movie_name,"some text").setVisibility(R.id.iv_movie_avator,View.GONE)...
 * <p/>
 * {@link #setImageUrl(int, String)} 和 {@link #setImageUrlWithPlaceHolder(int, String, int)}
 * 提供对ImageView进行网络图片Loader功能
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements QuickDataBinding {

    private QuickAdapterViewBinding quickAdapterViewBindingProxy;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.quickAdapterViewBindingProxy = new QuickAdapterViewBinding(itemView);
    }

    @Override
    public View getView() {
        return quickAdapterViewBindingProxy.getView();
    }

    /**
     * 获取当前layout中指定id的View,在Holder中定义了许多常用的操作，如：通过setText(viewId,value)
     * 设置指定id的textview的文字
     * 如果不能包含你所需要的操作，请 getView(ViewId).set***(value)
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return quickAdapterViewBindingProxy.getView(viewId);
    }

    //region 常用操作

    //region common
    public RecyclerViewHolder setBackgroundColor(int viewId, int color) {
        quickAdapterViewBindingProxy.setBackgroundColor(viewId,color);
        return this;
    }

    public RecyclerViewHolder setBackgroundResource(int viewId, int resourceId) {
        quickAdapterViewBindingProxy.setBackgroundResource(viewId,resourceId);
        return this;
    }

    public RecyclerViewHolder setVisibility(int viewId, int visibility) {
        quickAdapterViewBindingProxy.setVisibility(viewId, visibility);
        return this;
    }

    public RecyclerViewHolder setTag(int viewId, Object tag) {
        quickAdapterViewBindingProxy.setTag(viewId, tag);
        return this;
    }

    public RecyclerViewHolder setTag(int viewId, int key, Object tag) {
        quickAdapterViewBindingProxy.setTag(viewId, key, tag);
        return this;
    }

    public RecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        quickAdapterViewBindingProxy.setOnClickListener(viewId,onClickListener);
        return this;
    }

    public RecyclerViewHolder setOnTouchListener(int viewId, View.OnTouchListener onTouchListener) {
        quickAdapterViewBindingProxy.setOnTouchListener(viewId, onTouchListener);
        return this;
    }

    public RecyclerViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener onLongClickListener) {
        quickAdapterViewBindingProxy.setOnLongClickListener(viewId, onLongClickListener);
        return this;
    }

    public RecyclerViewHolder setAlpha(int viewId, float value) {
        quickAdapterViewBindingProxy.setAlpha(viewId,value);
        return this;
    }

    public RecyclerViewHolder setAdapter(int viewId, Adapter adapter) {
        quickAdapterViewBindingProxy.setAdapter(viewId, adapter);
        return this;
    }

    //endregion

    //region ImageView
    public RecyclerViewHolder setImageResource(int viewId, int imageResId) {
        quickAdapterViewBindingProxy.setImageResource(viewId, imageResId);
        return this;
    }

    public RecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        quickAdapterViewBindingProxy.setImageDrawable(viewId, drawable);
        return this;
    }

    public RecyclerViewHolder setImageUrl(int viewId, String imageUrl) {
        quickAdapterViewBindingProxy.setImageUrl(viewId, imageUrl);
        return this;
    }

    public RecyclerViewHolder setImageUrlWithPlaceHolder(int viewId, String imageUrl, int placeResId) {
        quickAdapterViewBindingProxy.setImageUrlWithPlaceHolder(viewId,imageUrl,placeResId);
        return this;
    }

    public RecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        quickAdapterViewBindingProxy.setImageBitmap(viewId, bitmap);
        return this;
    }

    public RecyclerViewHolder setImageLevel(int vieId, int level) {
        quickAdapterViewBindingProxy.setImageLevel(vieId, level);
        return this;
    }
    //endregion

    //region TextView
    public RecyclerViewHolder setText(int viewId, String value) {
        quickAdapterViewBindingProxy.setText(viewId,value);
        return this;
    }

    public RecyclerViewHolder setTextColor(int viewId, int textColor) {
        quickAdapterViewBindingProxy.setTextColor(viewId, textColor);
        return this;
    }

    public RecyclerViewHolder setTextColorRes(int viewId, int textColorRes) {
        quickAdapterViewBindingProxy.setTextColorRes(viewId,textColorRes);
        return this;
    }

    public RecyclerViewHolder setTextSize(int viewId, float size) {
        quickAdapterViewBindingProxy.setTextSize(viewId,size);
        return this;
    }

    //endregion

    //region ProgressBar
    public RecyclerViewHolder setProgress(int viewId, int progress) {
        quickAdapterViewBindingProxy.setProgress(viewId, progress);
        return this;
    }

    public RecyclerViewHolder setProgress(int viewId, int progress, int max) {
        quickAdapterViewBindingProxy.setProgress(viewId, progress, max);
        return this;
    }

    //endregion

    //endregion
}