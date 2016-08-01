package com.esyto.myprostruct.contract;

import android.content.Context;

import com.esyto.myprostruct.base.BaseModel;
import com.esyto.myprostruct.base.BasePresenter;
import com.esyto.myprostruct.base.BaseView;
import com.esyto.myprostruct.bean.Account;
import com.esyto.myprostruct.bean.request.UserListRq;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/22.
 */

public interface AccountContract {
    interface Model extends BaseModel {
        Observable<Account> queryAccount(UserListRq item);
    }

    interface View extends BaseView {
        void getAccountSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void queryAccount(UserListRq item, Context context);

    }
}
