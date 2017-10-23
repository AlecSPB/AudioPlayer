package com.tc.audioplayer.bussiness.search;

import android.text.TextUtils;

import com.tc.audioplayer.base.LifePresenter;
import com.tc.base.utils.SharedPreferencesUtil;
import com.tc.model.entity.HotSearch;
import com.tc.model.entity.SearchWrapper;
import com.tc.model.usecase.OnlineCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by itcayman on 2017/9/7.
 */

public class SearchPresent extends LifePresenter {
    private static final String KEY_NAME = "search";
    private static final String KEY_SEARCH = "search_history";

    public SearchPresent() {
        onlineCase = new OnlineCase();
    }

    private OnlineCase onlineCase;

    @Override
    public void loadData(boolean refresh) {

    }

    public void search(String key, Action1<SearchWrapper> showResult) {
        doRequest(onlineCase.getSearch(key), showResult, getOnErrorAction());
    }

    public void requestHotSearch(Action1<List<HotSearch>> hotSearchResult) {
        Action1<Throwable> onError = (throwable) -> {
        };
        doRequest(onlineCase.getHotSearch(), hotSearchResult, onError);
    }

    /**
     * 获取历史搜索记录
     */
    public static List<String> getSearchHistoryData() {
        String str = SharedPreferencesUtil.getString(KEY_NAME, KEY_SEARCH, "");
        String[] array = str.split(",");
        if (TextUtils.isEmpty(str) || array == null || array.length == 0)
            return null;
        List<String> strList = Arrays.asList(array);
        Collections.reverse(strList);
        return strList;
    }

    /**
     * 存储搜索历史到本地
     *
     * @param key 搜索关键词
     */
    public static void saveSearchKey(String key) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(key.trim())) {
            return;
        }
        deleteSearchKey(key);
        String str = SharedPreferencesUtil.getString(KEY_NAME, KEY_SEARCH, "");
        if (TextUtils.isEmpty(str)) {
            str += key;
        } else {
            str = str + "," + key;
        }
        SharedPreferencesUtil.putString(KEY_NAME, KEY_SEARCH, str);
    }

    /**
     * 删除某个本地搜索历史
     *
     * @param key 搜索关键词
     */
    public static void deleteSearchKey(String key) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(key.trim())) {
            return;
        }
        String str = SharedPreferencesUtil.getString(KEY_NAME, KEY_SEARCH, "");
        String[] array = str.split(",");
        if (TextUtils.isEmpty(str) || array == null || array.length == 0)
            return;
        for (int i = 0; i < array.length; i++) {
            if (array[i].contains(key)) {
                str = str.replace("," + key, "");
                str = str.replace(key, "");
                break;
            }
        }
        SharedPreferencesUtil.putString(KEY_NAME, KEY_SEARCH, str);
    }

    /**
     * 清空搜索历史记录
     */
    public static void clearSearchHistory() {
        SharedPreferencesUtil.putString(KEY_NAME, KEY_SEARCH, "");
    }
}
