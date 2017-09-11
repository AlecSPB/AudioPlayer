package com.tc.audioplayer.permission;

import android.Manifest;
import android.content.Context;

import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.IPermissionFragment;
import com.tc.audioplayer.permission.core.PermissionCode;
import com.tc.audioplayer.permission.core.RunTimePermission;


/**
 * Created by itcayman on 16/9/9.
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
