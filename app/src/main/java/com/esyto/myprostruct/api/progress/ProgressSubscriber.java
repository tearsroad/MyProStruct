package com.esyto.myprostruct.api.progress;

import android.content.Context;

import com.esyto.myprostruct.api.error.ApiException;
import com.esyto.myprostruct.api.error.ErrorMsg;
import com.esyto.myprostruct.api.error.ResultException;
import com.esyto.myprostruct.base.SubscriberOnNextListener;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by lhxez on 2016/7/18.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    //出错提示
    private final String networkMsg = "网络连接异常，请检查网络";
    private final String parseMsg="数据解析异常";
    private final String unknownMsg="未知错误";

    public ProgressSubscriber( Context context) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this);
    }
    public ProgressSubscriber( Context context,boolean cancelable,String mProgressMsg) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this);
        mProgressDialogHandler.setCancelable(cancelable);
        mProgressDialogHandler.setmProgressMsg(mProgressMsg);
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
        e.printStackTrace();
        dismissProgressDialog();
        Throwable throwable = e;
        //获取最根源的异常
        while(throwable.getCause() != null){
            e = throwable;
            throwable = throwable.getCause();
        }

        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            ex.setDisplayMessage(ErrorMsg.getErrorMsg(httpException.code()));
            onMyError(ex);
        } else if (e instanceof ResultException){    //服务器返回的错误
            ResultException resultException = (ResultException) e;
            ex = new ApiException(resultException, resultException.getErrCode());
            ex.setDisplayMessage(resultException.getMessage());
            onMyError(ex);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, ErrorMsg.PARSE_ERROR);
            ex.setDisplayMessage(parseMsg);            //均视为解析错误
            onMyError(ex);
        }else if(e instanceof SocketTimeoutException){
            ex = new ApiException(e, ErrorMsg.TIMEOUT_ERROR);
            ex.setDisplayMessage("服务器连接超时，请稍后再试！");            //连接超时
            onMyError(ex);
        } else{
            ex = new ApiException(e, ErrorMsg.UNKNOWN);
            ex.setDisplayMessage(unknownMsg);          //未知错误
            onMyError(ex);
        }
    }

    @Override
    public void onNext(T t) {
//        mSubscriberOnNextListener.onNext(t);
        onResult(t);
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
    protected abstract void onResult(T t);
    protected abstract void onMyError(ApiException rx);
}
