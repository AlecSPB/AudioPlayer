package com.tc.audioplayer.bussiness.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.tc.audioplayer.R;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.utils.InputMethodUtil;

/**
 * Created by itcayman on 2017/8/30.
 */

public class SearchActivity extends ToolbarActivity {
    private SearchFragment searchResultFragment;
    private String keyword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.common_push_up_in, 0);
        setContentView(R.layout.layout_empty);
        setContentUnderToolbar();
        minibar.setAutoVisibility(true);
        minibar.bindData();

        keyword = getIntent().getStringExtra("keyword");
        flToolbarContent.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setEnabled(false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        searchResultFragment = new SearchFragment();
        transaction.replace(R.id.fl_content, searchResultFragment).commit();
        initUI();
        if (!TextUtils.isEmpty(keyword)) {
            cetSearch.clearFocus();
            InputMethodUtil.hidden(cetSearch);
            searchResultFragment.search(keyword);
            cetSearch.setText(keyword);
        } else {
            cetSearch.requestFocus();
            InputMethodUtil.show(cetSearch);
        }
    }

    private void initUI() {
        flToolbarContent.setVisibility(View.VISIBLE);
        cetSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        cetSearch.setOnEditorActionListener(etActionListener);
        toolbar.setNavigationIcon(null);

        minibar.postDelayed(() -> {
            minibar.bindData();
        }, 500);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(null, color);
    }

    private TextView.OnEditorActionListener etActionListener = (view, actionId, event) -> {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String content = cetSearch.getText().toString();
            if (TextUtils.isEmpty(content))
                return true;
            searchResultFragment.search(content);
        }
        InputMethodUtil.hidden(cetSearch);
        return true;
    };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.common_push_up_out);
    }
}
