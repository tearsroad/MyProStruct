package com.esyto.myprostruct.ui;

import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity._User;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/22.
 */

public class UserListModel implements UserListContract.Model{
    @Override
    public Observable<_User> login(LoginItem item) {
        return null;
    }
}
