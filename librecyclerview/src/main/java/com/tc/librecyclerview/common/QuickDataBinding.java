package com.tc.librecyclerview.common;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Adapter;

/**
 * Date:16/7/4
 * Time:16:30
 * Author:pengliang
 *
 * 指定一个View，提供一组统一的快捷数据填充操作，比如：
 * QuickDataBinding quickDataBinding = new QuickDataBindingImpl(your_view);
 * quickDataBinding.setText(R.id.tv_movie_name,"some text").setVisibility(R.id.iv_movie_avator,View.GONE)
 *
 * 如果对View的填充中，有的View的方法没有定义，如View.setOnCreateContextMenuListener,getView()来获取原始View
 * quickDataBinding.getView(R.id.view_id).setOnCreateContextMenuListener即可
 */
public interface QuickDataBinding {

    View getView();

    /**
     * 获取当前View中某一个指定id的View
     * @param viewId
     * @param <T>
     * @return
     */
    <T extends View> T getView(int viewId);

    //region 常用操作

    //region common
     QuickDataBinding setBackgroundColor(int viewId, int color);

    QuickDataBinding setBackgroundResource(int viewId, int resourceId);

    QuickDataBinding setVisibility(int viewId, int visibility);

    QuickDataBinding setTag(int viewId, Object tag);

    QuickDataBinding setTag(int viewId, int key, Object tag);

    QuickDataBinding setOnClickListener(int viewId, View.OnClickListener onClickListener);

    QuickDataBinding setOnTouchListener(int viewId, View.OnTouchListener onTouchListener);

    QuickDataBinding setOnLongClickListener(int viewId, View.OnLongClickListener onLongClickListener);

    QuickDataBinding setAlpha(int viewId, float value);

    QuickDataBinding setAdapter(int viewId, Adapter adapter);

    //endregion

    //region ImageView
    QuickDataBinding setImageResource(int viewId, int imageResId);

    QuickDataBinding setImageDrawable(int viewId, Drawable drawable);

    QuickDataBinding setImageUrl(int viewId, String imageUrl);

    QuickDataBinding setImageUrlWithPlaceHolder(int viewId, String imageUrl, int placeResId);

    QuickDataBinding setImageBitmap(int viewId, Bitmap bitmap);

    QuickDataBinding setImageLevel(int vieId, int level);
    //endregion

    //region TextView
    QuickDataBinding setText(int viewId, String value);

    QuickDataBinding setTextColor(int viewId, int textColor);

    QuickDataBinding setTextColorRes(int viewId, int textColorRes);

    QuickDataBinding setTextSize(int viewId, float size);

    //endregion

    //region ProgressBar
    QuickDataBinding setProgress(int viewId, int progress);

    QuickDataBinding setProgress(int viewId, int progress, int max);
}
