package com.tc.audioplayer.permission.core;


import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by itcayman on 16/11/28.
 */

public interface IPermissionFragment {
    Fragment getFragment();

    IPermissionActivity getPermissionActivity();

    boolean isAdded();

    Context getContext();
}
