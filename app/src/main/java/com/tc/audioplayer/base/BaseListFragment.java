package com.tc.audioplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.widget.Minibar;
import com.tc.librecyclerview.LinearRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tianchao on 2017/8/5.
 */

public class BaseListFragment extends BaseFragment {

    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lrv_conent)
    public LinearRecyclerView recyclerView;
    @BindView(R.id.minibar)
    public Minibar minibar;
    @BindView(R.id.tv_retry)
    TextView tvRetry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base_list_with_minibar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        minibar.setVisibility(View.GONE);
        minibar.setAutoVisibility(false);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvRetry.setOnClickListener((v) -> {
            swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    protected void onRefresh() {

    }

    @Override
    public void setData(Object data) {
        super.setData(data);
        swipeRefreshLayout.setRefreshing(false);
        tvRetry.setVisibility(View.GONE);
    }

    @Override
    public void handleThrowable(Throwable t) {
        swipeRefreshLayout.setRefreshing(false);
        tvRetry.setVisibility(View.VISIBLE);
    }
}
