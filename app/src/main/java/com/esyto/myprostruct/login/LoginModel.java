package com.esyto.myprostruct.login;

import com.esyto.myprostruct.api.Api;
import com.esyto.myprostruct.base.RxSchedulers;
import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity._User;

import rx.Observable;

/**
 * Created by baixiaokang on 16/5/2.
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<_User> login(LoginItem item) {
        return Api.getInstance().movieService
                .login(item).compose(RxSchedulers.<_User>applySchedulers());
    }

}
