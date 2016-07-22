package com.esyto.myprostruct.login;


import android.content.Context;

import com.esyto.myprostruct.C;
import com.esyto.myprostruct.api.error.ApiException;
import com.esyto.myprostruct.api.error.ResultException;
import com.esyto.myprostruct.api.progress.ProgressSubscriber;
import com.esyto.myprostruct.base.SubscriberOnNextListener;
import com.esyto.myprostruct.base.util.SpUtil;
import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity._User;
import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * Created by baixiaokang on 16/4/29.
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login(LoginItem item, Context context) {
//        ProgressSubscriber progressSubscriber = new ProgressSubscriber<_User>(subscriberOnNextListener,context);
//        progressSubscriber.setCancelable(true);
//        progressSubscriber.setmProgressMsg("等待测试连接中...");

        mRxManager.add(mModel.login(item).subscribe(new ProgressSubscriber<_User>(context){

            @Override
            protected void onResult(_User user) {
                SpUtil.setUser(user);
                mView.showMsg(user.toString());
                mRxManager.post(C.EVENT_LOGIN, user);
                mView.loginSuccess();
            }

            @Override
            protected void onMyError(ApiException rx) {
                mView.showMsg(rx.getMessage()+"\n"+rx.getDisplayMessage());
            }
        }));
    }

    @Override
    public void onStart() {

    }
}
