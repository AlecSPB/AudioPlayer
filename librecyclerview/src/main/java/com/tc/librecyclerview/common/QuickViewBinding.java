package com.tc.librecyclerview.common;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tc.librecyclerview.config.ImageLoader;
import com.tc.librecyclerview.config.RecycerViewConfig;


/**
 * Date:16/7/4
 * Time:16:44
 * Author:pengliang
 */
public class QuickViewBinding implements QuickDataBinding {

    private static final ImageLoader imageLoader = RecycerViewConfig.getInstance().getImageLoader();
    protected View view;

    public QuickViewBinding(View view) {
        this.view = view;
    }

    @Override
    public View getView() {
        return view;
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
        return retrieveView(viewId);
    }

    //region 常用操作

    //region common
    public QuickViewBinding setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public QuickViewBinding setBackgroundResource(int viewId, int resourceId) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(resourceId);
        return this;
    }

    public QuickViewBinding setVisibility(int viewId, int visibility) {
        View view = retrieveView(viewId);
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
        return this;
    }

    public QuickViewBinding setTag(int viewId, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(tag);
        return this;
    }

    public QuickViewBinding setTag(int viewId, int key, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public QuickViewBinding setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(onClickListener);
        return this;
    }

    public QuickViewBinding setOnTouchListener(int viewId, View.OnTouchListener onTouchListener) {
        View view = retrieveView(viewId);
        view.setOnTouchListener(onTouchListener);
        return this;
    }

    public QuickViewBinding setOnLongClickListener(int viewId, View.OnLongClickListener onLongClickListener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(onLongClickListener);
        return this;
    }

    public QuickViewBinding setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public QuickViewBinding setAdapter(int viewId, Adapter adapter) {
        AdapterView adapterView = retrieveView(viewId);
        adapterView.setAdapter(adapter);
        return this;
    }

    //endregion

    //region ImageView
    public QuickViewBinding setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public QuickViewBinding setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public QuickViewBinding setImageUrl(int viewId, String imageUrl) {
        ImageView view = retrieveView(viewId);
        imageLoader.load(view, imageUrl);
        return this;
    }

    public QuickViewBinding setImageUrlWithPlaceHolder(int viewId, String imageUrl, int placeResId) {
        ImageView view = retrieveView(viewId);
        if (TextUtils.isEmpty(imageUrl)) {
            view.setImageResource(placeResId);
        } else {
            imageLoader.load(view, imageUrl, placeResId);
        }
        return this;
    }

    public QuickViewBinding setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public QuickViewBinding setImageLevel(int vieId, int level) {
        ImageView view = retrieveView(vieId);
        view.setImageLevel(level);
        return this;
    }
    //endregion

    //region TextView
    public QuickViewBinding setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    public QuickViewBinding setTextColor(int viewId, int textColor) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public QuickViewBinding setTextColorRes(int viewId, int textColorRes) {
        TextView view = retrieveView(viewId);
        view.setTextColor(getView().getContext().getResources().getColor(textColorRes));
        return this;
    }

    public QuickViewBinding setTextSize(int viewId, float size) {
        TextView view = retrieveView(viewId);
        view.setTextSize(size);
        return this;
    }

    //endregion

    //region ProgressBar
    public QuickViewBinding setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    public QuickViewBinding setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    //endregion

    //endregion

    /**
     * 根据id，从layout取出指定类型的View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    protected  <T extends View> T retrieveView(int viewId) {
        return (T) (view.findViewById(viewId));
    }

}
