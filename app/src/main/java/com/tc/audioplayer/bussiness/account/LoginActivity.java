package com.tc.audioplayer.bussiness.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tc.audioplayer.Navigator;
import com.tc.audioplayer.R;
import com.tc.audioplayer.base.BaseActivity;
import com.tc.audioplayer.utils.StatusBarUtil;
import com.tc.base.utils.TLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tianchao on 2017/9/19.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_to_register)
    Button btnToRegister;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    TLogger.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
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


    @OnClick(R.id.btn_to_register)
    public void toRegister() {
        Navigator.toRegisterActivity(this);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String username = edtName.getText().toString();
        String password = edtPassword.getText().toString();
        String verifyResult = verifyEdt(username, password);
        if (TextUtils.isEmpty(verifyResult)) {
            authLogin(username, password);
        } else {
            Toast.makeText(this, verifyResult, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Navigator.REQUEST_CODE_REGISTER && resultCode == RESULT_OK) {
            finish();
        }
    }

    private void authLogin(String usename, String password) {
        mAuth.signInWithEmailAndPassword(usename, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        TLogger.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            Navigator.toMainActivity(LoginActivity.this);
                        } else {
                            TLogger.e(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_login_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String verifyEdt(String username, String password) {
        String result = "";
        if (TextUtils.isEmpty(username)) {
            result = getString(R.string.auth_usename_empty);
        }
        if (TextUtils.isEmpty(password)) {
            result = getString(R.string.auth_password_empty);
        }
        return result;
    }
}
