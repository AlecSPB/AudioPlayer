package com.tc.librecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tc.librecyclerview.adapter.LoadMorePageAction;
import com.tc.librecyclerview.adapter.PinnedSectionedHeaderAdapter;
import com.tc.librecyclerview.decoration.DividerDecoration;


/**
 * Date:15/9/25
 * Time:10:21
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public class LinearRecyclerView extends RecyclerView {

    //悬停相关
    protected PinnedSectionedHeaderAdapter mPinnedSectionedHeaderAdapter;
    protected PinnedSectionHeaderHelper mPinnedSectionHeaderHelper;
    protected LinearLayoutManager mLinearLayoutManager;
    //start 分割线相关------
    private int dividerSize;
    private int dividerColor;
    private int dividerStyleFlags;
    private int dividerMarginLeft;
    private int dividerMarginRight;
    private int dividerMarginTop;
    private int dividerMarginBottom;
    //上拉加载更多
    private boolean mEnableLoadMore = false;
    private boolean isLoadingMore = false;
    private LoadMorePageAction mLoadMorePageAction;
    private int lastVisibleItem;
    private boolean mEnablePinnedSectionHeader = false;

    public LinearRecyclerView(Context context) {
        this(context, null);
    }

    public LinearRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.LinearRcView_Style);
    }

    public LinearRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initDividerStyle(context, attrs, defStyle);
    }

    private void initDividerStyle(Context context, AttributeSet atts, int defStyle) {
        TypedArray array = context.obtainStyledAttributes(atts, R.styleable.LinearRecyclerView_Divider,
                R.attr.LinearRcView_Style, R.style.Default_LRV_Divider_Style
        );
        dividerStyleFlags = array.getInt(R.styleable.LinearRecyclerView_Divider_divider_style, 1);
        dividerSize = array.getDimensionPixelOffset(R.styleable.LinearRecyclerView_Divider_divider_size, 1);
        dividerColor = array.getColor(R.styleable.LinearRecyclerView_Divider_divider_color, Color.GRAY);

        dividerMarginLeft = array.getDimensionPixelOffset(R.styleable.LinearRecyclerView_Divider_divider_marginLeft, -1);
        dividerMarginTop = array.getDimensionPixelOffset(R.styleable.LinearRecyclerView_Divider_divider_marginTop, -1);
        dividerMarginRight = array.getDimensionPixelOffset(R.styleable.LinearRecyclerView_Divider_divider_marginRight, -1);
        dividerMarginBottom = array.getDimensionPixelOffset(R.styleable.LinearRecyclerView_Divider_divider_marginBottom, -1);
        array.recycle();
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (!(layout instanceof LinearLayoutManager)) {
            throw new UnsupportedOperationException("LinearRecyclerView only support LinearLayoutManager!");
        }
        super.setLayoutManager(layout);
        this.mLinearLayoutManager = (LinearLayoutManager) layout;
    }


    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        int mVisibleItemCount = mLinearLayoutManager.getChildCount();
        int mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int mTotalItemCount = mLinearLayoutManager.getItemCount();
        if (mEnablePinnedSectionHeader) {
            onSrollPinnedHeader(mVisibleItemCount, mFirstVisibleItem);
        }

        onScrollLoardMore(mVisibleItemCount, mFirstVisibleItem, mTotalItemCount);
    }


    private void onScrollLoardMore(int mVisibleItemCount, int mFirstVisibleItem, int mTotalItemCount) {
        if (lastVisibleItem == mFirstVisibleItem + mVisibleItemCount) {
            return;
        }
        lastVisibleItem = mFirstVisibleItem + mVisibleItemCount;

        if (mVisibleItemCount > 0 && lastVisibleItem >= mTotalItemCount
                && mEnableLoadMore && !isLoadingMore && mLoadMorePageAction != null) {
            mLoadMorePageAction.loadMore();
        }
    }

    protected void onSrollPinnedHeader(int mVisibleItemCount, int mFirstVisibleItem) {
        mPinnedSectionHeaderHelper.onScrolled(this,
                getOritation(),
                mPinnedSectionedHeaderAdapter, mFirstVisibleItem, mVisibleItemCount);
    }


    /**
     * 开启上拉加载更多
     *
     * @param enableLoadMore
     * @param loadMorePageAction
     */
    public void enableLoadMore(boolean enableLoadMore, LoadMorePageAction loadMorePageAction) {
        this.mEnableLoadMore = enableLoadMore;
        this.mLoadMorePageAction = loadMorePageAction;
    }

    /**
     * 加载默认的分割线
     */
    public void addDefaultDivider() {
        this.addItemDecoration(new DividerDecoration(dividerColor, dividerSize,
                dividerStyleFlags, new DividerDecoration.Margin(dividerMarginLeft, dividerMarginTop,
                dividerMarginRight, dividerMarginBottom)
        ));
    }

    /**
     * 开启悬停
     *
     * @param adapter
     */
    public void enablePinnedSectionHeader(PinnedSectionedHeaderAdapter adapter) {
        this.mEnablePinnedSectionHeader = true;
        this.mPinnedSectionedHeaderAdapter = adapter;
        this.mPinnedSectionHeaderHelper = new PinnedSectionHeaderHelper();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mEnablePinnedSectionHeader) {
            mPinnedSectionHeaderHelper.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mEnablePinnedSectionHeader) {
            mPinnedSectionHeaderHelper.dispatchDraw(canvas, getOritation());
        }
    }

    @PinnedSectionHeaderHelper.ORIENTATION
    protected int getOritation() {
        return mLinearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL
                ? PinnedSectionHeaderHelper.HORIZONTAL : PinnedSectionHeaderHelper.VERTICAL;
    }

}
