package com.esyto.myprostruct.api.test;

import com.esyto.myprostruct.bean.Data;
import com.esyto.myprostruct.bean.UserListRp;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiServiceTest {


    @GET("users")
    Observable<Data<UserListRp>> getAllUser(@Query("skip") int skip, @Query("limit") int limit);

}
