package com.esyto.myprostruct.api.progress;

import android.content.Context;

import com.esyto.myprostruct.base.SubscriberOnNextListener;

import rx.Subscriber;

/**
 * Created by lhxez on 2016/7/18.
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        mSubscriberOnNextListener.onError(e);
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
    public void setCancelable(boolean cancelable) {
        mProgressDialogHandler.setCancelable(cancelable);
    }


    public void setmProgressMsg(String mProgressMsg) {
        mProgressDialogHandler.setmProgressMsg(mProgressMsg);

    }
}
