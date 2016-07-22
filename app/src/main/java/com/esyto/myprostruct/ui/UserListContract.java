package com.esyto.myprostruct.ui;

import android.content.Context;

import com.esyto.myprostruct.base.BaseModel;
import com.esyto.myprostruct.base.BasePresenter;
import com.esyto.myprostruct.base.BaseView;
import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity._User;
import com.esyto.myprostruct.login.LoginContract;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/22.
 */

public interface UserListContract {
    interface Model extends BaseModel {
        Observable<_User> login(LoginItem item);
    }

    interface View extends BaseView {
        void loginSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
        public abstract void login(LoginItem item, Context context);

    }
}
