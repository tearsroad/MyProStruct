package com.esyto.myprostruct.bean;

import com.esyto.myprostruct.C;
import com.esyto.myprostruct.api.test.ApiTest;
import com.esyto.myprostruct.base.BaseEntity;
import com.esyto.myprostruct.base.RxSchedulers;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/21.
 */

public class UserListRp extends BaseEntity.ListBean {
    public String username;
    public String password;
    public String face;
    public String sessionToken;

    @Override
    public Observable getPageAt(int page) {
        return ApiTest.getInstance().movieService.getAllUser(C.PAGE_COUNT * (page - 1), C.PAGE_COUNT).compose(RxSchedulers.io_main());

    }


}
