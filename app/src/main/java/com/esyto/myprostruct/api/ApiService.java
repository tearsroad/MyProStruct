package com.esyto.myprostruct.api;


import com.esyto.myprostruct.bean.Account;
import com.esyto.myprostruct.bean.UserListRp;
import com.esyto.myprostruct.bean._User;
import com.esyto.myprostruct.bean.request.LoginItem;
import com.esyto.myprostruct.bean.request.UserListRq;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {
    @POST("app/companys/login")
    Observable<_User> login(@Body LoginItem mComment);

    @POST("app/account/queryAccount")
    Observable<Account> queryAccount(@Body UserListRq userListRq);

    @POST("app/account/getAccountInfos")
    Observable<UserListRp> getAllUser(@Body UserListRq userListRq);

}
