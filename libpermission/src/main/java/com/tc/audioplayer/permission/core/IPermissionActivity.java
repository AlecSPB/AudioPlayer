package com.tc.audioplayer.permission.core;


import android.support.v7.app.AppCompatActivity;

/**
 * Created by itcayman on 16/11/28.
 */

public interface IPermissionActivity {
    void initPermission(RunTimePermission permission);

    AppCompatActivity getActivity();

    void finish();
}
