package com.tc.librecyclerview;

import android.graphics.Canvas;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.ViewGroup;

import com.tc.librecyclerview.adapter.PinnedSectionedHeaderAdapter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Date:15/11/17
 * Time:14:27
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 * <p/>
 * 为一个Adapter view提供悬停功能，配合{@link PinnedSectionedHeaderAdapter}使用
 */
public class PinnedSectionHeaderHelper {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    //悬停view的meassure时的MODE
    private int mWidthMode;
    private int mHeightMode;
    //悬停的View
    private View mCurrentHeader;
    //悬停view的一些参数
    private int mCurrentHeaderViewType = 0;
    private float mHeaderOffset;
    //listview中当前需要显示的section
    private int mCurrentSection = 0;
    private ViewMeasureSpec mViewMeasureSpec;

    /**
     * 获取HeaderView，而且提供了复用的功能
     *
     * @param oldView
     * @param adapter
     * @return
     */
    private View getSectionHeaderView(int position, View oldView, PinnedSectionedHeaderAdapter adapter, ViewGroup parent, @ORIENTATION int orientation) {
        //是否是一个新的Header，如果是一个新的Header，那需要重新去计算它的位置和大小，否则直接使用旧的就可以了
        int sectionType = adapter.getSectionHeaderViewType(position);
        boolean shouldLayout = sectionType != mCurrentSection || oldView == null;

        View view = adapter.getSectionHeaderView(position, oldView, parent);
        if (shouldLayout) {
            ensurePinnedHeaderLayout(parent, view, orientation);
            mCurrentSection = sectionType;
        }
        return view;
    }

    private void ensurePinnedHeaderLayout(ViewGroup parent, View header, @ORIENTATION int orientation) {
        if (header == null) return;

        if (header.isLayoutRequested()) {
            ViewMeasureSpec viewMeasureSpec = ensureMeasureSpec(parent, header, orientation);
            header.measure(viewMeasureSpec.getWidthSpec(), viewMeasureSpec.getHeightSpec());
            //FIX ME ，这里可以写得更加开放，悬浮View左上角不一定要 0,0
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        }
    }

    protected ViewMeasureSpec ensureMeasureSpec(ViewGroup parent, View header, @ORIENTATION int orientation) {
        if (mViewMeasureSpec == null) {
            ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
            int widthSpec;
            if (layoutParams != null && layoutParams.width > 0) {
                widthSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.width, View.MeasureSpec.EXACTLY);
            } else if (orientation == VERTICAL) {
                //Header的宽度在listview垂直方向，如果没有指定，直接使用父View的MODE
                widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), mWidthMode);
            } else {
                widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            }

            int heightSpec;
            if (layoutParams != null && layoutParams.height > 0) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
            } else if (orientation == HORIZONTAL) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredHeight(), mHeightMode);
            } else {
                heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            }

            mViewMeasureSpec = new ViewMeasureSpec(widthSpec, heightSpec);
        }

        return mViewMeasureSpec;
    }

    public void setViewMeasureSpec(ViewMeasureSpec viewMeasureSpec) {
        this.mViewMeasureSpec = viewMeasureSpec;
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        mHeightMode = View.MeasureSpec.getMode(heightMeasureSpec);
    }

    public void onScrolled(ViewGroup parentView, @ORIENTATION int orientation, PinnedSectionedHeaderAdapter mAdapter, int firstVisibleItem, int visibleItemCount) {
        this.onScrolled(parentView, orientation, mAdapter, firstVisibleItem, visibleItemCount, 0);
    }

    public void onScrolled(ViewGroup parentView, @ORIENTATION int orientation, PinnedSectionedHeaderAdapter mAdapter, int firstVisibleItem, int visibleItemCount, int headerCounts) {

        //Adapter为空  不需要悬停效果
        if (mAdapter == null || mAdapter.getCount() == 0 || firstVisibleItem < headerCounts) {
            mCurrentHeader = null;
            mHeaderOffset = 0.0f;
            parentView.invalidate();
            return;
        }

        //获取当前应该显示的section的第一个item的postion
        int position = mAdapter.getSectionForPosition(firstVisibleItem);

        if (position >= 0) {
            int viewType = mAdapter.getSectionHeaderViewType(position);
            mCurrentHeader = getSectionHeaderView(position,
                    mCurrentHeaderViewType != viewType ? null : mCurrentHeader, mAdapter, parentView, orientation);
            ensurePinnedHeaderLayout(parentView, mCurrentHeader, orientation);
            mCurrentHeaderViewType = viewType;
        } else {
            mCurrentHeader = null;
        }

        mHeaderOffset = 0.0f;

        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
            //找到section，判断是否和当前Header有相交部分，如果有，需要把Header做一下偏移
            if (i != position && mAdapter.isSectionHeader(i)) {
                View header = parentView.getChildAt(i - firstVisibleItem);
                if (mCurrentHeader != null) {
                    if (orientation == VERTICAL) {
                        float headerTop = header.getTop();
                        float pinnedHeaderHeight = mCurrentHeader.getMeasuredHeight();
                        if (pinnedHeaderHeight >= headerTop && headerTop > 0) {
                            mHeaderOffset = headerTop - header.getHeight();
                        }
                    } else {
                        float headerLeft = header.getLeft();
                        float pinnedHeaderWidth = mCurrentHeader.getMeasuredWidth();
                        if (pinnedHeaderWidth >= headerLeft) {
                            mHeaderOffset = headerLeft - pinnedHeaderWidth;
                        }
                    }
                }
            }
        }
        parentView.invalidate();
    }

    public void dispatchDraw(Canvas canvas, @ORIENTATION int oritation) {

        if (mCurrentHeader == null)
            return;

        int saveCount = canvas.save();
        if (oritation == VERTICAL) {
            canvas.translate(0, mHeaderOffset);
            canvas.clipRect(0, 0, mCurrentHeader.getMeasuredWidth(), mCurrentHeader.getMeasuredHeight()); // needed
        } else {
            canvas.translate(mHeaderOffset, 0);
            canvas.clipRect(0, 0, mCurrentHeader.getMeasuredWidth(), mCurrentHeader.getMeasuredHeight()); // 仅绘制需要展示的部分
        }
        mCurrentHeader.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @IntDef({VERTICAL, HORIZONTAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ORIENTATION {
    }

    /**
     * 用来描述view measure过程中的widthMeasureSpec和heightMeasureSpec
     */
    public static class ViewMeasureSpec {
        int heightSpec;
        int widthSpec;

        public ViewMeasureSpec(int widthSpec, int heightSpec) {
            this.widthSpec = widthSpec;
            this.heightSpec = heightSpec;
        }

        public int getHeightSpec() {
            return heightSpec;
        }

        public void setHeightSpec(int heightSpec) {
            this.heightSpec = heightSpec;
        }

        public int getWidthSpec() {
            return widthSpec;
        }

        public void setWidthSpec(int widthSpec) {
            this.widthSpec = widthSpec;
        }
    }

}
