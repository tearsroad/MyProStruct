package com.esyto.myprostruct.base;

/**
 * Created by lhxez on 2016/7/18.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable e);
}