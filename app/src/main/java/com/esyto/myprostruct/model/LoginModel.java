package com.esyto.myprostruct.model;

import com.esyto.myprostruct.api.Api;
import com.esyto.myprostruct.base.RxSchedulers;
import com.esyto.myprostruct.bean._User;
import com.esyto.myprostruct.bean.request.LoginItem;
import com.esyto.myprostruct.contract.LoginContract;

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
