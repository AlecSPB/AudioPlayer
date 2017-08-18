package com.tc.librecyclerview.config;

import android.widget.ImageView;

/**
 * Date:15/9/24
 * Time:15:00
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public interface ImageLoader {

    void load(ImageView imageView, String imageUrl);

    //placeResId  默认图resid
    void load(ImageView imageView, String imageUrl, int placeResId);

    void load(ImageView imageView, String imageUrl, int placeResId, int errorIvd);
}
