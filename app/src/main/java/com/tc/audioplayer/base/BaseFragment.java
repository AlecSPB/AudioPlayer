package com.tc.audioplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by tianchao on 2017/8/4.
 */

public class BaseFragment extends Fragment implements IView {

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
}
