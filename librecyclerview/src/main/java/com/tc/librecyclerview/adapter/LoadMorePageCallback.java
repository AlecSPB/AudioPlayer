package com.tc.librecyclerview.adapter;

/**
 * Date:15/11/10
 * Time:15:18
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public interface LoadMorePageCallback<D> {

    void preLoadMore();

    void loadMorePageFail();

    void loadMorePageSucc(D data);
}
