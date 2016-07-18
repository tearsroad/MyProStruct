package com.esyto.myprostruct.login;


import android.content.Context;

import com.esyto.myprostruct.C;
import com.esyto.myprostruct.api.progress.ProgressSubscriber;
import com.esyto.myprostruct.base.SubscriberOnNextListener;
import com.esyto.myprostruct.base.util.SpUtil;
import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity._User;
import com.orhanobut.logger.Logger;

/**
 * Created by baixiaokang on 16/4/29.
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login(LoginItem item, Context context) {
        ProgressSubscriber progressSubscriber = new ProgressSubscriber<_User>(subscriberOnNextListener,context);
        progressSubscriber.setCancelable(true);
        progressSubscriber.setmProgressMsg("等待测试连接中...");

        mRxManager.add(mModel.login(item).subscribe(progressSubscriber));
//        mRxManager.add(mModel.login(name, pass).subscribe(user -> {
//                    SpUtil.setUser(user);
//                    mRxManager.post(C.EVENT_LOGIN, user);
//                    mView.loginSuccess();
//                }, e -> mView.showMsg("登录失败!")
//        ));
    }
    private SubscriberOnNextListener subscriberOnNextListener = new SubscriberOnNextListener<_User>(){

        @Override
        public void onNext(_User user) {
            SpUtil.setUser(user);
            user.response_text.toString();
            mView.showMsg(user.toString());
            Logger.w(user.toString());
            mRxManager.post(C.EVENT_LOGIN, user);
            mView.loginSuccess();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            mView.showMsg("网络连接异常！");

        }
    };


}
