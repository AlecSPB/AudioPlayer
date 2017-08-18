package com.tc.librecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.tc.librecyclerview.adapter.HeaderFooterAdapter;


/**
 * Date:15/11/11
 * Time:15:37
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public class HeaderFooterRcview extends LinearRecyclerView {

    protected HeaderFooterAdapter mHeaderFooterAdapter;

    public HeaderFooterRcview(Context context) {
        super(context);
    }

    public HeaderFooterRcview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderFooterRcview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if(adapter == null){
            super.setAdapter(null);
            return;
        }
        if (!(adapter instanceof HeaderFooterAdapter)) {
            throw new UnsupportedOperationException("HeaderFooterRcview only support HeaderFooterAdapter!");
        }
        super.setAdapter(adapter);
        this.mHeaderFooterAdapter = (HeaderFooterAdapter) adapter;
    }

    @Override
    protected void onSrollPinnedHeader(int mVisibleItemCount, int mFirstVisibleItem) {
        mPinnedSectionHeaderHelper.onScrolled(this,
                getOritation(),
                mPinnedSectionedHeaderAdapter, mFirstVisibleItem, mVisibleItemCount, getHeaderCount());
    }

    public void addHeader(View header) {
        mHeaderFooterAdapter.addHeaderView(header);
    }

    public void removeHeader(View header) {
        mHeaderFooterAdapter.removeHeaderView(header);
    }

    public void addFooter(View footer) {
        mHeaderFooterAdapter.addFooterView(footer);
    }

    public void removeFooter(View footer) {
        mHeaderFooterAdapter.removeFooterView(footer);
    }

    public int getHeaderCount() {
        return mHeaderFooterAdapter.getHeaderCount();
    }

    public int getFooterCount() {
        return mHeaderFooterAdapter.getFooterCount();
    }
}
