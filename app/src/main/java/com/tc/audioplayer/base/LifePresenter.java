package com.tc.audioplayer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tc.base.utils.TLogger;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by tianchao on 2017/8/4.
 */

public abstract class LifePresenter<V extends IView> extends BasePresenter {
    private WeakReference<V> viewRef;

    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    public abstract void loadData(boolean refresh);

    public <T> void request(boolean refresh, Action1<T> onNext) {

    }

    public void onCreate(Bundle saved) {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onDestroy() {
        detachView();
    }

    public void onDestroyView() {
        clearSubscriptions();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    protected <T> Subscription doRequest(Observable<T> observable) {
        Action1 onNext = getOnNextAction();
        Action1 onError = getOnErrorAction();
        return doRequest(observable, onNext, onError);
    }

    protected final <T> void addSubscription(Observable<T> observable) {
        addSubscription(doRequest(observable));
    }

    protected Action1 getOnNextAction() {
        Action1 onNext = (data) -> {
            if (isViewAttached()) {
                TLogger.e("get data:", data.toString());
                getView().setData(formatData(data));
            }
        };
        return onNext;
    }

    //复写了formatData 如果手动调用setData 需要手动调用formatData
    protected Object formatData(Object data) {
        return data;
    }

    protected Action1 getOnErrorAction() {
        Action1<Throwable> onError = (throwable) -> {
            if (isViewAttached()) {
                TLogger.e("server data error:", throwable.toString());
                getView().handleThrowable(throwable);
            }
        };
        return onError;
    }
}
