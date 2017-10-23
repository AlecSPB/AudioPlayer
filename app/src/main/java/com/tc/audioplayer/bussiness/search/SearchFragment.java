package com.tc.audioplayer.bussiness.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseFragment;
import com.tc.audioplayer.event.SearchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itcayman on 2017/9/5.
 */

public class SearchFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] tabItems;
    private List<SearchResultFragment> fragmentList;
    private ProgressBar progressBar;
    private ContentPagerAdapter contentAdapter;
    private SearchPresent present;
    private String keyword;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        present = new SearchPresent();
        tabItems = getResources().getStringArray(R.array.search);
        fragmentList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_tab);
        viewPager = (ViewPager) view.findViewById(R.id.vp_content);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        initTab();
        initContent();
        return view;
    }

    private void initTab() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.search_text),
                ContextCompat.getColor(getContext(), R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        ViewCompat.setElevation(tabLayout, 10);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    private void initContent() {
        contentAdapter = new ContentPagerAdapter(getFragmentManager());
        fragmentList.add(SearchResultFragment.newInstance(SearchResultFragment.SONG));
        fragmentList.add(SearchResultFragment.newInstance(SearchResultFragment.AUTHOR));
        fragmentList.add(SearchResultFragment.newInstance(SearchResultFragment.ARTIST));
        viewPager.setAdapter(contentAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        present.attachView(this);
        if (!TextUtils.isEmpty(keyword)) {
            search(keyword);
        }
    }

    public void search(String key) {
        if (present == null) {
            keyword = key;
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        present.search(key, (result) -> {
            progressBar.setVisibility(View.GONE);
            fragmentList.get(0).setData(result.result.song_info.song_list);
            fragmentList.get(1).setData(result.result.artist_info.artist_list);
            fragmentList.get(2).setData(result.result.album_info.album_list);
        });
        SearchPresent.saveSearchKey(keyword);
        eventBus.post(new SearchEvent());
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return tabItems.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabItems[position];
        }
    }
}
