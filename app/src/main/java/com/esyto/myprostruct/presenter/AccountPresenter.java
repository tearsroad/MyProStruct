package com.esyto.myprostruct.presenter;

import android.content.Context;

import com.esyto.myprostruct.App;
import com.esyto.myprostruct.api.error.ApiException;
import com.esyto.myprostruct.api.progress.ProgressSubscriber;
import com.esyto.myprostruct.bean.Account;
import com.esyto.myprostruct.bean.request.UserListRq;
import com.esyto.myprostruct.contract.AccountContract;
import com.google.gson.Gson;

/**
 * Created by lhxez on 2016/7/22.
 */

public class AccountPresenter extends AccountContract.Presenter{


    @Override
    public void onStart() {
        UserListRq rq = new UserListRq();
        rq.token = App.mDataCache.token;
        queryAccount(rq,mContext);
    }

    @Override
    public void queryAccount(UserListRq item, Context context) {
        mRxManager.add(
            mModel.queryAccount(item).subscribe(new ProgressSubscriber<Account>(context) {
                @Override
                protected void onResult(Account account) {
                    mView.getAccountSuccess();
                    mView.showMsg(new Gson().toJson(account));
                }

                @Override
                protected void onMyError(ApiException rx) {
                    mView.showMsg(rx.getMessage()+"\n"+rx.getDisplayMessage());
                }
            }));
    }
}
