package com.tc.audioplayer.permission;

import android.Manifest;
import android.content.Context;

import com.tc.audioplayer.permission.core.IPermissionActivity;
import com.tc.audioplayer.permission.core.IPermissionFragment;
import com.tc.audioplayer.permission.core.PermissionCode;
import com.tc.audioplayer.permission.core.RunTimePermission;


/**
 * Created by itcayman on 16/9/12.
 */
public class LocationPermission extends RunTimePermission {

    public static final int CODE_ACCESS_FINE_LOCATION = PermissionCode.ACCESS_FINE_LOCATION;

    public LocationPermission(IPermissionActivity activity) {
        super(activity);
    }

    public LocationPermission(IPermissionFragment fragment) {
        super(fragment);
    }

    @Override
    public int getRequestCode() {
        return CODE_ACCESS_FINE_LOCATION;
    }

    @Override
    public String getRequestPermission() {
        return Manifest.permission.ACCESS_FINE_LOCATION;
    }

    @Override
    public String getRationalString(Context context) {
        return context.getString(R.string.permission_location_message);
    }
}
