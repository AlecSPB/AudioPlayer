package com.tc.audioplayer;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tc.audioplayer.base.ToolbarActivity;
import com.tc.audioplayer.permission.ExternalStoragePermission;
import com.tc.audioplayer.permission.PermissionUtil;
import com.tc.base.utils.TLogger;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import static com.tc.audioplayer.MainActivity.MUTI_PERMISSION_WINDOW;

/**
 * Created by itcayman on 2017/9/11.
 */

public class WelcomeActivity extends ToolbarActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private ExternalStoragePermission mReadExternalStoragePermission;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean signedIn = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivBg.setImageResource(R.drawable.welcome);
        setBgImageFitScreen();
        toolbar.setVisibility(View.GONE);


        mReadExternalStoragePermission = new ExternalStoragePermission(this);
        PermissionUtil.requestPermissions(this, MUTI_PERMISSION_WINDOW, mReadExternalStoragePermission);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    signedIn = true;
                    TLogger.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    signedIn = false;
                    TLogger.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @PermissionDenied(MUTI_PERMISSION_WINDOW)
    public void requestWelcomeFailed() {
        TLogger.d(TAG, "requestWelcomeFailed");
        doPermissionResult();
    }

    @PermissionGrant(MUTI_PERMISSION_WINDOW)
    public void requestWelcomeSuccess() {
        TLogger.d(TAG, "requestWelcomeSuccess");
        doPermissionResult();
    }

    private void doPermissionResult() {
        mReadExternalStoragePermission.requestPermission();
    }

    @PermissionGrant(ExternalStoragePermission.CODE_READ_EXTERNAL_STORAGE)
    public void requestReadSuccess() {
        TLogger.d(TAG, "requestReadSuccess");
        new Handler().postDelayed(() -> {
            Navigator.toMainActivity(WelcomeActivity.this);
            finish();
        }, 2000);
    }

    @PermissionDenied(ExternalStoragePermission.CODE_READ_EXTERNAL_STORAGE)
    public void requestReadFailed() {
        TLogger.d(TAG, "requestReadFailed");
        mReadExternalStoragePermission.permissionDenied();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
