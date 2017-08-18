package com.tc.audioplayer.base;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tianchao on 2017/8/4.
 */

public abstract class BasePresenter {
    private CompositeSubscription compositeSubscription;

    protected Subscription doRequest(Observable observable, Action1 onNext, Action1 onError) {
        Observable ob = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        if (onError != null) {
            return ob.subscribe(onNext, onError);
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    /**
     * 在处理完成后执行 onFinal。onFinal无论成功还是失败都会执行。
     *
     * @param observable
     * @param onNext
     * @param onError
     * @param onFinal
     * @return
     */
    protected Subscription doRequest(Observable observable, Action1 onNext, Action1<Throwable> onError, Action0 onFinal) {
        Observable ob = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        if (onFinal == null) {
            onFinal = Actions.empty();
        }
        if (onError != null) {
            return ob.doAfterTerminate(onFinal).subscribe(onNext, onError);
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    protected final <T> void addSubscription(Observable<T> observable, Action1<T> onNext, Action1 onError) {
        addSubscription(doRequest(observable, onNext, onError));
    }

//    protected final void addSubscription(Observable observable, SubscribeAction action) {
//        if (action.onPre() != null) {
//            action.onPre().call();
//        }
//        addSubscription(doRequest(observable, action.onNext(), action.onError(), action.onFinal()));
//    }

    protected final void addSubscription(Subscription s) {
        if (s != null && compositeSubscription != null) {
            compositeSubscription.add(s);
        }
    }

    protected final void removeSubscription(Subscription s) {
        if (s != null && compositeSubscription != null) {
            s.unsubscribe();
            compositeSubscription.remove(s);
        }
    }

    public final void clearSubscriptions() {
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }

}
