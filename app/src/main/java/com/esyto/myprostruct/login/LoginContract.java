package com.esyto.myprostruct.login;



import android.content.Context;

import com.esyto.myprostruct.base.BaseModel;
import com.esyto.myprostruct.base.BasePresenter;
import com.esyto.myprostruct.base.BaseView;
import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity._User;

import rx.Observable;

/**
 * Created by baixiaokang on 16/4/29.
 */
public interface LoginContract {
    interface Model extends BaseModel {
        Observable<_User> login(LoginItem item);
    }

    interface View extends BaseView {
        void loginSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void login(LoginItem item, Context context);
        @Override
        public void onStart() {}
    }
}
