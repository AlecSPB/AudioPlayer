package com.tc.audioplayer.bussiness.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseListFragment;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.event.SearchEvent;
import com.tc.base.utils.CollectionUtil;
import com.tc.model.entity.HotSearch;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianchao on 2017/10/22.
 */

public class SearchHistoryFragment extends BaseListFragment {

    public static SearchHistoryFragment newInstance() {
        SearchHistoryFragment fragment = new SearchHistoryFragment();
        return fragment;
    }

    private SearchHistoryAdapter adapter;
    private SearchPresent present;
    private List<Object> hotSearchList = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SearchHistoryAdapter(getContext());
        adapter.addHeaderView(getHeaderView());
        recyclerView.setAdapter(adapter);
        present = new SearchPresent();
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(() -> searchHot());
        adapter.setOnItemClickListener((v, positon) -> {
            Object item = adapter.getItem(positon);
            String keyword = "";
            if (item instanceof String) {
                keyword = (String) item;
            } else if (item instanceof HotSearch) {
                keyword = ((HotSearch) item).word;
            }
            Navigator.toSearchActivity(getContext(), keyword);
        });
        searchHot();
    }

    private void searchHot() {
        present.requestHotSearch((hotSearchResult) -> {
            List<Object> data = new ArrayList<>();
            data.addAll(getHistory());
            data.addAll(getHot(hotSearchResult));
            adapter.setData(data);
        });
    }

    private List<Object> getHot(List<HotSearch> hotSearchResult) {
        if (!CollectionUtil.isEmpty(hotSearchList))
            return hotSearchList;
        if (!CollectionUtil.isEmpty(hotSearchResult)) {
            hotSearchList.add(new SearchHistoryAdapter.SearchHeader(getString(R.string.search_hot), 1));
            hotSearchList.addAll(hotSearchResult);
        }
        return hotSearchList;
    }

    private List<Object> getHistory() {
        List<Object> data = new ArrayList<>();
        List<String> history = SearchPresent.getSearchHistoryData();
        List<String> temp = new ArrayList<>();
        if (history != null && history.size() > 0 && TextUtils.isEmpty(history.get(history.size() - 1))) {
            temp.addAll(history.subList(0, history.size() - 1));
        } else if (history != null) {
            temp.addAll(history);
        }
        if (!CollectionUtil.isEmpty(temp)) {
            data.add(new SearchHistoryAdapter.SearchHeader(getString(R.string.search_history), 0));
            data.addAll(temp);
        }
        return data;
    }

    private View getHeaderView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.header_search, recyclerView, false);
        EditText searchView = (EditText) view.findViewById(R.id.search_view);
        searchView.setOnClickListener((v) -> {
            Navigator.toSearchActivity(getContext(), "");
        });
        return view;
    }

    @Override
    protected int configDefaultRigsterFlags() {
        return EventBusRegisterFlags.NEED_DEFAULT_REGISTER;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SearchEvent event) {
        List<Object> data = new ArrayList<>();
        data.addAll(getHistory());
        data.addAll(getHot(null));
        adapter.setData(data);
    }
}
