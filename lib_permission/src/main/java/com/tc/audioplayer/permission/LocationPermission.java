package com.sankuai.moviepro.permission;

import android.Manifest;
import android.content.Context;

import com.sankuai.moviepro.permission.core.IPermissionActivity;
import com.sankuai.moviepro.permission.core.IPermissionFragment;
import com.sankuai.moviepro.permission.core.PermissionCode;
import com.sankuai.moviepro.permission.core.RunTimePermission;


/**
 * Created by zhangtao21 on 16/9/12.
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
