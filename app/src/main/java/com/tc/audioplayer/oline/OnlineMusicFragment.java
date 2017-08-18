package com.tc.audioplayer.oline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.tc.audioplayer.R;
import com.tc.audioplayer.oline.music.MusicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tc.audioplayer.oline.music.MusicFragment.HOT;
import static com.tc.audioplayer.oline.music.MusicFragment.NEW;

/**
 * Created by tianchao on 2017/8/2.
 */

public class OnlineMusicFragment extends Fragment {
    public static OnlineMusicFragment newInstance() {
        OnlineMusicFragment instance = new OnlineMusicFragment();
        return instance;
    }

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.stl_title)
    SlidingTabLayout stlTitle;


    private List<Fragment> fragmentList;
    private String[] tabTitles;
    private OnlinePagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_music, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initUI();
    }

    private void initData() {
        tabTitles = getResources().getStringArray(R.array.online_tabs);
        fragmentList = new ArrayList<>();
        Fragment hotFragment = MusicFragment.newInstance(HOT);
        Fragment newFragment = MusicFragment.newInstance(NEW);
        Fragment listFragment = ListMusicFragment.newInstance();
        fragmentList.add(hotFragment);
        fragmentList.add(newFragment);
        fragmentList.add(listFragment);
        adapter = new OnlinePagerAdapter(getChildFragmentManager());
    }

    private void initUI() {
        vpContent.setAdapter(adapter);
        stlTitle.setViewPager(vpContent);
        vpContent.setCurrentItem(0);
    }

    private class OnlinePagerAdapter extends FragmentPagerAdapter {
        public OnlinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
