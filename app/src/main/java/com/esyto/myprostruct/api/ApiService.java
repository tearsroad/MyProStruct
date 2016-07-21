package com.esyto.myprostruct.api;


import com.esyto.myprostruct.entity.Data;
import com.esyto.myprostruct.entity.LoginItem;
import com.esyto.myprostruct.entity.UserListRp;
import com.esyto.myprostruct.entity._User;
import com.esyto.myprostruct.entity.request.UserListRq;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {
    @POST("app/companys/login")
    Observable<_User> login(@Body LoginItem mComment);

    @POST("app/account/getAccountInfos")
    Observable<UserListRp> getAllUser(@Body UserListRq userListRq);

}
