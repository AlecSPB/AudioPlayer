package com.tc.audioplayer;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tc.audioplayer.base.BaseActivity;
import com.tc.audioplayer.bussiness.album.AlbumListFragment;
import com.tc.audioplayer.bussiness.artist.ArtistListFragment;
import com.tc.audioplayer.bussiness.billboard.BillboardListFragment;
import com.tc.audioplayer.bussiness.hot.HotFragment;
import com.tc.audioplayer.bussiness.search.SearchHistoryFragment;
import com.tc.audioplayer.utils.StatusBarUtil;
import com.tc.audioplayer.widget.Minibar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
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
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorLayout;
    ImageView ivAvatar;
    TextView tvUsername;

    private MainPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private String[] tabTitles;
    private int[] iconRess = new int[]{R.drawable.selector_tab_billboard, R.drawable.selector_tab_album,
            R.drawable.selector_tab_artist, R.drawable.selector_tab_hot, R.drawable.selector_tab_artist};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        StatusBarUtil.setTranslucentForDrawerLayout(this, drawerLayout, 0);
        initData();
        initUI();
        initUserinfo();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged("", color);
    }

    private void initData() {
        tabTitles = getResources().getStringArray(R.array.main_tabs);
        fragmentList = new ArrayList<>();
        Fragment billboardFragment = BillboardListFragment.newInstance();
        Fragment albumFragment = AlbumListFragment.newInstance();
        Fragment artistFragment = ArtistListFragment.newInstance();
        Fragment hotFragment = HotFragment.newInstance();
        Fragment searchFragment = SearchHistoryFragment.newInstance();
        fragmentList.add(billboardFragment);
        fragmentList.add(albumFragment);
        fragmentList.add(artistFragment);
        fragmentList.add(hotFragment);
        fragmentList.add(searchFragment);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
    }

    private void initUI() {
        View headerView = navigationView.getHeaderView(0);
        ivAvatar = (ImageView) headerView.findViewById(R.id.iv_avatar);
        tvUsername = (TextView) headerView.findViewById(R.id.tv_username);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        vpContentMain.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContentMain);
        vpContentMain.setCurrentItem(0);
        for (int i = 0; i < tabTitles.length; i++) {
            tabLayout.getTabAt(i).setCustomView(createTabView(i));
        }

        minibar.setAutoVisibility(true);
        minibar.setFragmentManager(getSupportFragmentManager());
        minibar.postDelayed(() -> {
            minibar.bindData();
        }, 500);
        vpContentMain.addOnPageChangeListener(pageChangeListener);
        vpContentMain.setOffscreenPageLimit(5);
        onVPageSelected(0);
    }

    private void initUserinfo() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            tvUsername.setText(user.getEmail());
            navigationView.getHeaderView(0).setOnClickListener(null);
        } else {
            tvUsername.setText(getString(R.string.unlogin));
            navigationView.getHeaderView(0).setOnClickListener((v) -> {
                Navigator.toLoginActivity(MainActivity.this);
            });
        }
    }

    private View createTabView(int position) {
        String name = tabTitles[position];
        int iconRes = iconRess[position];
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        TextView textView = (TextView) layoutInflater.inflate(R.layout.item_home_tab, null);
        textView.setText(name);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, iconRes, 0, 0);
        return textView;
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

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            onVPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void onVPageSelected(int position) {
        setToolbarTitle(toolbar, tabTitles[position]);
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
//        if (id == R.id.action_search) {
//            Navigator.toSearchActivity(this);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
//            case R.id.nav_model_single:
//                Toast.makeText(this, "简单模式功能暂未上线", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_timer:
//                Toast.makeText(this, "定时功能暂未上线", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.nav_about:
                break;
//            case R.id.nav_share:
//                Toast.makeText(this, "分享功能暂未上线", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.nav_fav:
                Navigator.toFavListActivity(this);
                break;
            case R.id.nav_exit:
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    protected int configDefaultRigsterFlags() {
//        return EventBusRegisterFlags.NEED_DEFAULT_REGISTER;
//    }

//    @Subscribe
//    public void onEventMainThread(LoginEvent event){
//        initUserinfo();
//    }
}
