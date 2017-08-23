package com.tc.audioplayer.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by itcayman on 2017/8/19.
 */

public class RxUtils {
    /**
     * Returns a Subscription that do onNext action with interval time by a interval count
     *
     * @param times  the times to interval
     * @param period the period of time between onNext actions be executed
     */
    public static Subscription createIntervalSubscription(int times, int period, TimeUnit timeUnit, Action1 onNext) {
        return Observable.interval(0, period, timeUnit)
                .takeUntil((index) -> index == times - 1)
                .subscribeOn(Schedulers.io())
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, throwable -> {
                });
    }
}
