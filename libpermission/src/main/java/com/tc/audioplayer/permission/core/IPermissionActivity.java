package com.sankuai.moviepro.permission.core;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by tianchao on 16/11/28.
 */

public interface IPermissionActivity {
    void initPermission(RunTimePermission permission);

    AppCompatActivity getActivity();

    void finish();
}
