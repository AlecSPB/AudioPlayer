package com.tc.audioplayer.bussiness.album;

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
import com.tc.model.entity.Album;
import com.tc.model.entity.AlbumDetailEntity;
import com.tc.model.entity.PlayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by itcayman on 2017/9/25.
 */

public class AlbumnDetailActivity extends ToolbarActivity {
    private String albumnid;
    private AlbumnDetailPresenter presenter;
    private AlbumnDetailAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView ivAlbumn;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvLanguage;
    private TextView tvCompany;
    private TextView tvTime;
    private TextView tvPlayAll;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base);
        setContentUnderToolbar();

        albumnid = getIntent().getStringExtra("albumnid");

        presenter = new AlbumnDetailPresenter(albumnid);
        presenter.attachView(this);
        adapter = new AlbumnDetailAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, position) -> {
            PlayList playList = new PlayList();
            playList.addSong(adapter.getItem(position));
            PlayerManager.getInstance().play(playList, 0);
        });
        initHeaderView();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            progressBar.setVisibility(View.VISIBLE);
            presenter.loadData(true);
        });

        progressBar.setVisibility(View.VISIBLE);
        presenter.loadData(false);
    }

    private void initHeaderView() {
        View view = LayoutInflater.from(this).inflate(R.layout.header_album_detail, recyclerView, false);
        ivAlbumn = (ImageView) view.findViewById(R.id.iv_albumn);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvAuthor = (TextView) view.findViewById(R.id.tv_author);
        tvLanguage = (TextView) view.findViewById(R.id.tv_language);
        tvCompany = (TextView) view.findViewById(R.id.tv_company);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvPlayAll = (TextView) view.findViewById(R.id.tv_play_all);
        tvPlayAll.setOnClickListener((v) -> {
            if (CollectionUtil.isEmpty(adapter.getData()))
                return;
            PlayList playList = new PlayList();
            playList.addSongList(adapter.getData());
            PlayerManager.getInstance().play(playList, 0);
        });
        adapter.addHeaderView(view);
    }

    private void updateHeader(Album album) {
        Glide.with(this)
                .load(album.pic_radio)
                .asBitmap()
                .transform(new RoundedCornersTransformation(this, 10, 0))
                .into(ivAlbumn);
        tvTitle.setText(album.title);
        tvAuthor.setText(getString(R.string.album_author, album.author));
        tvLanguage.setText(getString(R.string.album_language, album.language));
        tvCompany.setText(getString(R.string.album_company, album.publishcompany));
        tvTime.setText(getString(R.string.album_time, album.publishtime));
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
        AlbumDetailEntity detailEntity = (AlbumDetailEntity) data;
        updateHeader(detailEntity.albumInfo);
        adapter.setData(detailEntity.songlist);
    }

}
