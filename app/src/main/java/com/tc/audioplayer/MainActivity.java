package com.tc.audioplayer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tc.audioplayer.base.BaseActivity;
import com.tc.audioplayer.bussiness.oline.OnlineMusicFragment;
import com.tc.audioplayer.local.LocalMusicFragment;
import com.tc.audioplayer.widget.Minibar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MUTI_PERMISSION_WINDOW = 10;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.vp_content_main)
    ViewPager vpContentMain;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.minibar)
    Minibar minibar;

    private MainPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private String[] tabTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initData();
        initUI();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged("", color);
    }

    private void initData() {
        tabTitles = getResources().getStringArray(R.array.main_tabs);
        fragmentList = new ArrayList<>();
        Fragment onlineFragment = OnlineMusicFragment.newInstance();
        Fragment localFragment = LocalMusicFragment.newInstance();
        fragmentList.add(onlineFragment);
        fragmentList.add(localFragment);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
    }

    private void initUI() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        vpContentMain.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContentMain);
        vpContentMain.setCurrentItem(0);

        minibar.postDelayed(() -> {
            minibar.bindData();
        }, 500);
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Navigator.toSearchActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            // Handle the camera action
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
