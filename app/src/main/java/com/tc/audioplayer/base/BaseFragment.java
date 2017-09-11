package com.tc.audioplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.IPermissionFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tianchao on 2017/8/4.
 */

public class BaseFragment extends Fragment implements IPermissionFragment, IView {
    protected EventBus eventBus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
        registerEventBus(configDefaultRigsterFlags());
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    /**
     * 当前Fragment作为一个订阅者，默认订阅周期为onActivityCreated()-----onDestroyView()
     * 如果需要改变订阅者的订阅周期，无须重写此方法
     * 请直接在子类的相应周期回调中调用eventBus.register***(this) 和 eventBus.unregister(this)即可
     *
     * @return
     */
    protected int configDefaultRigsterFlags() {
        return EventBusRegisterFlags.NOT_NEED_DEFAULT_REGISTER;
    }

    /**
     * 反注册EventBus
     */
    private void registerEventBus(int flag) {
        if (eventBus.isRegistered(this))
            return;

        if (flag == EventBusRegisterFlags.NEED_DEFAULT_REGISTER) {
            eventBus.register(this);
        }
    }
}
