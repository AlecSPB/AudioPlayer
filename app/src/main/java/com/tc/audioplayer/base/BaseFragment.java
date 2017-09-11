package com.tc.audioplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.IPermissionFragment;

/**
 * Created by tianchao on 2017/8/4.
 */

public class BaseFragment extends Fragment implements IPermissionFragment, IView {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void handleThrowable(Throwable t) {
        Log.e("Tag", "handleThrowable: " + t.getMessage());
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public IPermissionActivity getPermissionActivity() {
        return (IPermissionActivity) getActivity();
    }
}
