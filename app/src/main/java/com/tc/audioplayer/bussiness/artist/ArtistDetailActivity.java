package com.tc.audioplayer.bussiness.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.player.PlayerManager;
import com.tc.base.utils.CollectionUtil;
import com.tc.model.entity.ArtistSongList;
import com.tc.model.entity.PlayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by itcayman on 2017/9/25.
 */

public class ArtistDetailActivity extends ToolbarActivity {
    private String albumnid;
    private String tinguid;
    private String author;
    private String country;
    private String authorImg;
    private ArtistDetailPresenter presenter;
    private ArtistDetailAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView ivAlbumn;
    private TextView tvAuthor;
    private TextView tvLanguage;
    private TextView tvPlayAll;
    private View headerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base);
        setContentUnderToolbar();
        minibar.setAutoVisibility(true);
        minibar.bindData();

        setToolbarCenterTitle("歌手");
        albumnid = getIntent().getStringExtra("albumnid");
        tinguid = getIntent().getStringExtra("tinguid");
        author = getIntent().getStringExtra("author");
        country = getIntent().getStringExtra("country");
        authorImg = getIntent().getStringExtra("authorImg");

        presenter = new ArtistDetailPresenter(albumnid, tinguid);
        presenter.attachView(this);
        adapter = new ArtistDetailAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> {
            PlayList playList = new PlayList();
            playList.addSong(adapter.getItem(position));
            PlayerManager.getInstance().play(playList, 0);
        });
        initHeaderView();

        progressBar.setVisibility(View.VISIBLE);
        presenter.loadData(false);
    }

    private void initHeaderView() {
        headerView = LayoutInflater.from(this).inflate(R.layout.header_artist_detail, recyclerView, false);
        ivAlbumn = (ImageView) headerView.findViewById(R.id.iv_albumn);
        tvAuthor = (TextView) headerView.findViewById(R.id.tv_author);
        tvLanguage = (TextView) headerView.findViewById(R.id.tv_language);
        tvPlayAll = (TextView) headerView.findViewById(R.id.tv_play_all);
        tvPlayAll.setOnClickListener((v) -> {
            if (CollectionUtil.isEmpty(adapter.getData()))
                return;
            PlayList playList = new PlayList();
            playList.addSongList(adapter.getData());
            PlayerManager.getInstance().play(playList, 0);
        });
        headerView.setVisibility(View.GONE);
        adapter.addHeaderView(headerView);

        Glide.with(this)
                .load(authorImg)
                .asBitmap()
                .transform(new RoundedCornersTransformation(this, 10, 0))
                .into(ivAlbumn);
        tvAuthor.setText(author);
        tvLanguage.setText(country + "歌手");
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
        headerView.setVisibility(View.VISIBLE);
        ArtistSongList songListWrapper = (ArtistSongList) data;
        adapter.setData(songListWrapper.songlist);
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        presenter.loadData(true);
    }
}
