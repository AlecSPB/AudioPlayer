package com.tc.audioplayer.bussiness.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class RegisterActivity extends BaseActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_re_password)
    EditText edtRePassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

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

    @OnClick(R.id.tv_back)
    public void onBack() {
        finish();
    }

    @OnClick(R.id.btn_register)
    public void register() {
        String username = edtName.getText().toString();
        String password = edtPassword.getText().toString();
        String repassword = edtRePassword.getText().toString();
        String verifyResult = verifyEdt(username, password, repassword);
        if (TextUtils.isEmpty(verifyResult)) {
            progressBar.setVisibility(View.VISIBLE);
            authRegister(username, password);
        } else {
            Toast.makeText(this, verifyResult, Toast.LENGTH_SHORT).show();
        }
    }

    private void authRegister(String username, String password) {
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            TLogger.d(TAG, "createUserWithEmail:onComplete: " + task.isSuccessful());
                            Navigator.toMainActivity(RegisterActivity.this);
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            TLogger.e(TAG, "createUserWithEmail:fail: " + task.getException().toString());
                            Toast.makeText(RegisterActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String verifyEdt(String username, String password, String repassword) {
        String result = "";
        if (TextUtils.isEmpty(username)) {
            result = getString(R.string.auth_usename_empty);
        }
        if (TextUtils.isEmpty(password)) {
            result = getString(R.string.auth_password_empty);
        }
        if (TextUtils.isEmpty(repassword)) {
            result = getString(R.string.auth_repassword_empty);
        }
        if (!password.equals(repassword)) {
            result = getString(R.string.auth_repassword_not_match);
        }
        return result;
    }
}
