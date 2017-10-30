package com.tc.audioplayer.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by tianchao on 17/2/20.
 */

public class ProgressView extends View implements Runnable {
    private Paint mPaint;
    private int mWidth, mHeight;
    private MHandler mHandler;
    private Thread thread;
    private boolean threadStart;
    private static final int[] SECTION_COLORS = {Color.parseColor("#00FFFFFF"), Color.parseColor("#73a3e5"), Color.parseColor("#00FFFFFF")};
    private float[] positions;
    private float positionOffset = 0.01f;
    private RectF rectProgressBg;

    private class MHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    }

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mHandler = new MHandler();
        positions = new float[3];
        positions[0] = 0.3f;
        positions[1] = 1.0f;
        positions[2] = 1.5f;
        rectProgressBg = new RectF(0, 1, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!threadStart)
            return;
        positions[0] = positions[0] + positionOffset;
        positions[1] = positions[1] + positionOffset;
        positions[2] = positions[2] + positionOffset;
        if (positions[1] >= 1) {
            positionOffset = -0.01f;
        }
        if (positions[1] <= 0) {
            positionOffset = 0.01f;
        }
        float section = 1f;
        rectProgressBg.right = mWidth * section;
        rectProgressBg.bottom = mHeight - 1;
        LinearGradient shader = new LinearGradient(3, 0, (mWidth - 3)
                * section, mHeight, SECTION_COLORS, positions,
                Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        canvas.drawRoundRect(rectProgressBg, 0, 0, mPaint);
    }

    @Override
    public void run() {
        try {
            while (threadStart) {
                Thread.sleep(40);
                mHandler.sendEmptyMessage(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (!threadStart) {
            threadStart = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        threadStart = false;
        thread = null;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY
                || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST
                || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(5);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        super.onDetachedFromWindow();
    }

    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
