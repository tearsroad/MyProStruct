package com.esyto.myprostruct.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esyto.myprostruct.R;
import com.esyto.myprostruct.base.BaseActivity;

public class UserListActivity extends BaseActivity<UserListPresenter,UserListModel> implements UserListContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void showMsg(String msg) {

    }
}
