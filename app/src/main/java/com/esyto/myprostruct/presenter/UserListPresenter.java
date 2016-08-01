package com.esyto.myprostruct.presenter;

import android.content.Context;

import com.esyto.myprostruct.api.error.ApiException;
import com.esyto.myprostruct.api.progress.ProgressSubscriber;
import com.esyto.myprostruct.bean.UserListRp;
import com.esyto.myprostruct.bean.request.UserListRq;
import com.esyto.myprostruct.contract.UserListContract;
import com.google.gson.Gson;

/**
 * Created by lhxez on 2016/7/22.
 */

public class UserListPresenter extends UserListContract.Presenter{


    @Override
    public void onStart() {

    }

    @Override
    public void getUserList(UserListRq item, Context context) {
        mRxManager.add(
            mModel.getUserList(item).subscribe(new ProgressSubscriber<UserListRp>(context) {
                @Override
                protected void onResult(UserListRp userListRp) {
                    mView.getListSuccess();
                    mView.showMsg(new Gson().toJson(userListRp));
                }

                @Override
                protected void onMyError(ApiException rx) {
                    mView.showMsg(rx.getMessage()+"\n"+rx.getDisplayMessage());
                }
            }));
    }
}
