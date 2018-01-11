package com.tc.audioplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.tc.audioplayer.AudioApplication;
import com.tc.audioplayer.R;
import com.tc.audioplayer.event.EventBusRegisterFlags;
import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.IPermissionFragment;
import com.tc.audioplayer.utils.MarketUtils;
import com.tc.audioplayer.utils.NetUtils;
import com.tc.audioplayer.widget.alertview.TAlert;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tianchao on 2017/8/4.
 */

public class BaseFragment extends Fragment implements IPermissionFragment, IView {
    protected EventBus eventBus;
    private static boolean hasShowGoXufengDialog = false;

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
        //有网络情况下，如果列表完全加载失败（可能Api已经关闭或所在区域不可用），而本地没有播放过的歌曲，
        // 出来一个对话框。 “您可以试试雪峰音乐”，点确定就去市场。
        if (NetUtils.isNetworkAvailable() && !hasShowGoXufengDialog) {
            hasShowGoXufengDialog = true;
            String noticeTitle = AudioApplication.getInstance().getString(R.string.notice_default_title);
            String noticeContent = AudioApplication.getInstance().getString(R.string.notice_go_xufeng_app);
            String ok = AudioApplication.getInstance().getString(R.string.dialog_ok);
            String cancel = AudioApplication.getInstance().getString(R.string.dialog_cancel);
            TAlert.showAlert(getContext(), noticeTitle, noticeContent, ok, cancel,
                    new TAlert.AlertListener() {
                        @Override
                        public void ok() {
                            hasShowGoXufengDialog = false;
                            MarketUtils.gotoRelatedApps(getContext());
                        }

                        @Override
                        public void cancel() {
                            hasShowGoXufengDialog = false;
                        }
                    });
        }
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
