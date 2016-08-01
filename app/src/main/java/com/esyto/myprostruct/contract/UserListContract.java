package com.esyto.myprostruct.contract;

import android.content.Context;

import com.esyto.myprostruct.base.BaseModel;
import com.esyto.myprostruct.base.BasePresenter;
import com.esyto.myprostruct.base.BaseView;
import com.esyto.myprostruct.bean.UserListRp;
import com.esyto.myprostruct.bean.request.UserListRq;

import rx.Observable;

/**
 * Created by lhxez on 2016/7/22.
 */

public interface UserListContract {
    interface Model extends BaseModel {
        Observable<UserListRp> getUserList(UserListRq item);
    }

    interface View extends BaseView {
        void getListSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getUserList(UserListRq item, Context context);

    }
}
