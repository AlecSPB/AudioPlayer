package com.tc.audioplayer.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.audioplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tianchao on 2017/8/6.
 */

public class Minibar extends LinearLayout {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;

    public Minibar(Context context) {
        super(context);
        init();
    }

    public Minibar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Minibar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_minibar, this);
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
        ButterKnife.bind(this);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
