package com.sankuai.moviepro.permission;

import android.Manifest;
import android.content.Context;

import com.sankuai.moviepro.permission.core.IPermissionActivity;
import com.sankuai.moviepro.permission.core.IPermissionFragment;
import com.sankuai.moviepro.permission.core.PermissionCode;
import com.sankuai.moviepro.permission.core.RunTimePermission;

/**
 * Created by zhangtao21 on 16/9/9.
 */
public class PhoneStatePermission extends RunTimePermission {

    public static final int CODE_READ_PHONE_STATE = PermissionCode.READ_PHONE_STATE;

    public PhoneStatePermission(IPermissionActivity activity) {
        super(activity);
    }

    public PhoneStatePermission(IPermissionFragment fragment) {
        super(fragment);
    }

    @Override
    public int getRequestCode() {
        return CODE_READ_PHONE_STATE;
    }

    @Override
    public String getRequestPermission() {
        return Manifest.permission.READ_PHONE_STATE;
    }

    @Override
    public String getRationalString(Context context) {
        return context.getString(R.string.permission_phone_state_message);
    }

}
