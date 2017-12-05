package com.tc.librecyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Date:15/10/29
 * Time:14:32
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 * Support Header and Footer
 * <p/>
 * indexWithHeaderFooter：界面上面展示的Item的index，所以是包含头部或者Footer的
 * dataIndex:真正list中的数据index
 */
public abstract class HeaderFooterAdapter<D> extends QuickRcAdapter<D>
        implements HeaderFooterItemWrapper {

    private static final int HEADERS_START = Integer.MIN_VALUE;
    private static final int FOOTERS_START = Integer.MIN_VALUE / 2;

    private int header_offset = HEADERS_START;
    private int footer_offset = FOOTERS_START;

    public static int MAX_HEADER_COUNT = 10;
    public static int MAX_FOOTER_COUNT = 10;


    private List<ViewPair> mHeaderViews, mFooterViews;

    public HeaderFooterAdapter(Context context) {
        this(context, null);
    }

    public HeaderFooterAdapter(Context context, List<D> data) {
        super(context, data);
        mHeaderViews = new ArrayList<ViewPair>();
        mFooterViews = new ArrayList<ViewPair>();
    }

    /**
     * @param indexWithHeaderFooter 注意，每一个HEADER和FOOTER的View类型都不一样，因为FOOTER HEADER 不需要缓存
     * @return
     */
    @Override
    public final int getItemViewType(int indexWithHeaderFooter) {
        int hCount = getHeaderCount();
        //Header
        if (indexWithHeaderFooter < hCount) {
            return mHeaderViews.get(indexWithHeaderFooter).viewType;
        } else {
            int itemCount = super.getItemCount();
            //listdata viewtype
            if (indexWithHeaderFooter < hCount + itemCount) {
                return getListItemViewHolderType(indexWithHeaderFooter - hCount);
            } else {
                //Footer
                return mFooterViews.get(indexWithHeaderFooter - hCount - itemCount).viewType;
            }
        }
    }

    /**
     * @param dataIndex
     * @return
     */
    @Override
    public int getListItemViewHolderType(int dataIndex) {
        return 0;
    }

    @Override
    public final int getItemCount() {
        return getHeaderCount() + getFooterCount() + super.getItemCount();
    }

    public int getFooterCount() {
        return mFooterViews.size();
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public void addHeaderView(View view) {
        if (mHeaderViews.size() >= MAX_HEADER_COUNT) {
            throw new UnsupportedOperationException("HEADER COUNT CAN'T EXCEED MAX_HEADER_COUNT!");
        }
        if (view != null) {
            mHeaderViews.add(new ViewPair(header_offset++, view));
        }
        notifyDataSetChanged();
    }

    public void addHeaderView(int index, View view) {
        if (mHeaderViews.size() >= MAX_HEADER_COUNT) {
            throw new UnsupportedOperationException("HEADER COUNT CAN'T EXCEED MAX_HEADER_COUNT!");
        }
        if (index >= MAX_HEADER_COUNT || index<0) {
            throw new UnsupportedOperationException("INDEX MUST BE RIGHT!");
        }
        if (view != null) {
            mHeaderViews.add(index, new ViewPair(header_offset++, view));
        }
        notifyDataSetChanged();
    }

    public void addFooterView(View view) {
        if (mFooterViews.size() >= MAX_FOOTER_COUNT) {
            throw new UnsupportedOperationException("FOOTER COUNT CAN'T EXCEED MAX_HEADER_COUNT!");
        }
        if (view != null) {
            mFooterViews.add(new ViewPair(footer_offset++, view));
        }
        notifyDataSetChanged();
    }

    public void removeHeaderView(View view) {
        if (view != null && mHeaderViews != null) {
            Iterator<ViewPair> iterator = mHeaderViews.iterator();
            while (iterator.hasNext()) {
                ViewPair viewPair = iterator.next();
                if (viewPair.view.equals(view)) {
                    iterator.remove();
                }
            }
        }
        notifyDataSetChanged();
    }

    public void removeFooterView(View view) {
        if (view != null && mFooterViews != null) {
            Iterator<ViewPair> iterator = mFooterViews.iterator();
            while (iterator.hasNext()) {
                ViewPair viewPair = iterator.next();
                if (viewPair.view.equals(view)) {
                    iterator.remove();
                }
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 返回Header or Footer ，或者inflate真正的数据Item
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public final View inflaterItemView(ViewGroup parent, int viewType) {
        if (mHeaderViews != null) {
            for (ViewPair viewPair : mHeaderViews) {
                if (viewPair.viewType == viewType) {
                    return viewPair.view;
                }
            }
        }

        if (mFooterViews != null) {
            for (ViewPair viewPair : mFooterViews) {
                if (viewPair.viewType == viewType) {
                    return viewPair.view;
                }
            }
        }

        return inflaterListItemView(parent, viewType);
    }

    /**
     * 绑定数据，去掉头部和尾部，因为这些View不需要绑定数据
     *
     * @param viewHolder
     * @param indexWithHeaderFooter
     */
    @Override
    public final void bindItemData(RecyclerViewHolder viewHolder, int indexWithHeaderFooter) {
        //List中真正的数据
        if (isDataItem(indexWithHeaderFooter)) {
            bindListItemData(viewHolder, indexWithHeaderFooter - getHeaderCount());
        }
    }

    /**
     * 绑定点击事件，去掉HEADER 和 FOOTER
     *
     * @param holder
     * @param indexWithHeaderFooter
     */
    @Override
    protected void bindItemClickListener(RecyclerViewHolder holder, int indexWithHeaderFooter) {
        //如果不是数据item，那么点击事件交于使用者绑定
        if (!isDataItem(indexWithHeaderFooter)) return;
        super.bindItemClickListener(holder, indexWithHeaderFooter - getHeaderCount());
    }

    /**
     * 根据索引判断是否是list中的数据
     *
     * @param indexWithHeaderFooter
     * @return
     */
    protected boolean isDataItem(int indexWithHeaderFooter) {
        return indexWithHeaderFooter >= getHeaderCount() && indexWithHeaderFooter < getHeaderCount() + super.getItemCount();
    }

    /**
     * 获取list中item的数据，注意！！！
     *
     * @param dataIndex 是数据中的位置，并不包括HEADER  FOOTER
     * @return
     */
    @Override
    public D getItem(int dataIndex) {
        return super.getItem(dataIndex);
    }

    /**
     * 在页面中的item index，返回数据
     *
     * @param indexWithHeaderFooter
     * @return
     */
    public D getItemFromViewIndex(int indexWithHeaderFooter) {
        if (isDataItem(indexWithHeaderFooter)) {
            return getItem(indexWithHeaderFooter - getHeaderCount());
        }
        return null;
    }

    public void onDestory() {
        if (mHeaderViews != null) {
            mHeaderViews.clear();
        }
        if (mFooterViews != null) {
            mFooterViews.clear();
        }

        header_offset = HEADERS_START;
        footer_offset = FOOTERS_START;
    }

    private static class ViewPair {
        public final int viewType;
        public final View view;

        public ViewPair(int viewType, View view) {
            this.viewType = viewType;
            this.view = view;
        }
    }
}
