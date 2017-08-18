package com.tc.audioplayer.base;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by tianchao on 2017/8/4.
 */

public class BaseFragment extends Fragment implements IView {
    @Override
    public void setData(Object data) {

    }

    @Override
    public void handleThrowable(Throwable t) {
        Log.e("Tag", "handleThrowable: " + t.getMessage());
    }
}
