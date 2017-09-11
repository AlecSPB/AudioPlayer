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
    private SearchResultFragment searchResultFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_empty);
        flToolbarContent.setVisibility(View.VISIBLE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        searchResultFragment = new SearchResultFragment();
        transaction.replace(R.id.fl_content, searchResultFragment).commit();
        initUI();
    }

    private void initUI() {
        cetSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        cetSearch.setOnEditorActionListener(etActionListener);

        minibar.postDelayed(() -> {
            minibar.bindData();
        }, 500);
        cetSearch.requestFocus();
    }

    private TextView.OnEditorActionListener etActionListener = (view, actionId, event) -> {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String content = cetSearch.getText().toString();
            if(TextUtils.isEmpty(content))
                return true;
            searchResultFragment.search(content);
        }
        InputMethodUtil.hidden(cetSearch);
        return true;
    };
}
