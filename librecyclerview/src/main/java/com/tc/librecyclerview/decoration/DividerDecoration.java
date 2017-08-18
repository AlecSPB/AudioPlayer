package com.tc.librecyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Date:15/9/25
 * Time:15:24
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    //分割线样式
    public static final int FLAG_DIVIDER_NONE = 0x00;
    public static final int FLAG_DIVIDER_MIDDLE = 0x00000001;
    public static final int FLAG_DIVIDER_HEADER = 0x00000002;
    public static final int FLAG_DIVIDER_TAIL = 0x00000004;
    public static final int FLAG_DIVIDER_ALL = 0x00000060;
    //画笔
    Paint mDividerPaint;
    //分割线大小 颜色
    private int mDividerSize = 0;
    private int mDividerColor = 0x0;
    private int mDividerStyleFlags = FLAG_DIVIDER_NONE;
    private Margin mDividerMargin;

    public DividerDecoration(int color, int size, int styleFlags, Margin margin) {
        this.mDividerColor = color;
        this.mDividerSize = size;
        this.mDividerStyleFlags = styleFlags;
        this.mDividerMargin = margin;

        mDividerPaint = new Paint();
        mDividerPaint.setColor(mDividerColor);
        mDividerPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (!(parent.getLayoutManager() instanceof LinearLayoutManager)) {
            throw new UnsupportedOperationException("LinearRecyclerView only support LinearLayoutManager!");
        }

        int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();

        int pLeft = parent.getPaddingLeft();
        int pRight = parent.getWidth() - parent.getPaddingRight();
        int pTop = parent.getPaddingTop();
        int pBottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (orientation == LinearLayoutManager.VERTICAL) {
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDividerSize;
                c.save();
                //主要解决在Decoration到边界时，会画到父View的Pading上
                if (top < pTop) {
                    c.clipRect(pLeft, pTop, parent.getWidth(), bottom - pTop);
                } else if (bottom > pBottom) {
                    c.clipRect(pLeft, top, parent.getWidth(), pBottom - top);
                }
                c.drawRect(pLeft, top, pRight, bottom, mDividerPaint);
                c.restore();
            } else {
                int begin = child.getRight() + params.rightMargin;
                int end = begin + mDividerSize;
                c.save();
                if (begin < pLeft) {
                    c.clipRect(pLeft, pTop, end - pLeft, parent.getHeight());
                } else if (end > pRight) {
                    c.clipRect(begin, pTop, pRight - begin, parent.getHeight());
                }
                c.drawRect(begin, pTop, end, pBottom, mDividerPaint);
                c.restore();
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.bottom = mDividerSize;
        } else {
            outRect.right = mDividerSize;
        }
    }

    public static class Margin {
        public int marginLeft;
        public int marginRight;
        public int marginTop;
        public int marginBottom;

        public Margin(int left, int top, int right, int bottom) {
            this.marginLeft = left;
            this.marginRight = right;
            this.marginTop = top;
            this.marginBottom = bottom;
        }
    }
}
