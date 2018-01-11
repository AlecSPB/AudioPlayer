package com.tc.audioplayer.widget.alertview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tc.audioplayer.R;

public class AlertBuilder extends Dialog implements DialogInterface {

    public interface CancelListener {
        void cancel();
    }

    private CancelListener mCancelListener;

    private Effectstype type = null;
    private LinearLayout mLinearLayoutView;
    private RelativeLayout mRelativeLayoutView;
    private LinearLayout mLinearLayoutMsgView;
    private LinearLayout mLinearLayoutTopView;
    private View mDialogView;
    private TextView mTitle;
    private TextView mMessage;
    private Button okButton;
    private Button cancelButton;
    private int mDuration = -1;
    private boolean isCancelable = false;

    public static AlertBuilder getInstance(Context context) {
        return new AlertBuilder(context, R.style.dialog_untran);
    }

    public AlertBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
    }

    public AlertBuilder setCancelListener(CancelListener cancelListener) {
        this.mCancelListener = cancelListener;
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mCancelListener != null) {
                    mCancelListener.cancel();
                }
            }
        });

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mCancelListener != null) {
                    mCancelListener.cancel();
                }
            }
        });
        return this;
    }

    private void init(Context context) {
        mDialogView = LayoutInflater.from(context).inflate(R.layout.api_dialog_layout, null);
        mLinearLayoutView = (LinearLayout) mDialogView.findViewById(R.id.parentPanel);
        mRelativeLayoutView = (RelativeLayout) mDialogView.findViewById(R.id.main);
        mLinearLayoutTopView = (LinearLayout) mDialogView.findViewById(R.id.topPanel);
        mLinearLayoutMsgView = (LinearLayout) mDialogView.findViewById(R.id.contentPanel);
        mTitle = (TextView) mDialogView.findViewById(R.id.alertTitle);
        mMessage = (TextView) mDialogView.findViewById(R.id.messageShow);
        okButton = (Button) mDialogView.findViewById(R.id.button1);
        cancelButton = (Button) mDialogView.findViewById(R.id.button2);

        setContentView(mDialogView);
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mLinearLayoutView.setVisibility(View.VISIBLE);
                if (type == null) {
                    type = Effectstype.Slidetop;
                }
                start(type);
            }
        });

        mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCancelable) {
                    dismiss();
                }
            }
        });

        isCancelableOnTouchOutside(false);
    }


    public AlertBuilder withTitle(CharSequence title) {
        toggleView(mLinearLayoutTopView, title);
        mTitle.setText(title);
        return this;
    }

    public AlertBuilder withMessage(int textResId) {
        toggleView(mLinearLayoutMsgView, textResId);
        mMessage.setText(textResId);
        return this;
    }

    public AlertBuilder withMessage(CharSequence msg) {
        toggleView(mLinearLayoutMsgView, msg);
        if (mMessage != null) {
            mMessage.setText(msg);
        }
        return this;
    }

    public AlertBuilder withDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public AlertBuilder withEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public AlertBuilder withOkButtonText(CharSequence text) {
        okButton.setVisibility(View.VISIBLE);
        okButton.setBackgroundColor(Color.TRANSPARENT);
        okButton.setTextColor(Color.parseColor("#ffa42f"));
        okButton.setText(text);
        return this;
    }

    public AlertBuilder withCancelButtonText(CharSequence text) {
        cancelButton.setVisibility(View.VISIBLE);
        cancelButton.setBackgroundColor(Color.TRANSPARENT);
        cancelButton.setTextColor(Color.parseColor("#999999"));
        cancelButton.setText(text);
        return this;
    }

    public AlertBuilder setOnOkButtonClick(View.OnClickListener click) {
        okButton.setOnClickListener(click);
        return this;
    }

    public AlertBuilder setOnCheckButtonClick(View.OnClickListener click) {
        return this;
    }

    public AlertBuilder setOnCacnelButtonClick(View.OnClickListener click) {
        cancelButton.setOnClickListener(click);
        return this;
    }

    public AlertBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public AlertBuilder isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void toggleView(View view, Object obj) {
        if (obj == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void cancel() {
        super.cancel();
    }
}
