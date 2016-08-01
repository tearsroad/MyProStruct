package com.esyto.myprostruct.model;

import com.esyto.myprostruct.api.Api;
import com.esyto.myprostruct.base.RxSchedulers;
import com.esyto.myprostruct.bean.UserListRp;
import com.esyto.myprostruct.bean.request.UserListRq;
import com.esyto.myprostruct.contract.UserListContract;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/22.
 */

public class UserListModel implements UserListContract.Model{
    @Override
    public Observable<UserListRp> getUserList(UserListRq item) {
        return Api.getInstance().movieService
                .getAllUser(item).compose(RxSchedulers.<UserListRp>applySchedulers());
    }
}
