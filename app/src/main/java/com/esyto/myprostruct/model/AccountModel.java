package com.esyto.myprostruct.model;

import com.esyto.myprostruct.api.Api;
import com.esyto.myprostruct.base.RxSchedulers;
import com.esyto.myprostruct.bean.Account;
import com.esyto.myprostruct.bean.request.UserListRq;
import com.esyto.myprostruct.contract.AccountContract;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/22.
 */

public class AccountModel implements AccountContract.Model{

    @Override
    public Observable<Account> queryAccount(UserListRq item) {
        return Api.getInstance().movieService
                .queryAccount(item).compose(RxSchedulers.<Account>applySchedulers());
    }
}
