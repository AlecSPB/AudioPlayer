package com.tc.audioplayer.bussiness.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;

/**
 * Created by tianchao on 2017/10/22.
 */

public class SearchHistoryFragment extends BaseListFragment {

    public static SearchHistoryFragment newInstance(){
        SearchHistoryFragment fragment = new SearchHistoryFragment();
        return fragment;
    }

    private SearchHistoryAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SearchHistoryAdapter(getContext());
        adapter.addHeaderView(getHeaderView());
        recyclerView.setAdapter(adapter);
    }

    private View getHeaderView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.header_search, recyclerView, false);
        SearchView searchView = (SearchView) view.findViewById(R.id.search_view);
        return view;
    }
}
