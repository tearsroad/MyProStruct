package com.esyto.myprostruct.presenter;


import android.content.Context;

import com.esyto.myprostruct.App;
import com.esyto.myprostruct.C;
import com.esyto.myprostruct.api.error.ApiException;
import com.esyto.myprostruct.api.progress.ProgressSubscriber;
import com.esyto.myprostruct.bean._User;
import com.esyto.myprostruct.bean.request.LoginItem;
import com.esyto.myprostruct.contract.LoginContract;

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
                App.mDataCache.token = user.token;
                mView.showMsg("token:"+user.token+";"+user.toString());
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
        String username = "18030024119";
        String pwd = "123456";
        LoginItem item = new LoginItem();
//        LoginItem.Account ac = item.new Account();
        item.account = username;
        item.password = pwd;
//        item.request_text = ac;
        login(item,mContext);
    }
}
