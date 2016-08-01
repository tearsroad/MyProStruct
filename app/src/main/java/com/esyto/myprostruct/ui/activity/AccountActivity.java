package com.esyto.myprostruct.ui.activity;

import android.widget.TextView;

import com.esyto.myprostruct.R;
import com.esyto.myprostruct.base.BaseActivity;
import com.esyto.myprostruct.contract.AccountContract;
import com.esyto.myprostruct.model.AccountModel;
import com.esyto.myprostruct.presenter.AccountPresenter;

import butterknife.Bind;

public class AccountActivity extends BaseActivity<AccountPresenter,AccountModel> implements AccountContract.View {

    @Bind(R.id.tv_msg)
    TextView tvMsg;


    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public void initView() {

    }

    @Override
    public void getAccountSuccess() {

    }

    @Override
    public void showMsg(String msg) {
        tvMsg.setText(msg);
    }
}
