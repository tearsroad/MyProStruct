package com.esyto.myprostruct.api.progress;

import android.content.Context;

import com.esyto.myprostruct.api.error.ApiException;
import com.esyto.myprostruct.api.error.ResultException;
import com.esyto.myprostruct.base.SubscriberOnNextListener;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by lhxez on 2016/7/18.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
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
            switch(httpException.code()){
                case UNAUTHORIZED:
                case FORBIDDEN:
                    ex.setDisplayMessage(e.getMessage());
                    onError(ex);        //权限错误，需要实现
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage(networkMsg);  //均视为网络错误
                    onError(ex);
                    break;
            }
        } else if (e instanceof ResultException){    //服务器返回的错误
            ResultException resultException = (ResultException) e;
            ex = new ApiException(resultException, resultException.getErrCode());
            onError(ex);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, ApiException.PARSE_ERROR);
            ex.setDisplayMessage(parseMsg);            //均视为解析错误
            onError(ex);
        } else {
            ex = new ApiException(e, ApiException.UNKNOWN);
            ex.setDisplayMessage(unknownMsg);          //未知错误
            onError(ex);
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
    protected abstract void onError(ResultException rx);
}
