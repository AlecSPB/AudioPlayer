package com.tc.librecyclerview.common;

import android.util.SparseArray;
import android.view.View;

/**
 * Date:16/7/4
 * Time:16:55
 * Author:pengliang
 */
public class QuickAdapterViewBinding extends QuickViewBinding {

    private final SparseArray<View> views;

    public QuickAdapterViewBinding(View view) {
        super(view);
        views = new SparseArray<>();
    }

    @Override
    protected <T extends View> T retrieveView(int viewId) {
        View result = views.get(viewId);
        if (result == null) {
            result = view.findViewById(viewId);
            views.put(viewId, result);
        }
        return (T) result;
    }
}
